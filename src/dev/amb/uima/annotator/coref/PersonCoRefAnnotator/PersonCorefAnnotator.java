package dev.amb.uima.annotator.coref.PersonCoRefAnnotator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import opennlp.tools.coref.DefaultLinker;
import opennlp.tools.coref.DiscourseEntity;
import opennlp.tools.coref.Linker;
import opennlp.tools.coref.LinkerMode;
import opennlp.tools.coref.mention.DefaultParse;
import opennlp.tools.coref.mention.Mention;
import opennlp.tools.coref.mention.MentionContext;
import opennlp.tools.lang.english.TreebankLinker;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.AbstractBottomUpParser;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import dev.amb.uima.typeSystem.core.Sentence;
import dev.amb.uima.typeSystem.core.Token;
import dev.amb.uima.typeSystem.coref.CorefDiscourseEntity;
import dev.amb.uima.typeSystem.coref.RefMention;
import dev.amb.uima.typeSystem.ner.Person;

public class PersonCorefAnnotator extends JCasAnnotator_ImplBase {


	// modelling resources
	private SentenceDetectorME sentDetect = null;
	private TokenizerME tokenDetect = null;
	private POSTaggerME posTagger = null;
	private NameFinderME personNameFinder = null;
	private Linker corefLinker = null;
	private Parser treeParser = null;


	// annotation lists
	private ArrayList<Sentence>	allSentences = null;
	private ArrayList<Token> allTokens = null;
	private ArrayList<Person> allPeople = null;


	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// set up modelling resources
		this.sentDetect = this.createSentenceDetector();

		this.tokenDetect = this.createTokenizer();

		this.posTagger = this.createPOSTagger();

		this.treeParser = this.createTreeParser();

		this.personNameFinder = this.createPersonFinder();

		this.corefLinker = this.createCorefLinker();

	}


	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		// TODO do processing within this annotator, but break out over aggregate
		// process sentences
		this.processSentences(jcas);

		// process tokens
		this.processTokens(jcas);

		// process person entities
		this.processPersonEntities(jcas);

		// process noun-phrase coreference
		this.processCoreference(jcas);

		// find mention groups which refer to each person span
		// what if DiscourseEntity mentions contain more than one namedEntity?
		// what if namedEntity is in more than one DiscourseEntity??
		// merge; pick one; selection mechanism to pick a new set from the mentionSets

	}

	// util processing methods to help break up into AggregateAE
	private void processCoreference(JCas jcas) throws AnalysisEngineProcessException {

		if(this.allSentences == null) {
			this.allSentences = this.getAllSentences(jcas);
		}

		try {

			int sentCount = 0;

			for(Sentence sent : allSentences) {

				sentCount = sentCount + 1;

				int sentStartIndex = sent.getBegin();

				ArrayList<Token> tokens = this.getIndexedTokensForSentence(jcas, sent);

				ArrayList<String> tokenValues = new ArrayList<String>();

				for(Token token : tokens) {

					tokenValues.add(token.getCoveredText());

				}

				// do POS tagging - we might want to persist
				String[] tokenValuesArray = {};

				String[] posTags = posTagger.tag(tokenValues.toArray(tokenValuesArray));


				// generate a Parse of flat tokens for the sentence
				Parse sentParser = new Parse(sent.getCoveredText(), new Span(0 , sent.getCoveredText().length()), AbstractBottomUpParser.INC_NODE, 1, 0);

				for (Token token : tokens) {



					sentParser.insert(new Parse(sent.getCoveredText(), new Span( token.getSentStartPos() , token.getSentEndPos() ), AbstractBottomUpParser.TOK_NODE, 0 , sentCount));

				}

				// full tree parse on the input sentence parse data
				Parse sentParseResult = treeParser.parse(sentParser);

				// now we have parseTree result for sentence, use the CoRef linker
				DefaultParse parseWrapper = new DefaultParse(sentParseResult, sentCount);

				// obtain coRefLinker mention results
				Mention[] extents = corefLinker.getMentionFinder().getMentions(parseWrapper);

				int entTotal = extents.length;

				for (int entIndex = 0; entIndex < entTotal; entIndex++) {

					// construct parses for mentions which don't have constituents
					if (extents[entIndex].getParse() == null) {

						// not sure how to get head index, but it doesn't seem to be used at this point
						final Parse snp = new Parse(sentParseResult.getText(), extents[entIndex].getSpan(), "NML", 1.0, 0);

						sentParseResult.insert(snp);

						// replace current extend with Parse
						extents[entIndex].setParse(new DefaultParse(snp, sentCount));
					}

					// gather all extent mentions into a list
					List<Mention> mentions = new ArrayList<Mention>();

					mentions.addAll(Arrays.asList(extents));

					// get all discourse entities from list of extent mentions
					Mention[] mArray = {};
					Mention[] mResultArray = mentions.toArray(mArray);

					if(mResultArray == null) {
						System.out.println("For some reason, results for mentions[] created from extents is null");
					}

					DiscourseEntity[] discourse = null;

					try {

						discourse = corefLinker.getEntities(mResultArray);

					} catch (Exception e) {

						e.printStackTrace();

					}

					if(discourse != null && discourse.length != 0) {

						for(DiscourseEntity discEnt :  discourse) {

							System.out.println("Discourse entity :: " + discEnt.toString());

							CorefDiscourseEntity corefDE = new CorefDiscourseEntity(jcas);

							int numMentions = discEnt.getNumMentions();

							corefDE.setNumberOfMentions(numMentions);

							if(discEnt.getMentions() != null) {

								Iterator<MentionContext> discEntMentions = discEnt.getMentions();

								List<RefMention> refMentions = new ArrayList<RefMention>();

								while(discEntMentions.hasNext()) {

									Mention mention =  discEntMentions.next();

									System.out.println("Discourse entity parse :: " + mention.getParse().toString());

									RefMention refMention = new RefMention(jcas);

									refMention.setBegin(sentStartIndex + mention.getSpan().getStart());

									refMention.setEnd(sentStartIndex + mention.getSpan().getEnd());

									refMentions.add(refMention);

									refMention.addToIndexes();

								}

								// not like a list... use factory method to create :(
								FSList refs = this.createRefMentionFSList(jcas, refMentions);

								corefDE.setCorefMentions(refs);

								corefDE.addToIndexes();
							}



						}

					}

				}

			}

		} catch(Exception e) {

			throw new AnalysisEngineProcessException(e);

		}

	}


	private void processPersonEntities(JCas jcas) throws AnalysisEngineProcessException {

		try {

			ArrayList<Sentence> allSentences = this.getAllSentences(jcas);

			for(Sentence sent : allSentences) {

				ArrayList<Token> tokens = this.getIndexedTokensForSentence(jcas, sent);


				ArrayList<String> tokenValues = new ArrayList<String>();

				for(Token token : tokens) {

					tokenValues.add(token.getCoveredText());

				}

				String[] tokenStrings = {};

				Span[] people = personNameFinder.find(tokenValues.toArray(tokenStrings));

				for (Span person : people) {
					// as person annotation spans multiple tokens, need to find indexes <startOfFirst , endOfLast>
					int startIndex = tokens.get(person.getStart()).getBegin();

					int endIndex = tokens.get(person.getEnd() - 1).getEnd();

					Person personEnt = new Person(jcas);

					personEnt.setBegin(startIndex);

					personEnt.setEnd(endIndex);

					personEnt.addToIndexes();
				}


			}

		} catch(Exception e) {
			throw new AnalysisEngineProcessException(e);
		}

	}


	private void processTokens(JCas jcas) throws AnalysisEngineProcessException {
		try {

			ArrayList<Sentence> sentences = this.getAllSentences(jcas);

			for(Sentence sent : sentences) {

				int sentStart = sent.getBegin();

				Span[] tokenSpans = tokenDetect.tokenizePos(sent.getCoveredText());

				for(Span tokenSpan : tokenSpans) {
					Token token = new Token(jcas, (sentStart + tokenSpan.getStart()), (sentStart + tokenSpan.getEnd()));
					token.setSentStartPos(tokenSpan.getStart());
					token.setSentEndPos(tokenSpan.getEnd());
					token.addToIndexes();
				}

			}

		} catch (Exception e) {

			throw new AnalysisEngineProcessException(e);

		}

	}


	private void processSentences(JCas jcas) {

		Span[] sentSpans = sentDetect.sentPosDetect(jcas.getDocumentText());

		for(Span sentSpan : sentSpans) {

			Sentence sent = new Sentence(jcas, sentSpan.getStart() , sentSpan.getEnd());

			sent.addToIndexes();

		}

	}



	// util methods for setting up modelling resources

	private Linker createCorefLinker() throws ResourceInitializationException {
		try {

			URL coRefModelUrl = this.getClass().getResource("/opennlp/coref");

//			final Linker linker = new TreebankLinker(coRefModelUrl.getPath(), LinkerMode.TEST);
			final Linker linker = new DefaultLinker(coRefModelUrl.getPath(), LinkerMode.TEST);

			return linker;

		} catch (Exception e) {

			throw new ResourceInitializationException(e);

		}
	}


	private NameFinderME createPersonFinder() throws ResourceInitializationException {
		try {
			URL personModelUrl = this.getClass().getResource("/en-ner-person.bin");

			final TokenNameFinderModel personModel = new TokenNameFinderModel(personModelUrl.openStream());

			return new NameFinderME(personModel);

		} catch (IOException ioe) {
			throw new ResourceInitializationException(ioe);
		}
	}





	private Parser createTreeParser() throws ResourceInitializationException {
		try {

			URL parseModelUrl = this.getClass().getResource("/en-parser-chunking.bin");

			final ParserModel parseModel = new ParserModel(parseModelUrl.openStream());

			final Parser parser = ParserFactory.create(parseModel);

			return parser;

		} catch (Exception e) {

			throw new ResourceInitializationException(e);

		}
	}


	private POSTaggerME createPOSTagger() throws ResourceInitializationException {
		try {

			URL posModelUrl = this.getClass().getResource("/en-pos-maxent.bin");

			final POSModel posModel = new POSModel(posModelUrl.openStream());

			final POSTaggerME posDetector = new POSTaggerME(posModel);

			return posDetector;

		} catch(Exception e) {

			throw new ResourceInitializationException(e);

		}
	}


	private TokenizerME createTokenizer() throws ResourceInitializationException {
		try {

			URL tokenModelUrl = this.getClass().getResource("/en-token.bin");

			final TokenizerModel tokenModel = new TokenizerModel(tokenModelUrl.openStream());

			return new TokenizerME(tokenModel);

		} catch (IOException ioe) {
			throw new ResourceInitializationException();
		}
	}


	private SentenceDetectorME createSentenceDetector() throws ResourceInitializationException {
		try {
			URL sentenceModelUrl = this.getClass().getResource("/en-sent.bin");

			final SentenceModel sentenceModel = new SentenceModel(sentenceModelUrl.openStream());

			return new SentenceDetectorME(sentenceModel);

		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
	}



	// annotation getter methods
	private ArrayList<Person> getAllPersonEntities(JCas jcas) throws AnalysisEngineProcessException {
		try {
			AnnotationIndex<Annotation> index = jcas.getAnnotationIndex(Person.type);

			Iterator<Annotation> iter = index.iterator();

			ArrayList<Person> people = new ArrayList<Person>();

			while(iter.hasNext()) {
				Person person = (Person) iter.next();
				people.add(person);
			}
			return people;

		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
	}


	private ArrayList<Token> getAllTokens(JCas jcas) throws AnalysisEngineProcessException {
		try {
			AnnotationIndex<Annotation> index = jcas.getAnnotationIndex(Token.type);

			Iterator<Annotation> iter = index.iterator();

			ArrayList<Token> tokens = new ArrayList<Token>();

			while(iter.hasNext()) {
				Token token = (Token) iter.next();
				tokens.add(token);
			}
			return tokens;

		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
	}


	private ArrayList<Token> getIndexedTokensForSentence(JCas jcas , Sentence sentence) throws AnalysisEngineProcessException {

		try {

			AnnotationIndex<Annotation> tokenIndex = jcas.getAnnotationIndex(Token.type);

			ArrayList<Token> sentTokens = new ArrayList<Token>();
			Iterator<Annotation> tokenIter = tokenIndex.iterator();

			while(tokenIter.hasNext()) {
				Token token = (Token) tokenIter.next();

				if(token.getBegin() >= sentence.getBegin() && token.getEnd() <= sentence.getEnd() ) {
					sentTokens.add(token);
				}
			}

			return sentTokens;


		} catch (Exception e) {

			throw new AnalysisEngineProcessException(e);

		}

	}


	private ArrayList<Sentence> getAllSentences(JCas jcas) throws AnalysisEngineProcessException {
		try {
			AnnotationIndex<Annotation> index = jcas.getAnnotationIndex(Sentence.type);

			Iterator<Annotation> iter = index.iterator();

			ArrayList<Sentence> sentences = new ArrayList<Sentence>();

			while(iter.hasNext()) {
				Sentence sent = (Sentence) iter.next();
				sentences.add(sent);
			}
			return sentences;

		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
	}


	// Taken from: http://www.mail-archive.com/user@uima.apache.org/msg00893.html
	//and UIMAfit: http://code.google.com/p/uimafit/source/browse/trunk/uimaFIT/src/main/java/org/uimafit/util/FSCollectionFactory.java
	public FSList createRefMentionFSList(JCas aJCas, Collection<RefMention> aCollection) {

		if (aCollection.size() == 0) {

			return new EmptyFSList(aJCas);

		}

		NonEmptyFSList head = new NonEmptyFSList(aJCas);

		NonEmptyFSList list = head;

		Iterator<RefMention> i = aCollection.iterator();

		while (i.hasNext()) {

			head.setHead(i.next());

			if (i.hasNext()) {

				head.setTail(new NonEmptyFSList(aJCas));

				head = (NonEmptyFSList) head.getTail();
			}

			else {

				head.setTail(new EmptyFSList(aJCas));
			}

		}

		return list;
	}



}

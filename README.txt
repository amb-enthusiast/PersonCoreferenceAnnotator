PersonCoreferenceAnnotator README

This is a UIMA annotator which performs Person named entity recognition (with sentence and token detection as subtasks).

In addition, the OpenNLP coreference resolution tool is executed, using a treebank parser.  As with the CoreferenceTest, this is experimental - further work needed to understand the internals of the Coreference tool, and to then apply it to resolve mentions of named entities within the input document.

The annotator includes a type system which captures OpenNLP DiscourseEntity and Mention results.


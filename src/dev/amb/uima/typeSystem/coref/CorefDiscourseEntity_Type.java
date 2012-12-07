
/* First created by JCasGen Thu Dec 06 14:29:04 MST 2012 */
package dev.amb.uima.typeSystem.coref;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Dec 06 18:15:08 MST 2012
 * @generated */
public class CorefDiscourseEntity_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CorefDiscourseEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CorefDiscourseEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CorefDiscourseEntity(addr, CorefDiscourseEntity_Type.this);
  			   CorefDiscourseEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CorefDiscourseEntity(addr, CorefDiscourseEntity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CorefDiscourseEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
 
  /** @generated */
  final Feature casFeat_corefMentions;
  /** @generated */
  final int     casFeatCode_corefMentions;
  /** @generated */ 
  public int getCorefMentions(int addr) {
        if (featOkTst && casFeat_corefMentions == null)
      jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_corefMentions);
  }
  /** @generated */    
  public void setCorefMentions(int addr, int v) {
        if (featOkTst && casFeat_corefMentions == null)
      jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    ll_cas.ll_setRefValue(addr, casFeatCode_corefMentions, v);}
    
  
 
  /** @generated */
  final Feature casFeat_numberOfMentions;
  /** @generated */
  final int     casFeatCode_numberOfMentions;
  /** @generated */ 
  public int getNumberOfMentions(int addr) {
        if (featOkTst && casFeat_numberOfMentions == null)
      jcas.throwFeatMissing("numberOfMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    return ll_cas.ll_getIntValue(addr, casFeatCode_numberOfMentions);
  }
  /** @generated */    
  public void setNumberOfMentions(int addr, int v) {
        if (featOkTst && casFeat_numberOfMentions == null)
      jcas.throwFeatMissing("numberOfMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    ll_cas.ll_setIntValue(addr, casFeatCode_numberOfMentions, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CorefDiscourseEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_corefMentions = jcas.getRequiredFeatureDE(casType, "corefMentions", "uima.cas.FSList", featOkTst);
    casFeatCode_corefMentions  = (null == casFeat_corefMentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_corefMentions).getCode();

 
    casFeat_numberOfMentions = jcas.getRequiredFeatureDE(casType, "numberOfMentions", "uima.cas.Integer", featOkTst);
    casFeatCode_numberOfMentions  = (null == casFeat_numberOfMentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_numberOfMentions).getCode();

  }
}



    
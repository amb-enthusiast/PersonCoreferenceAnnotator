
/* First created by JCasGen Thu Dec 06 10:14:03 MST 2012 */
package dev.amb.uima.typeSystem.core;

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
public class Token_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("dev.amb.uima.typeSystem.core.Token");



  /** @generated */
  final Feature casFeat_sentStartPos;
  /** @generated */
  final int     casFeatCode_sentStartPos;
  /** @generated */ 
  public int getSentStartPos(int addr) {
        if (featOkTst && casFeat_sentStartPos == null)
      jcas.throwFeatMissing("sentStartPos", "dev.amb.uima.typeSystem.core.Token");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentStartPos);
  }
  /** @generated */    
  public void setSentStartPos(int addr, int v) {
        if (featOkTst && casFeat_sentStartPos == null)
      jcas.throwFeatMissing("sentStartPos", "dev.amb.uima.typeSystem.core.Token");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentStartPos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentEndPos;
  /** @generated */
  final int     casFeatCode_sentEndPos;
  /** @generated */ 
  public int getSentEndPos(int addr) {
        if (featOkTst && casFeat_sentEndPos == null)
      jcas.throwFeatMissing("sentEndPos", "dev.amb.uima.typeSystem.core.Token");
    return ll_cas.ll_getIntValue(addr, casFeatCode_sentEndPos);
  }
  /** @generated */    
  public void setSentEndPos(int addr, int v) {
        if (featOkTst && casFeat_sentEndPos == null)
      jcas.throwFeatMissing("sentEndPos", "dev.amb.uima.typeSystem.core.Token");
    ll_cas.ll_setIntValue(addr, casFeatCode_sentEndPos, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentStartPos = jcas.getRequiredFeatureDE(casType, "sentStartPos", "uima.cas.Integer", featOkTst);
    casFeatCode_sentStartPos  = (null == casFeat_sentStartPos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentStartPos).getCode();

 
    casFeat_sentEndPos = jcas.getRequiredFeatureDE(casType, "sentEndPos", "uima.cas.Integer", featOkTst);
    casFeatCode_sentEndPos  = (null == casFeat_sentEndPos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentEndPos).getCode();

  }
}



    
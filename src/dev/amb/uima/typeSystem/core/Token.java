

/* First created by JCasGen Thu Dec 06 10:14:03 MST 2012 */
package dev.amb.uima.typeSystem.core;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 18:15:08 MST 2012
 * XML source: /Users/AntBurke/dev/workspaces/eclipse/uimaWorkspace/PersonCoreferenceAnnotator/desc/DevTypeSystemDescriptor.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: sentStartPos

  /** getter for sentStartPos - gets 
   * @generated */
  public int getSentStartPos() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentStartPos == null)
      jcasType.jcas.throwFeatMissing("sentStartPos", "dev.amb.uima.typeSystem.core.Token");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentStartPos);}
    
  /** setter for sentStartPos - sets  
   * @generated */
  public void setSentStartPos(int v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentStartPos == null)
      jcasType.jcas.throwFeatMissing("sentStartPos", "dev.amb.uima.typeSystem.core.Token");
    jcasType.ll_cas.ll_setIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentStartPos, v);}    
   
    
  //*--------------*
  //* Feature: sentEndPos

  /** getter for sentEndPos - gets 
   * @generated */
  public int getSentEndPos() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentEndPos == null)
      jcasType.jcas.throwFeatMissing("sentEndPos", "dev.amb.uima.typeSystem.core.Token");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentEndPos);}
    
  /** setter for sentEndPos - sets  
   * @generated */
  public void setSentEndPos(int v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentEndPos == null)
      jcasType.jcas.throwFeatMissing("sentEndPos", "dev.amb.uima.typeSystem.core.Token");
    jcasType.ll_cas.ll_setIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentEndPos, v);}    
  }

    


/* First created by JCasGen Thu Dec 06 14:29:04 MST 2012 */
package dev.amb.uima.typeSystem.coref;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 18:15:08 MST 2012
 * XML source: /Users/AntBurke/dev/workspaces/eclipse/uimaWorkspace/PersonCoreferenceAnnotator/desc/DevTypeSystemDescriptor.xml
 * @generated */
public class CorefDiscourseEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CorefDiscourseEntity.class);
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
  protected CorefDiscourseEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CorefDiscourseEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CorefDiscourseEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CorefDiscourseEntity(JCas jcas, int begin, int end) {
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
  //* Feature: corefMentions

  /** getter for corefMentions - gets 
   * @generated */
  public FSList getCorefMentions() {
    if (CorefDiscourseEntity_Type.featOkTst && ((CorefDiscourseEntity_Type)jcasType).casFeat_corefMentions == null)
      jcasType.jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CorefDiscourseEntity_Type)jcasType).casFeatCode_corefMentions)));}
    
  /** setter for corefMentions - sets  
   * @generated */
  public void setCorefMentions(FSList v) {
    if (CorefDiscourseEntity_Type.featOkTst && ((CorefDiscourseEntity_Type)jcasType).casFeat_corefMentions == null)
      jcasType.jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    jcasType.ll_cas.ll_setRefValue(addr, ((CorefDiscourseEntity_Type)jcasType).casFeatCode_corefMentions, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: numberOfMentions

  /** getter for numberOfMentions - gets 
   * @generated */
  public int getNumberOfMentions() {
    if (CorefDiscourseEntity_Type.featOkTst && ((CorefDiscourseEntity_Type)jcasType).casFeat_numberOfMentions == null)
      jcasType.jcas.throwFeatMissing("numberOfMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CorefDiscourseEntity_Type)jcasType).casFeatCode_numberOfMentions);}
    
  /** setter for numberOfMentions - sets  
   * @generated */
  public void setNumberOfMentions(int v) {
    if (CorefDiscourseEntity_Type.featOkTst && ((CorefDiscourseEntity_Type)jcasType).casFeat_numberOfMentions == null)
      jcasType.jcas.throwFeatMissing("numberOfMentions", "dev.amb.uima.typeSystem.coref.CorefDiscourseEntity");
    jcasType.ll_cas.ll_setIntValue(addr, ((CorefDiscourseEntity_Type)jcasType).casFeatCode_numberOfMentions, v);}    
  }

    
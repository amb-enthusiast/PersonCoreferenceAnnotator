

/* First created by JCasGen Thu Dec 06 10:14:03 MST 2012 */
package dev.amb.uima.typeSystem.ner;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 18:15:08 MST 2012
 * XML source: /Users/AntBurke/dev/workspaces/eclipse/uimaWorkspace/PersonCoreferenceAnnotator/desc/DevTypeSystemDescriptor.xml
 * @generated */
public class Person extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Person.class);
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
  protected Person() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Person(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Person(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Person(JCas jcas, int begin, int end) {
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
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_corefMentions == null)
      jcasType.jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.ner.Person");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Person_Type)jcasType).casFeatCode_corefMentions)));}
    
  /** setter for corefMentions - sets  
   * @generated */
  public void setCorefMentions(FSList v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_corefMentions == null)
      jcasType.jcas.throwFeatMissing("corefMentions", "dev.amb.uima.typeSystem.ner.Person");
    jcasType.ll_cas.ll_setRefValue(addr, ((Person_Type)jcasType).casFeatCode_corefMentions, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    
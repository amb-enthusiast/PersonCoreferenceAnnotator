

/* First created by JCasGen Thu Dec 06 14:21:03 MST 2012 */
package dev.amb.uima.typeSystem.coref;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 06 18:15:08 MST 2012
 * XML source: /Users/AntBurke/dev/workspaces/eclipse/uimaWorkspace/PersonCoreferenceAnnotator/desc/DevTypeSystemDescriptor.xml
 * @generated */
public class RefMention extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RefMention.class);
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
  protected RefMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public RefMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public RefMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public RefMention(JCas jcas, int begin, int end) {
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
     
}

    
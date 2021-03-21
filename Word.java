import java.util.ArrayList;
import java.util.List;

/**
 * Word is a class that stores a word object that contains the word, its definitions, origin and parts of speech
 * 
 * @author teddyarasavelli
 *
 */
public class Word implements Comparable<Word>{
  
  private String word; // word name 
  private List<String> definitions; // list of definitions. 
  private String origin; // word origin. Ex: latin, greek etx
  private String types; // parts of speech. Ex: verb, noun etc
  
  /**
   * Constructor that takes in a word and initilaizes a word object with a word field and leaves the other fields as null
   * 
   * @param word - String word that the field word will be assigned to
   */
  public Word(String word) {
    this.word = word;
    this.definitions = new ArrayList<String>();
  }
  
  /**
   * Constructor that takes in a word, definitions and an origin
   * 
   * @param word - String that will be assigned to the word field
   * @param defs - List<String> that contains definitions for the word
   * @param origin - String that represents origin of the word
   */
  public Word(String word, List<String> defs, String origin) {
    this.word = word;
    this.definitions = defs;
    this.origin = origin;
  }
  
  /**
   * Constructor that takes in the word, list of definitions, origin, and types and initializes the fields to the parameters
   * 
   * @param word - String that will be the word field
   * @param defs - List<String> definitions that are associated with the word
   * @param origin - String that will contain the origin of the word
   * @param types - String that contains the parts of speech associated with the word
   */
  public Word(String word, List<String> defs, String origin, String types) {
    this.word = word;
    this.definitions = defs;
    this.origin = origin;
    this.types = types;
  }
  
  /**
   * getWord() returns the word 
   * 
   * @return String word that is contained in the field of the same name
   */
  public String getWord() {
    return this.word;
  }
  
  /**
   * getDefinitions() returns a list of definitions associated with the word
   * 
   * @return List<String> of definitions that are stored in the same field 
   */
  public List<String> getDefinitions(){
    return this.definitions;
  }
  
  /**
   * getOrigin() returns the origin associated with the word
   * 
   * @return String of the origin
   */
  public String getOrigin() {
    return this.origin;
  }
  
  /**
   * getTypes() returns the parts of speech that are associated with the word
   * 
   * @return String that contains the types that the word is 
   */
  public String getTypes() {
    return this.types;
  }
  
  /**
   * addOrigin() replaces or adds the origin that is passed in to be the new origin field associated with the word
   * 
   * @param origin String that will replace old origin or be the new origin
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }
  
  /**
   * addTypes() adds the type that is passed in to the word object
   * 
   * @param type String that contains type(s) associated with the word
   */
  public void setTypes(String types) {
    this.types = types;
  }
  
  /**
   * addDefinition() adds a definition that is passed in to the existing list of definitions 
   * `
   * @param def - String def that will be added if the definition list does not already contain the definition
   */
  public void addDefinition(String def) {
    if(!definitions.contains(def))
      definitions.add(def);
  }
  
  /**
   * toString() returns a string representation of the word that contains its word, definitions, types and origin
   * 
   * @return stringRep - String that contains contents of word
   */
  public String toString() {
    String stringRep = this.word + ": ";
    
    for(String def : definitions)
      stringRep += def + " | ";
    
    stringRep += "Types: " + this.types + " ";
    
    stringRep += "Origin: " +  origin;
    return stringRep;
  }

  @Override
  /**
   * compareTo() compares this word to another one by the word field using the comapreTo for strings
   * 
   * @param o - Word that is being compared to
   * @return int - 0 if words are the same, 1 if greater (lexigrophical) and -1 if less than 
   */
  public int compareTo(Word word) {
    
    return this.word.compareTo(word.word);
    
  }
  
  /**
   * equals() returns true if the object is equal to the current Word object
   * 
   * @param o - Object that is being compared to
   * @return boolean - true if object is equal to the word, false otherwise
   * 
   */
  public boolean equals(Object o) {
    if(o instanceof Word) {
      return ((Word) o).word.equals(this.word);
    }
    
    return false;
  }
  
  /**
   * hashCode() returns a hashCode of the object computed through the string hashCode method
   * 
   * @return int - returns an int hashcode of the word field (needed to use contains)
   */
  public int hashCode() {
    return this.word.hashCode();
  }
  
  

}

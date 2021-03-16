import java.util.List;

public class Word implements Comparable<Word>{
  
  private String word;
  private List<String> definitions;
  private String origin;
  
  
  
  public Word(String word, List<String> defs, String origin) {
    this.word = word;
    this.definitions = defs;
    this.origin = origin;
  }
  
  public String getWord() {
    return this.word;
  }
  
  public List<String> getDefinitions(){
    return this.definitions;
  }
  
  public String getOrigin() {
    return this.origin;
  }
  
  public String toString() {
    String stringRep = this.word + ": ";
    
    for(String def : definitions)
      stringRep += def + "\n";
    
    stringRep += origin;
    return stringRep;
  }
  
  public void addDefinition(String def) {
    if(!definitions.contains(def))
      definitions.add(def);
  }

  @Override
  public int compareTo(Word o) {
    
    return this.word.compareTo(o.getWord());
    
  }
  
  

}

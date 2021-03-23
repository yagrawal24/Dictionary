import java.util.List;

//--== CS400 File Header Information ==--
//Name: Anthony Reis
//Email: atreis@wisc.edu
//Team: Red
//Role: Backend Developer
//TA: Mu Kai
//Lecturer: Gary Dahl
//Notes to Grader: none

public interface BackendInterface {
  public boolean insert(String word, List<String> defs, String origin);
  public Word get(String word);
  public Word get(int random);
  public int size();
  public boolean isEmpty();
  public List<String> getSuggestions(String word);
}
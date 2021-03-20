import java.util.List;

public interface BackendInterface {
  public void insert(String word, List<String> defs, String origin);
  public Word get(String word);
  public void size();
  public boolean isEmpty();
  public List<String> getSuggestions(String word);
}

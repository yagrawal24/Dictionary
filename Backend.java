import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface {
  private RedBlackTree<Word> dictionary;
  
  public Backend() {
    dictionary = new RedBlackTree<Word>();
    try {
      List<Word> words = DictionaryDataReader.readFile("Oxford_English_Dictionary.txt");
      for (int i = 0; i < words.size(); i ++) {
        dictionary.insert(words.get(i));
      }
    } catch (DataFormatException | IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void insert(String word, List<String> defs, String origin) {
    Word newWord = new Word(word, defs, origin);
    dictionary.insert(newWord);
  }

  @Override
  public Word get(String word) { 
    if (word == null)
      return null;
    
    RedBlackTree.Node<Word> currWord = dictionary.root;
    
    while (currWord != null) {
      if (word.compareTo(currWord.data.getWord()) < 0)
        currWord = currWord.leftChild;
      else if (word.compareTo(currWord.data.getWord()) > 0)
        currWord = currWord.rightChild;
      else
        return currWord.data;
    }
    return null;
  }
  

  @Override
  public void size() {  
    dictionary.size();
  }

  @Override
  public boolean isEmpty() {
    if (dictionary.size() == 0)
      return true;
    return false;
  }

  @Override
  public List<String> getSuggestions(String word) {
    String wordOrig = word;
    String currWord = null;
    
    List<String> suggestions = new ArrayList<String>();
    String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    
    // Append a letter to original word and search
    for (int i = 0; i < alphabet.length; i ++) {
      word += alphabet[i];

      if (get(word) != null) {
        suggestions.add(get(word).getWord());
      }
      word = wordOrig;
    }
    
    currWord = wordOrig.substring(0, wordOrig.length() - 1);

    // Remove a letter from original word and then append a letter and search
    for (int i = 0; i < alphabet.length; i ++) {
      word += alphabet[i];
      
      if (get(word) != null) {
        suggestions.add(get(word).getWord());
      }
      word = currWord;
    }
    
    currWord = wordOrig.substring(0, wordOrig.length() - 2);

    // Remove two letters from original word and then append a letter and search
    for (int i = 0; i < alphabet.length; i ++) {
      word += alphabet[i];
      
      if (get(word) != null) {
        suggestions.add(get(word).getWord());
      }
      word = currWord;
    }
    
    return suggestions;
  }
}

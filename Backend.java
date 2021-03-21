import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

//--== CS400 File Header Information ==--
//Name: Anthony Reis
//Email: atreis@wisc.edu
//Team: Red
//Role: Backend Developer
//TA: Mu Kai
//Lecturer: Gary Dahl
//Notes to Grader: none

/**
 * This class implements the backend of the dictionary for project 2. These methods allow 
 * for the frontend to interact with the backend by allowing the front end to access and 
 * contribute to the dictionary contents found in the red black tree.
 * 
 * @author reistonyt
 */

public class Backend implements BackendInterface {
  private RedBlackTree<Word> dictionary;
  List<Word> wordsList;
  
  /**
   * Backend constructor. Instantiates the data wrangler class, which returns a list of word
   * objects from the dictionary file. Then adds those word objects to the red black tree.
   */
  public Backend() {
    dictionary = new RedBlackTree<Word>();
    try {
      wordsList = DictionaryDataReader.readFile("Oxford_English_Dictionary.txt");
      for (int i = 0; i < wordsList.size(); i ++) {
        dictionary.insert(wordsList.get(i));
      }
    } catch (DataFormatException | IOException e) {
      e.printStackTrace();
    }
  }
  
  
  /**
   * Inserts a new word object into the red black tree as specified by the front end
   * 
   * @param word - new word
   * @param defs - list of definitions as there may be more than one definition
   *               for a word
   * @param origin - the origin of the word 
   */
  @Override
  public void insert(String word, List<String> defs, String origin) {
    Word newWord = new Word(word.toLowerCase(), defs, origin);
    dictionary.insert(newWord);
  }

  /**
   * Gets a word object from the red black tree as specified by the front end
   * 
   * @param word - word to find
   * 
   * @return - word object if word was found, else null
   */
  @Override
  public Word get(String word) { 
    if (word == null)
      return null;
    
    RedBlackTree.Node<Word> currWord = dictionary.root;
    
    while (currWord != null) {
      if (word.toLowerCase().compareTo(currWord.data.getWord()) < 0)
        currWord = currWord.leftChild;
      else if (word.toLowerCase().compareTo(currWord.data.getWord()) > 0)
        currWord = currWord.rightChild;
      else
        return currWord.data;
    }
    return null;
  }
  
  /**
   * Gets a random word specified by a random integer passed from front end
   * 
   * @param random - random integer
   * 
   * @return - random word
   */
  public Word get(int random) {
    if (random < 0 || random > wordsList.size())
       return null;
    return wordsList.get(random);
  }

  /**
   * Displays current size of the red black tree
   */
  @Override
  public int size() {  
    return dictionary.size();
  }

  /**
   * Returns true if tree is not empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (dictionary.size() == 0)
      return true;
    return false;
  }

  /**
   * If a word cannot be found in the red black tree as requested by the front end,
   * an attempt will be made to create a list of suggestions that the front end (the user)
   * is looking for.
   * 
   * The methods used to generate suggestions are:
   *    1) Appending (a-z) to the end of the original word and searching the dictionary
   *    2) Remove the last letter from the original word and appending (a-z) and then searching
   *    3) Remove the last 2 letters from the original word and appending (a-z) and then searching
   *    
   *    Example: word is "bac"
   *        1) Using strategy 1, this algorithm would search bac(a), then bac(b), then bac(c)
   *           and so on for the entirety of the alphabet.
   *        2) Using strategy 2, this algorithm would first remove "c" from bac so the new word would
   *           be "ba".  Then it would search ba(a), then ba(b), then ba(c) and so on for the entirety
   *           of the alphabet.
   * 
   * @param - word to get suggestions for
   * 
   * @return - list of words 
   */
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

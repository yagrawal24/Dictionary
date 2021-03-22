import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//--== CS400 File Header Information ==--
//Name: Anthony Reis
//Email: atreis@wisc.edu
//Team: Red
//Role: Backend Developer
//TA: Mu Kai
//Lecturer: Gary Dahl
//Notes to Grader: none

public class TestBackend extends Backend {
    /**
   * This test ensures that the backend instantiates correctly and that there are
   * the correct number of words in the red black tree as in the dictionary text file.
   * There are duplicate words in the dictionary test file so this test eliminates the
   * duplicates and then compares the size of the list to the size of the red black tree
   * (which should not have duplicates).
   * @return true if passes, false otherwise
   */
  @Test
  public void backendInstantiation() {
    Backend backend = new Backend();
    
    List<Word> uniqueList = new ArrayList<Word>();
    
    for (int i = 0; i < backend.wordsList.size(); i ++) {
      if (!uniqueList.contains(backend.wordsList.get(i))) {
        uniqueList.add(backend.wordsList.get(i));
      }
    }
    
    assertEquals(backend.dictionary.size(), uniqueList.size());
  }
  
    /**
   * This test ensures that words are correctly inserted into the red black tree.  That is, 
   * once a word is inserted, this test checks that whether or not the word is able to be found
   * in the tree.
   * @return true if passes, false otherwise
   */
  @Test
  public void testInsert() {
    Backend backend = new Backend();
    
    // Sub Test 1
    List<String> wordDefs = new ArrayList<String>();
    wordDefs.add("this is a test");
    backend.insert("test word", wordDefs, "The origin of this word is me");
    
    assertEquals(backend.get("test word").toString(), "test word: this is a test | Types: null Origin: The origin of this word is me");
    
    // Sub Test 2
    List<String> wordDefs1 = new ArrayList<String>();
    wordDefs1.add("this is another test");
    backend.insert("z word1", wordDefs1, "The origin of this word is me");
    
    assertEquals(backend.get("z word1").toString(), "z word1: this is another test | Types: null Origin: The origin of this word is me");
    
    // Sub Test 3
    List<String> wordDefs2 = new ArrayList<String>();
    wordDefs2.add("this is a third test");
    backend.insert("a word2", wordDefs2, "The origin of this word is me");
    
    assertEquals(backend.get("a word2").toString(), "a word2: this is a third test | Types: null Origin: The origin of this word is me");
  }
   
    /**
   * This test ensures that the word of the day returns a random word based on a random input.
   * @return true if passes, false otherwise
   */
  @Test
  public void testRandomWordOfTheDay() {
    Backend backend = new Backend();
    
    Random rand = new Random();
    int random = rand.nextInt(backend.wordsList.size() - 1);
    
    assertNotEquals(backend.get(random), null);
  }
  
    /**
   * This test ensures that getSuggestions() works correctly.
   * @return true if passes, false otherwise
   */
  @Test
  public void testGetSuggestions() {
    Backend backend = new Backend();
    
    List<String> suggestions = backend.getSuggestions("Abandone");
    assertEquals(suggestions.toString(), "[abandoned, abandon]");
    
    suggestions = backend.getSuggestions("Journeyma");
    assertEquals(suggestions.toString(), "[journeyman]");

  }
  
    /**
   * This test ensures that the get function works correctly by searching
   * up random words throughout the dictionary.
   * @return true if passes, false otherwise
   */
  @Test
  public void testGet() {
    Backend backend = new Backend();
    
    assertNotEquals(backend.get("good").toString(), null);
    assertNotEquals(backend.get("Abracadabra").toString(), null);
    assertNotEquals(backend.get("Recommence").toString(), null);
    assertNotEquals(backend.get("Transponder").toString(), null);
    assertNotEquals(backend.get("Zoological").toString(), null);
  }
}
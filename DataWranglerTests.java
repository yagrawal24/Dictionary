// --== CS400 File Header Information ==--
// Name: Teddy Arasavelli
// Email: arasavelli@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit test class that verifies functionality of the DictionaryDataReader class and the Word class
 * 
 * @author teddyarasavelli
 *
 */
public class DataWranglerTests {

  /*
   * Following method tests whether the DataReader class's readData() method will return a list of
   * word objects of all the words in the dictionary file
   *
   */
  @Test
  public void testDataReader() {
    // since the the readFile method will be static, there is no need to insantiate
    // DictionaryDataReader

    try {
      List<Word> list = DictionaryDataReader.readFile("smallDictionary.txt");



      String[] words = new String[] {"a-", "aa", "aardvark", "ab", "aback", "abacus", "abaft"};

      List<Word> expectedList = new ArrayList<Word>();

      for (String word : words) {
        expectedList.add(new Word(word));
      }
      
      assertEquals(expectedList.size(), list.size());
      
    } catch (Exception e) {
      fail();
    }

  }

  /*
   * following method tests whether or not the DictionaryDataReader class's readData() method will
   * throw the correct exceptions in the correct cases.
   *
   */
  @Test
  public void testDataReaderExceptions() {

    boolean fileNotFoundThrown = false;
    try {
      List<Word> testFileNotFound = DictionaryDataReader.readFile("smallDictionary.txts"); // will
    
    } catch (Exception e) {
      fileNotFoundThrown = true;
    }

    assertEquals(fileNotFoundThrown, true);


  }

  /*
   * Following method tests whether or not words with multiple definitions and types actually have
   * all the definitions that are in the dictionary file
   *
   */
  @Test
  public void testMultipleDefintions() {
    // will fail all the tests initially

    List<Word> list;
    try {
      list = DictionaryDataReader.readFile("smallDictionary.txt");
      
      assertEquals(list.get(0).getDefinitions().size(), 1);
      
      assertEquals(list.get(1).getDefinitions().size(), 3);
      assertEquals(list.get(2).getDefinitions().size(), 1);
      assertEquals(list.get(3).getDefinitions().size(), 1);
      assertEquals(list.get(4).getDefinitions().size(), 1);
      assertEquals(list.get(5).getDefinitions().size(), 2);
      assertEquals(list.get(6).getDefinitions().size(), 1);
      
    } catch (Exception e) {
      fail();
    }
  }


  /*
   * Following method tests whether or not the toString() method returns a correcly formatted string
   * representation of each word
   *
   */
  @Test
  public void testToString() {
    // will fail all the tests initially
    String expected = "a: 1 | 2 | Types: d Origin: c"; // will create expected
                                                                         // words when the classes
                                                                         // have been made
    String expected2 = "e: 1 | 2 | Types: g Origin: f";
    String expected3 = "h: 1 | 2 | Types: j Origin: i";
    
    List<String> testDefs = new ArrayList<String>();
    testDefs.add("1");
    testDefs.add("2");

    Word a = new Word("a", testDefs, "c", "d");
    Word b = new Word("e", testDefs, "f", "g");
    Word c = new Word("h", testDefs, "i", "j");
    

    assertEquals(expected, a.toString());
    assertEquals(expected2, b.toString());
    assertEquals(expected3, c.toString());
  }

  /*
   * Following method tests whether or not the getWord() and getOrigin() methods function properly
   * and return the correct output.
   *
   */
  @Test
  public void testGetters() {
    
    List<String> testDefs = new ArrayList<String>();
    testDefs.add("1");
    testDefs.add("2");

    Word a = new Word("a", testDefs, "greek", "d");
    Word b = new Word("e", testDefs, "latin", "g");
    Word c = new Word("h", testDefs, "i", "j");
    
    // testing getDefinitions() method of Word
    
    assertEquals(testDefs, a.getDefinitions());
    assertEquals(testDefs, b.getDefinitions());
    assertEquals(testDefs, c.getDefinitions());
    
    // testing getTypes() method of Word
    
    assertEquals("d", a.getTypes());
    assertEquals("g", b.getTypes());
    assertEquals("j", c.getTypes());

    // testing the getOrigin method of Word

    assertEquals("greek", a.getOrigin());
    assertEquals("latin", b.getOrigin());

    // testing the getWord method of Word

    assertEquals("a", a.getWord());
    assertEquals("e", b.getWord());
  }
  /**
   * Following method tests the functionality of the setters in the Word class (addDefinition, setOrigin, setTypes)
   * 
   */
  @Test
  public void testSetters() {
    
    List<String> testDefs = new ArrayList<String>();
    testDefs.add("1");
    testDefs.add("2");

    Word a = new Word("a", testDefs, "greek", "d");
    Word b = new Word("e", testDefs, "latin", "g");
    Word c = new Word("h", testDefs, "i", "j");
    
    // testing addDefinition() method
    
   a.addDefinition("3");
   b.addDefinition("3");
   c.addDefinition("3");
   
   testDefs.add("3");
   
   assertEquals(testDefs, a.getDefinitions());
   assertEquals(testDefs, b.getDefinitions());
   assertEquals(testDefs, c.getDefinitions());
   
   // testing setOrigin method of Word
   
   a.setOrigin("X");
   b.setOrigin("Y");
   c.setOrigin("Z");
   
   assertEquals("X", a.getOrigin());
   assertEquals("Y", b.getOrigin());
   assertEquals("Z", c.getOrigin());
   
   // testing the setTypes method of Word
   
   a.setTypes("noun verb");
   b.setTypes("noun");
   c.setTypes("adjective");
   
   assertEquals("noun verb", a.getTypes());
   assertEquals("noun", b.getTypes());
   assertEquals("adjective", c.getTypes());
   
   

    
  }
}

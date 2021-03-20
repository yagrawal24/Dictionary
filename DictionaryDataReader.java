import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class DictionaryDataReader {

  /**
   * readFile() static method that takes in a dictionary file and returns a list of Word objects
   * that contain their definitions and origin
   * 
   * 
   * @param fileName - dictionary file that will be read in
   * @return List<Word> - word list that contains all the words in the dictionary and their
   *         respective definitions and origin
   * 
   * @throws FileNotFoundException - thrown when the file name does not point to a legal file
   * @throws DataFormatException   - thrown when the data in the file does not match up with the
   *                               specific format needed to read in the word
   * @throws IOException           - thrown when issues arise when parsing through data
   */
  public static List<Word> readFile(String fileName)
      throws FileNotFoundException, DataFormatException, IOException {



    FileReader dictionary = new FileReader(fileName);
    BufferedReader parseDictionary = new BufferedReader(dictionary);

    String[] allTypes = {"adv.", "Adv.", "—adv.", "—Adv.", "v.", "V.", "—v.", "—V.", "adj.", "Adj.",
        "—adj.", "—Adj.", "prefix", "Prefix", "suffix", "Suffix", "n.", "N.", "—n.", "—N>", "n.pl.",
        "N.pl", "predic.", "Predic.", "—predic.", "gram.", "Gram.", "comb.", "Comb.", "colloq.", "Collo1.",
        "hist.", "Hist.", "esp.", "Esp.", "—prep","—prep.","—Prep", "naut.", "Naut.", "aeron.", "Aeron.",
        "Pron.", "pron.", "abbr.", "Abbr.", "var.", "Var.", "contr.", "Contr.", "attrib.",
        "Attrib.", "int.", "—int." , "Int.", "slang", "Slang", "offens.", "Offens.", "pawn.", "Pawn.",
        "symb.", "Symb.", "&"};
    
    

    List<String> allParts = Arrays.asList(allTypes);
    
    List<Word> wordList = new ArrayList<Word>();

    String line = "";
    while ((line = parseDictionary.readLine()) != null) {
      if (!line.isBlank()) {
        Scanner parseLine = new Scanner(line);
        
        String[] lineContent = line.split("\\s+");

        // first word will always be atleast the first word of the word
        String word = lineContent[0];
        
        if(word.length() <= 1)
          continue;
        
        String partsOfSpeech = "";

        // do a manual check to see if the second word is also part of the word
        String wordOrPOF = lineContent[1];

        if (!allParts.contains(wordOrPOF)) {
          word += " " + wordOrPOF;
        } else if (allParts.contains(wordOrPOF)) {
          partsOfSpeech += wordOrPOF;
        }
        
        


        List<String> definitions = new ArrayList<String>();
        boolean originMode = false; // if false, reading in a definition, if true, reading in an
                                    // origin.
        String origin = "";
        String definitionToAdd = "";
        for(int j = 2; j < lineContent.length; j++) {
          String parseWord = lineContent[j];

              

          if (allParts.contains(parseWord)) {
            partsOfSpeech += " " + parseWord;
          } else {       // At this point, the rest of the line should be definitions and then the origin
            if (!originMode) {
              if (isNumber(parseWord)) {
                if (definitionToAdd.length() != 0 && Integer.parseInt(parseWord) != 1) { // add the definition and then reset the
                                                     // definition to be blank string for the
                                                     // potential next definition
                  definitions.add(definitionToAdd);
                  definitionToAdd = "";
                }
              } else if (parseWord.contains("[")) {
                definitions.add(definitionToAdd);
                originMode = true;
                origin += " " + parseWord;

              }
              else {
                definitionToAdd += " " + parseWord;
              }

            }
            else { // now parsing through the origin
              origin += " " + parseWord;
            }
          }

        }
        
        
        // if still in definition mode, add the definition to the list
        
        if(!originMode)
          definitions.add(definitionToAdd);
        
        // if the line has a usage tag, add this word as another definition of the previous word
        if(word.equalsIgnoreCase("-Usage")) {
          wordList.get(wordList.size() - 1).addDefinition("Usage: " + definitionToAdd);
        }
        else {
          Word toAdd = new Word(word, definitions, origin);
          wordList.add(toAdd);
        }

      }
    }

    parseDictionary.close();
    return wordList;
  }

  /**
   * isNumber() private helper to determine if the given string is a number of not (used when words
   * contain multiple definitions)
   * 
   * @param str - String to est
   * @return boolean - true if string is a number, false oherwise.
   */
  private static boolean isNumber(String str) {
    try {
      int num = Integer.parseInt(str);
    } catch (Exception e) {
      return false;
    }

    return true;
  }
}


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

  public static List<Word> readFile(String fileName)
      throws FileNotFoundException, DataFormatException, IOException {

    FileReader dictionary = new FileReader(fileName);
    BufferedReader parseDictionary = new BufferedReader(dictionary);

    List<Word> wordList = new ArrayList<Word>();

    String line = "";
    
    while ((line = parseDictionary.readLine()) != null) {



      if (!line.isBlank()) {
        String[] wordContent = line.split("\\s+");
        if (wordContent.length > 1) { // make sure that we are not reading a word that is simply an
                                      // A, B, C, D etc

          String word = "";
          List<String> definitions = new ArrayList<String>();
          String origin = "";
          List<String> partsOfSpeech = new ArrayList<String>();

          int startIndex = 1;
          word = wordContent[0];
          if (!wordContent[1].contains(".")) {
            word += " " + wordContent[1];
            startIndex = 2;
          }

          String currentAspect = "d"; // reading in a definition when "d", reading in an origin when
                                      // "o"

          String currentDef = "";

          if (!(wordContent[1].equalsIgnoreCase("prefix")
              || wordContent[1].equalsIgnoreCase("suffix"))) {
            for (int i = startIndex; i < wordContent.length; i++) {
              String parseWord = wordContent[i];


                if (currentAspect.equals("d")) {

                  if (isNumber(parseWord)) {
                    if (currentDef.length() != 0 && Integer.parseInt(parseWord) != 1) {
                      currentDef = currentDef.trim();
                      definitions.add(currentDef);
                      currentDef = "";
                    }
                  } else if (parseWord.contains("[")) {
                    currentDef.trim();
                    definitions.add(currentDef);
                    currentAspect = "o";
                    origin += parseWord + " ";
                  } else
                    currentDef += parseWord + " ";

                } else if (currentAspect.equals("o")) {
                  origin += parseWord + " ";
                }
              


            }

            origin = origin.trim();

            currentDef = currentDef.trim();
            if (currentAspect.equals("d")) {
              definitions.add(currentDef);
            }



            Word toAdd = new Word(word, definitions, origin);
            wordList.add(toAdd);
          }

        }
      }



    }

    parseDictionary.close();
    return wordList;

  }

  private static boolean isNumber(String str) {
    try {
      int num = Integer.parseInt(str);
    } catch (Exception e) {
      return false;
    }

    return true;
  }
}

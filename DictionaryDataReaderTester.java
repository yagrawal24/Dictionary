import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public class DictionaryDataReaderTester {
  
  public static void main(String[] args) throws FileNotFoundException, DataFormatException, IOException {
    List<Word> allWords = DictionaryDataReader.readFile("/Users/teddyarasavelli/eclipse-workspace/Red Black Tree Project 2 CS400/src/Dictionary.txt");
    
    
    int i = 0;
    for(Word word : allWords) {
      if(i < 100 && word.getWord().length() > 1) {
         System.out.println(word.getWord() + " " + word.getDefinitions().size());
      }
      i++;
    }
  }

}

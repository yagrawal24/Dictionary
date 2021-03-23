import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontEndDeveloperTests {

    @Test
    /**
     * Tests command to exit program when 'e' or 'x' is typed
     */
    public void testCommandExit() {
        Frontend.switchModes("e");
        Frontend.enterMode();

        if(Frontend.currMode != Frontend.Mode.EXIT || Frontend.runLoop)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly adding word to dictionary while in addition mode
     */
    public void testCommandAddition() {
        Frontend.switchModes("a");

        if(Frontend.currMode != Frontend.Mode.ADDITION)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests ability to give definition of word while in search mode
     */
    public void testCommandSearch() {
        Frontend.switchModes("s");

        if(Frontend.currMode != Frontend.Mode.SEARCH)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly pulling a random word from dictionary
     */
    public void testCommandRandom() {
        Frontend.switchModes("r");

        if(Frontend.currMode != Frontend.Mode.RANDOM)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly adding word to dictionary while in search mode
     */
    public void testAdditionFromSearch() {
        Frontend.testPrintWord("abcdef", 'y');
        if(Frontend.currMode != Frontend.Mode.ADDITION)
            fail("Failed to switch modes");
    }
}
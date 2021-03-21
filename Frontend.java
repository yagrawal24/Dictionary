import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Frontend {
    // Mode Enums

    enum Mode {
        ADDITION,
        SEARCH,
        RANDOM,
        EXIT
    }

    // Static Class Members

    static Backend backend = new Backend();
    static boolean runLoop = true;
    static Mode currMode;
    static Scanner scnr = new Scanner(System.in);

    // Static Class Methods

    static void promptMode() {
        // Prompt
        System.out.println("\nWhat mode would you like to enter?");

        // Print Out Options Based on First Char
        Mode[] allModes = Mode.values();
        for(int i = 0; i < allModes.length; i++) {
            System.out.println("["+allModes[i].toString().charAt(0)+"] - " + allModes[i]);
        }

        // Set Static Mode to Valid Mode
        char charMode = scnr.next(".").charAt(0);
        currMode = getModeFromChar(charMode);
    }

    static Mode getModeFromChar(char inputMode) {
        // Char to Mode Switch Statement
        switch(Character.toLowerCase(inputMode)) {
            case 'a':
                return Mode.ADDITION;
            case 's':
                return Mode.SEARCH;
            case 'n':
            case 'r':
                return Mode.RANDOM;
            case 'x':
            case 'e':
                return Mode.EXIT;
            default:
                System.out.println("Invalid character inputted: '"+inputMode+"'");
                if(currMode == null)
                    promptMode();
                return null;
        }
    }

    static boolean switchModes(String input) {
        // Backup Current Mode
        Mode saveMode = currMode;

        // Switch Modes
        char cMode = input.charAt(0);
        currMode = getModeFromChar(cMode);

        // If same mode, return true
        if(currMode == saveMode)
            return true;

        // If success, print message and return true
        if(currMode != null) {
            System.out.println("Switched to '"+currMode+"' mode");
            System.out.println("-".repeat(20));
            return true;
        }

        // If fail, restore old mode; return false
        currMode = saveMode;
        return false;
    }

    static void printWord(String word) {
        try {
            if(backend.get(word) != null && !backend.get(word).getWord().isEmpty()) { // Found Word
                List<String> definitions = backend.get(word).getDefinitions();
                String origin = backend.get(word).getOrigin();

                System.out.println(word.toLowerCase() + " (" + backend.get(word).getOrigin() + "):");
                for(int i = 0; i < definitions.size(); i++) {
                    System.out.println("\t["+i+"] " + definitions.get(i));
                }
            }
        } catch (NoSuchElementException err) {
            System.out.println(err.getMessage());
        }
    }

    static void printWord(int word) {
        try {
            if(backend.get(word) != null && !backend.get(word).getWord().isEmpty()) { // Found Word
                List<String> definitions = backend.get(word).getDefinitions();
                String origin = backend.get(word).getOrigin();

                System.out.println(backend.get(word).getWord().toLowerCase()+backend.get(word).getOrigin() + ":");
                for(int i = 0; i < definitions.size(); i++) {
                    System.out.println("\t["+i+"] " + definitions.get(i));
                }
            }
        } catch (NoSuchElementException err) {
            System.out.println(err.getMessage());
        }
    }

    static void additionMode() {
        System.out.println("Type a word you wish to add to your dictionary (Must exceed one character): ");
        System.out.println("(Alternatively, enter a char [(S)earch/(R)andom/(E)xit] to switch modes)");

        // Get Input
        String word = scnr.next();
        List<String> definitions = new ArrayList<String>();
        boolean continuePrompt = true;
        String origin;

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }

        // Get Definition(s) & Origin
        System.out.println("Type the definition of '" + word + "':");
        if(scnr.hasNextLine()) scnr.nextLine(); // Clean Next Line
        definitions.add(scnr.nextLine());
        do {
            System.out.println("Would you like to add another definition (y/n): ");
            char c = scnr.next(".").charAt(0);

            if(c == 'y') {
                System.out.println("Type the definition of '" + word + "':");
                scnr.nextLine(); // Eat newline
                definitions.add(scnr.nextLine());
            } else if(c == 'n') {
                continuePrompt = false;
            } else {
                continue;
            }
        } while (continuePrompt);

        System.out.println("Type the origin of '" + word + "':");
        if(scnr.hasNextLine()) scnr.nextLine(); // Clean Next Line
        origin = scnr.nextLine();

        // Add Word to Dictionary
        backend.insert(word, definitions, origin);
        System.out.println("'"+word+"' was successfully added to your dictionary!\n");
    }

    static void searchMode() {
        System.out.println("Type a word you wish to search from your dictionary: ");
        System.out.println("(Alternatively, enter a char [(A)ddition/(R)andom/(E)xit] to switch modes)");

        // Get Input
        if(scnr.hasNextLine()) scnr.nextLine(); // Clean Next Line
        String word = scnr.nextLine();
        System.out.println(word);

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }

        // Search Word from Dictionary
        printWord(word);
    }

    static void randomMode() {
        // Search Word from Dictionary
        printWord( (new Random()).nextInt(backend.size()) );
        System.out.println("Enter a char [(A)ddition/(S)earch/(R)andom/(E)xit] to switch modes");

        // Get Input
        String word = scnr.next();

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }
    }

    static void exitMode() {
        System.out.println("Exiting program!");

        scnr.close();
        runLoop = false;
    }

    static void enterMode() {
        System.out.println("Entered '" + currMode + "' mode");
        System.out.println("-".repeat(20));
        // Main Program Loop
        while(runLoop == true) {
            // Mode Switch Statement
            switch(currMode) {
                case ADDITION:
                    additionMode();
                    break;
                case SEARCH:
                    searchMode();
                    break;
                case RANDOM:
                    randomMode();
                    break;
                case EXIT:
                    exitMode();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Intro Text
        System.out.println("Welcome to the dictionary program\n");
        System.out.print("Word of the day: ");
        printWord( (new Random()).nextInt(backend.size()) );

        // Method Calls
        promptMode();
        enterMode();
    }
}
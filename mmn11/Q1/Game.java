package Q1;

import java.util.Scanner;

/**
 * Object to represent the word guessing game that was described in mmn11
 * Has methods that allow playing a single round, or multiple rounds.
 */
public class Game {    private String unchosenLetters;
    private ChosenWord chosenWord;

    /**
     * Plays multiple rounds of the game, pausing to ask if the player wants to continue for a new round
     */
    public void play() {
        do {
            playRound();
        } while (checkShouldContinue());
    }

    /**
     * Plays a single round of the game - choosing a new word from the collection and allowing the player
     * to guess different characters until he can uncover the entire word
     */
    public void playRound() {
        System.out.println("Starting new round!");
        WordList wordList = new WordList();
        String randomWord = wordList.pickRandomWord();
        chosenWord = new ChosenWord(randomWord);
        unchosenLetters = "abcdefghijklmnopqrstuvwxyz";
        while (!chosenWord.isFullyDiscovered()) {
            playGuess();
        }
        System.out.println("You win!");
    }

    /**
     * Plays a single guess of a letter in the chosen word
     */
    private void playGuess() {
        printGameState();
        System.out.print("Guess a letter: ");
        char pickedCharacter = readInputCharacter();
        if (unchosenLetters.indexOf(pickedCharacter) == -1) {
            System.out.println("Letter was already picked, try again.");
            return;
        }
        removeChosenLetter(pickedCharacter);
        if (chosenWord.guessCharacter(pickedCharacter)) {
            System.out.println("Good guess! Uncovering letter.");
        } else {
            System.out.println("Bad guess! Better luck next character.");
        }
    }

    /**
     * Helper method to remove a letter from the unchosenLetters string
     * @param chosenLetter - Letter to remove from the unchosenLetters string
     */
    private void removeChosenLetter(char chosenLetter) {
        int letterIndex = unchosenLetters.indexOf(chosenLetter);
        unchosenLetters = unchosenLetters.substring(0, letterIndex) + unchosenLetters.substring(letterIndex + 1);
    }

    /**
     * Prints the current game state - what unchosen letters are left and the current
     * discovery state of the word
     */
    private void printGameState() {
        System.out.println("---");
        System.out.printf("Unchosen letters - %s\n", unchosenLetters);
        System.out.printf("Word - %s\n", chosenWord.toString());
        System.out.println("---");
    }

    /**
     * Prompt the user to choose if they would like to continue for another round or not
     * @return - true if the user chose to continue, false if they want to quit.
     */
    private boolean checkShouldContinue() {
        char choice;
        while (true) {
            System.out.print("Press Y for another round, or N to stop playing: ");
            choice = Character.toLowerCase(readInputCharacter());
            if (choice == 'y') return true;
            if (choice == 'n') return false;
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Read a single character from the standard input.
     * If the user inserts more than one character, the method prompt him to try again
     * @return - The character that was read from the standard input.
     */
    private char readInputCharacter() {
        boolean isCharacter = false;
        String readString = "";
        while (!isCharacter) {
            readString = new Scanner(System.in).nextLine();
            if (readString.length() != 1) {
                System.out.print("Invalid character, try again: ");
            } else {
                isCharacter = true;
            }
        }
        return readString.charAt(0);
    }
}

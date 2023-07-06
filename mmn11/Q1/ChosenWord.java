package Q1;

import java.util.HashSet;

/**
 * Represents the word that was chosen from the words list,
 * and the current state of the guessing of it
 */
public class ChosenWord {
    private String word;
    private HashSet<Character> charactersInWord;
    private HashSet<Character> discoveredCharacters;

    public ChosenWord(String chosenWord) {
        word = chosenWord;
        charactersInWord = new HashSet<Character>();
        for (char ch : word.toCharArray()) {
            charactersInWord.add(ch);
        }
        discoveredCharacters = new HashSet<Character>();
    }

    /**
     * Format the chosen word according to the game display format.
     * @return - The chosen word from the words list, where every letter that is yet to be discovered is replaced by '_'
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (discoveredCharacters.contains(ch)) {
                str.append(ch);
            } else {
                str.append('_');
            }
        }
        return str.toString();
    }

    /**
     * Determine if the chosen word was fully discovered
     * @return - true if the word was fully discovered, false if it wasn't
     */
    public boolean isFullyDiscovered() {
        return charactersInWord.size() == discoveredCharacters.size();
    }

    /**
     * Check if a certain letter is part of the chosen word.
     * If it is part of the word, the state updates to reflect that this letter was already discovered.
     * @param ch - The letter to check against the word
     * @return - true if the letter is part of the word, false if it isn't
     */
    public boolean guessCharacter(char ch) {
        if (discoveredCharacters.contains(ch)) return false;
        if (charactersInWord.contains(ch)) {
            discoveredCharacters.add(ch);
            return true;
        }
        return false;
    }
}

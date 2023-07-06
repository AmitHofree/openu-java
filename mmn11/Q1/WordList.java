package Q1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class to represent the collection of words available for the game
 */
public class WordList {
    private final List<String> words;

    public WordList() {
        words = Arrays.asList("apple", "juice", "banana", "bread", "charlie", "plane", "house", "dog", "zimbabwe");
    }

    /**
     * Picks a random word for the game
     * @return - A random word from the word collection
     */
    public String pickRandomWord() {
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(words.size());
        return words.get(randomIndex);
    }
}

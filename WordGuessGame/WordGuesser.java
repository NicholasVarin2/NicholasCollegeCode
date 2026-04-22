import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordGuesser {

    private List<String> words;
    private Set<Character> guessedLetters;
    private String currentPattern;
    private int remainingGuesses;
    private final int wordLength;
    /*
     * Use the passed in values to initialize the state of the game. The set of words to consider should initially be all words from
     * the dictionary that are of the given length. The constructor should throw an IllegalArgumentException exception if
     * the passed in length is less than 1 or if the passed in max is less than 0.
     * The constructor uses the Collection<E> interface for its parameter so that a client can pass it either a set or a list. The
     * interface has the usual iterator() method for getting an iterator and allows access with foreach loops.
     */
    public WordGuesser(Collection<String> words, int length, int max) {
        if (length < 1) {
            throw new IllegalArgumentException("Word length must be at least 1.");
        }
        if (max < 0) {
            throw new IllegalArgumentException("Max guesses must be non-negative.");
        }

        // Filter words by the given length
        this.words = new ArrayList<>();
        for (String word : words) {
            if (word.length() == length) {
                // filteredWords.add(word);
                this.words.add(word);
            }
        }

        if (this.words.isEmpty()) {
            throw new IllegalArgumentException("No words of the specified length found in the dictionary.");
        }

      
        // this.currentPattern = "-".repeat(length);
        this.wordLength = length;
        this.remainingGuesses = max;
        this.guessedLetters = new TreeSet<>();
        this.currentPattern = "-".repeat(length);
    }

    /*
     * The client calls this method to get access to the current set of words being considered by the letter guesser.
     */
    public Set<String> words() {
        return new HashSet<>(words);
    }
    
    // The client calls this method to find out how many guesses the player has left.

    public int guessesLeft() {
        return this.remainingGuesses;
    }
    // The client calls this method to find out the current set of letters that have been guessed by the user. These should be in alphabetical order.
    public Set<Character> guesses() {
        return new TreeSet<>(this.guessedLetters);
    }
    /*
     * Return the current pattern to be displayed for the guessing game taking into account guesses that have been made. Letters
     * that have not yet been guessed should be displayed as a dash and there should be spaces separating the letters. There should
     * be no leading or trailing spaces. This method should throw an IllegalStateException if the set of words is empty.
     */
    public String pattern() {
        if (this.words.isEmpty()) {
            throw new IllegalStateException("The set of words is empty.");
        }
        return String.join(" ", this.currentPattern.split(""));

    //     StringBuilder patternBuilder = new StringBuilder();
    //     // Iterate through the current pattern and build the display string
    //     for (int i = 0; i < this.currentPattern.length(); i++) {
    //         char currentChar = this.currentPattern.charAt(i);
    //         // If the character has been guessed, append it to the pattern
    //         if (this.guessedLetters.contains(currentChar)) {
    //             patternBuilder.append(currentChar);
    //         } else {
    //             // If the character has not been guessed, append a dash
    //             patternBuilder.append('-');
    //         }
    //         // Add a space between characters, but not after the last one
    //         if (i < this.currentPattern.length() - 1) {
    //             patternBuilder.append(' ');
    //         }
    //     }

    //     return patternBuilder.toString();
     }
    /*
     * This is the method that does most of the work by recording the next guess made by the user. It should throw an
     * IllegalStateException if the number of guesses left is not at least 1 or if the set of words is empty. Using this guess,
     * it should decide what set of words to use going forward. It should return the number of occurrences of the guessed letter in
     * the new pattern and it should appropriately update the number of guesses left. This method should also throw a string
     * exception if the list of words is empty. It should throw an IllegalArgumentException if the previous exception was
     * not thrown and the character being guessed was guessed previously.
     */
    public int record(char guess) {
        if (this.remainingGuesses < 1) {
            throw new IllegalStateException("No guesses left.");
        }
        if (this.words.isEmpty()) {
            throw new IllegalStateException("The set of words is empty.");
        }
        if (this.guessedLetters.contains(guess)) {
            throw new IllegalArgumentException("Letter '" + guess + "' has already been guessed.");
        }

        // Add the guessed letter to the set of guessed letters
        this.guessedLetters.add(guess);

        // Create a map to group words by their resulting patterns
        Map<String, List<String>> patternGroups = new HashMap<>();
        for (String word : this.words) {
            StringBuilder patternBuilder = new StringBuilder();
            for (int i = 0; i < wordLength; i++) {
                char c = word.charAt(i);
                if (c == guess) {
                    patternBuilder.append(c);
                } else {
                    patternBuilder.append(this.currentPattern.charAt(i));
                }
            }
            String pattern = patternBuilder.toString();
            patternGroups.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word); 
        }

        // Find the largest group of words and update the current pattern
        String bestPattern = null;
        for (String pattern : patternGroups.keySet()) {
            if (bestPattern == null || patternGroups.get(pattern).size() > patternGroups.get(bestPattern).size()) {
                bestPattern = pattern;
            }
        }
        
        this.words = patternGroups.get(bestPattern);
        this.currentPattern = bestPattern;

        // Count occurrences of the guessed letter in the new pattern
        int occurrences = 0;
        for (char c : bestPattern.toCharArray()) {
            if (c == guess) {
                occurrences++;
            }
        }
      

        // Update remaining guesses if the guessed letter is not in the pattern
        if (occurrences == 0) {
            this.remainingGuesses--;
        }

        return occurrences;
    }
}

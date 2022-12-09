package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Player {

    /**
     * The number of incorrect guesses the player currently has for this word.
     */
    private int numIncorrectGuesses;

    /**
     * The characters the player has already guessed for this word.
     */
    private Set<String> alreadyGuessed;

    /**
     * The maximum number of incorrect guesses. AKA number of lives.
     */
    private int maxIncorrect = 7;

    /**
     * The constructor. Constructs a new player.
     */
    public Player() {
        this.numIncorrectGuesses = 0;
        alreadyGuessed = new HashSet<>();
    }

    /**
     * Returns false if the player is dead, ie surpassed 7 incorrect guesses.
     * Returns true if the player is alive, ie less than 7 incorrect guesses.
     * @return whether the player is alive or dead
     */
    public boolean alive() {
        return (this.numIncorrectGuesses >= maxIncorrect ? false : true);
    }

    /**
     * The player guesses a word.
     * @param word the word that the player is supposed to guess.
     */
    public boolean guess(String word) {
        // reset all
        this.reset();
        System.out.println("");
        // pre-process word so that it's all in upper-case
        word = word.toUpperCase();

        String[] wordArr = new String[word.length()];

        // Represent the word as a series of underscores
        for (int i = 0; i < wordArr.length; i++) {
            wordArr[i] = "_ ";
        }

        Scanner scanner = new Scanner(System.in);

        // Initialize current string
        String current;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append(wordArr[i]);
        }
        current = stringBuilder.toString();
        
        // deal with the guess
        do {
            System.out.println("Guess the word: " + current);

            // Ask player to input a character to guess
            System.out.print("Input a letter to guess: ");
            String guessedChar = scanner.nextLine().toUpperCase();
            while (!this.validGuess(guessedChar)) {
                System.out.print("Please enter a new letter: ");
                guessedChar = scanner.nextLine().toUpperCase();
                // For consistency, we only work with upper-case.
            }

            // If input character correct
            if (word.contains(guessedChar.toUpperCase())) {
                System.out.println("Good guess!");
                // iterate through word and indicate that these letters have been guessed
                for (int i = 0; i < word.length(); i++) {
                    if (word.substring(i, i+1).equals(guessedChar.toUpperCase())) {
                        wordArr[i] = guessedChar + " ";
                        stringBuilder = new StringBuilder();
                        for (int k = 0; k < wordArr.length; k++) {
                            stringBuilder.append(wordArr[k]);
                        }
                        current = stringBuilder.toString();
                    }
                }
                current = stringBuilder.toString();
                System.out.println(current);
                System.out.println("Lives remaining: " + (maxIncorrect-numIncorrectGuesses) + ".");
            }

            // If input character incorrect
            else {
                System.out.println("Incorrect guess. ");
                this.numIncorrectGuesses += 1;
                System.out.println("Lives remaining: " + (maxIncorrect-numIncorrectGuesses) + ".");
                if (!this.alive()) {
                    return false;
                }
            }

            System.out.println();
        } while (current.contains("_"));
        // scanner.close();

        // If word completely revealed, ie end of this word
        System.out.println("Congratulations! You've guess the word! The word was \"" + word + "\".");
        return true;
    }
    
    /**
     * Determines the validity of the guessed character. Adds the character to alreadyGuessed if it is a valid guess.
     * Invalid if invalid character or if character has already been guessed.
     * @param guessChar The input guess
     * @return a boolean determining if it's a valid guess
     */
    private boolean validGuess(String guessedChar) {
        if (!guessedChar.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM]")) {
            System.out.print("Not a valid letter. ");
            return false;
        }
        // If input character already guessed
        int numAlrGuessed;
        numAlrGuessed = alreadyGuessed.size();
        this.alreadyGuessed.add(guessedChar.toUpperCase());
        if (numAlrGuessed == this.alreadyGuessed.size()) {
            System.out.print("You've already guessed this character. ");
            return false;
        }
        return true;        
    }

    /**
     * Resets the number of incorrect guesses to be 0, and resets the characters already guessed to empty.
     */
    private void reset(){
        this.numIncorrectGuesses = 0;
        this.alreadyGuessed = new HashSet<>();
    }

}

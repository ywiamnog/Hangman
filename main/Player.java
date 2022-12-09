package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Player {

    // /**
    //  * The number of points that the player has.
    //  */
    // private int points;
    // 
    // /**
    //  * The number of incorrect guesses the player currently has for this word.
    //  */
    // private int numIncorrectGuesses;

    /**
     * The characters the player has already guessed for this word.
     */
    private Set<String> alreadyGuessed;

    /**
     * The constructor. Constructs a new player.
     */
    public Player() {
        // this.points = 0;
        // this.numIncorrectGuesses = 0;
        alreadyGuessed = new HashSet<>();
    }

    /**
     * The player guesses a word.
     * @param word the word that the player is supposed to guess.
     */
    public void guess(String word) {
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
            }

            // If input character incorrect
            else {
                System.out.println("Incorrect guess. ");
            }

            // // If too many incorrect guesses
            // if (numIncorrectGuesses >= 7) {
            //     System.out.println("You lose!")
            // }
            System.out.println();
        } while (current.contains("_"));
        scanner.close();

        // If word completely revealed, ie end of this word
        System.out.println("Congratulations! You've guess the word! The word was \"" + word + "\".");

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

    // /**
    //  * Resets the number of points this player has to 0, and resets the characters already guessed to empty.
    //  */
    // private void reset(){
    //     this.numIncorrectGuesses = 0;
    //     this.alreadyGuessed = new ArrayList<>();
    // }

}

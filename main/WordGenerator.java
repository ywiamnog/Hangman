package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    /**
     * The word selected for the player to guess.
     */
    private String word = "";

    /**
     * A List of possible words.
     */
    private List<String> wordList;

    /**
     * A List of integer indices of words that have already been used.
     */
    private List<Integer> alreadyUsed;

    /**
     * The constructor. Generates a random word for the player to guess from a given filepath.
     * @param filepath the path to the file that the words are generated from.
     */
    public WordGenerator(String filepath) throws IOException{
        this.wordList = genDictionary(filepath);
        // // generate random word
        // Random rand = new Random();
        // int randInt = rand.nextInt(this.wordList.size());
        // this.word = wordList.get(randInt);
        // initialize alreadyUsed
        alreadyUsed = new ArrayList<>();
        // alreadyUsed.add(randInt);
    }
    
    /**
     * Returns the word.
     * @return the word.
     */
    public String getWord() {
        this.generateNewWord();
        return this.word;
    }

    /**
     * Generates one word at random from the dictionary of words.
     * @return a randomly selected word.
     */
    private void generateNewWord() {
        // generate a new random Integer and check to make sure that it is not already used
        Random rand = new Random();
        int randInt;
        do {
            randInt = rand.nextInt(this.wordList.size());
        } while (!this.alreadyUsed.contains(randInt));

        this.word = wordList.get(randInt); 
        this.alreadyUsed.add(randInt);
    }

    /**
     * Generates a dictionary of words from an input file.
     * @return a List of words that can be generated.
     * @throws IOException if the input file is not a valid filepath.
     */
    private static List<String> genDictionary(String filepath) throws IOException {
        List<String> wordList = new ArrayList<>();
        
        File wordFile = new File(filepath);
        BufferedReader reader = new BufferedReader(new FileReader(wordFile));
        
        // Read words and add each word to the List<String> of words
        String line;
        while ((line = reader.readLine()) != null) {
            wordList.add(line);
        }
        reader.close();

        return wordList;
    } 
    
}

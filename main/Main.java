package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        int points = 0;
        // Create new game of hangman
        String userDir = System.getProperty("user.dir");
        WordGenerator wordGenerator = new WordGenerator(userDir + "/main/words/wordsList.txt");
        String word = wordGenerator.getWord();
        // System.out.println(word);

        // Create new player
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        boolean alive = player.guess(word);
        if (alive){
            points += 1;
        }

        do {
            String message;
            if (!alive) {
                message = "You lose! The correct word was \"" + word.toUpperCase() + "\". Would you like to try again? ";
            }
            else {
                message = "Continue with a new word? ";
            }

            // new word run it back?
            System.out.print(message + "(Say \"Y\" for yes or \"N\" for no): ");
            String contStr = scanner.nextLine();
            
            while (!contStr.matches("[YyNn]")){
                System.out.print("Sorry, I didn't understand that. " + message + "(Say \"Y\" for yes or \"N\" for no): ");
                contStr = scanner.nextLine();
            }
            // no run it back >:(
            if (contStr.equals("N") || contStr.equals("n")){
                cont = false;
                System.out.println("Thank you for playing. You correctly guessed " + points + " words :)");
                break;
            }

            // if not alive create new player
            if (!alive){
                player = new Player();
                points = 0;
            }
            // start new word
            word = wordGenerator.getWord();
            System.out.println(word);
            alive = player.guess(word);
            if (alive){
                points += 1;
            }
        } while (cont == true);

        scanner.close();
    }

}
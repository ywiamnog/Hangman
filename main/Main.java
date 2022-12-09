package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        // Create new game of hangman
        String userDir = System.getProperty("user.dir");
        WordGenerator wordGenerator = new WordGenerator(userDir + "/main/words/wordsList.txt");
        String word = wordGenerator.getWord();
        System.out.println(word);

        // Create new player
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        do {
            player.guess(word);

            // new word run it back?
            System.out.print("Continue with a new word? (Say \"Y\" for yes or \"N\" for no)");
            String contStr = scanner.nextLine();
            while (!contStr.matches("[YyNn]")){
                System.out.print("Sorry, I didn't understand that. Continue with a new word? (Say \"Y\" for yes or \"N\" for no)");
                contStr = scanner.nextLine();
            }
            // no run it back >:(
            if (contStr.equals("N") || contStr.equals("n")){
                cont = false;
            }
        } while (cont == true);
        scanner.close();
    }

}
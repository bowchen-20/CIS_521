
package hangmanGame;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Hangman class, which is also the parent class
 * @Bowen Chen & Tangji Li
 * 
 *
 */

public class Hangman {

    protected ArrayList<String> cleanWords;
    protected ArrayList<Character> guess;
    protected ArrayList<Character> mistakes;

    public Hangman(File file) throws FileNotFoundException {
        guess = new ArrayList<Character>();
        mistakes = new ArrayList<Character>();
        readFileGetCleanWords(file);
    }

    public boolean isGameOver(String word) {

        //go over all the characters
        for(int i = 0; i<word.length();i++){
            char c = word.charAt(i);

            //if character not guessed yet, return false
            if(!guess.contains(c)){
                return false;
            }
        }
        //if all characters guessed, return true
        return true;
    }

    public void updateMistakes(char c, String word) {
        //character guessed not in word, add to mistake
        for(int i = 0;i<word.length();i++){
            if(word.charAt(i)==c){
                return;
            }
        }
        mistakes.add(c);
    }

    public void print(String word) {
        //prints the word with dashes where player hasn't guessed
        String printout = "";

        for(int i = 0; i<word.length(); i++){

            //get the character at i for word
            char c = word.charAt(i);

            //if character is in guesses, printout the letter
            if(guess.contains(c)){
                printout += c + " ";
            }
            //otherwise an underscore
            else{
                printout += "_ ";
            }
        }
        System.out.println(printout);
        System.out.println("Guess" + guess.toString());
    }

    public String updateGuess(char c, String word) {
        guess.add(c);
        return word;
    }

    public void readFileGetCleanWords(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);

        //new arraylist to store words
        cleanWords = new ArrayList<String>();

        //read all the lines
        while(in.hasNextLine()){
            String word = in.nextLine();

            //check if word has non alpha
            boolean hasNonAlpha = word.matches("^.*[^a-zA-Z].*$");

            //only consider it if it does not
            if(!hasNonAlpha){
                cleanWords.add(word.toUpperCase());
            }
        }
        in.close();
    }

    public String chooseStartWord() {
        Random r = new Random();
        //choose a random word from our clean words
        int index = r.nextInt(cleanWords.size());
        return cleanWords.get(index);
    }
}

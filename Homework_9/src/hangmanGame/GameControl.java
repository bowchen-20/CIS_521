
package hangmanGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * game control of Hangman
 * obtain player input
 * @Bowen Chen & Tangji Li
 * attended TA office hour for help and tangji's girlfriend 
 * Menting also elped in terms clarifying the structures early
 * on
 */


public class GameControl{
	public static void main(String[] args) throws FileNotFoundException {

		//initialize a boolean to represent if the player want to play the game
		boolean play = true;

		//while the player wants to play the game
		while(play) {
			//reads the file
			File file = new File("words.txt");

			//set a HangmanTraditional and HangmanEvil
			Hangman traditional = new Hangman(file);
			HangmanEvil evil = new HangmanEvil(file);
			
			//generate a boolean to choose Traditional or Evil model
			Hangman hangman;
			Random rand = new Random();
			if(rand.nextBoolean()){
				hangman = traditional;
			}else{
				hangman = evil;
			}

			System.out.println("Welcome to Hangman.\n");

			//initialize a string to store a word for user to guess
			String wordInit;

			//generic choose start word
			wordInit = hangman.chooseStartWord();

			//print out what the word looks like
			System.out.println("The word appears in the form below: ");
			hangman.print(wordInit);
			//set a scanner for player input
			Scanner scan = new Scanner(System.in);
			//ask for player input
			System.out.println("Please guess a letter and enter it below: ");
			//if the player enters something
			while(scan.hasNext()) {
				//get the input
				String letter = scan.next().toUpperCase();
				System.out.println();

				//if it only contains one character
				if(letter.length()==1) {
					
					if (Character.isLetter(letter.charAt(0))){

						wordInit = hangman.updateGuess(letter.charAt(0), wordInit);

						//print out the word
						hangman.print(wordInit);

						//update incorrect guesses
						hangman.updateMistakes(letter.charAt(0), wordInit);

						if(hangman.isGameOver(wordInit)) {
							//stop right here
							break;
						}else {//if the game is not over, ask for another input
							System.out.println("Please input a letter below: ");
						}
					} else {//if the player's input is not a letter
						System.out.println("Please inpu a valid letter here: ");
					}
				}else if (letter.length() > 1) {//if the player inputs more than one
					System.out.println("Please guess one letter at a time: ");
				}
			}


			//if game is over
			if(hangman.isGameOver(wordInit)) {
				//print out the results
				System.out.println("Congrats, you just won!");
				if(!(hangman instanceof HangmanEvil)) {
					System.out.println("You were playing the traditional version of Hangman!");
				}else {
					System.out.println("You were playing the evil version of Hangman!");
				}
				System.out.println("You guessed the word " + hangman.guess.size() + " times in total.");
				System.out.println("The number of incorrect guesses adds up to: " + hangman.mistakes.size());


			
				boolean playAgain = true;
				while(playAgain) {
					System.out.println("Do you wish to play again? Please enter y/n: ");
					String response = scan.next();
					if(response.equals("y")) {
						playAgain= false;
						play = true;
					}
					else if(response.equals("n")) {
						playAgain = false;
						//game ends here essentially
						play = false;
						scan.close();

					}
					else {
						//keep asking until a valid answer is obtained
						System.out.println("Your input is invalid. Please enter 'y' to play again or 'n' to quit.");
					}
				}
			}
		}
	}
}




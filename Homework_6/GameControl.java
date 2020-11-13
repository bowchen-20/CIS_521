package simple21;

import java.util.Scanner;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {
    
	/**
	 * Human player.
	 */
    HumanPlayer human;
    
    /**
     * Computer player.
     */
    ComputerPlayer player1;
    
    /**
     * Computer player.
     */
    ComputerPlayer player2;
    
    /**
     * Computer player.
     */
    ComputerPlayer player3;
    
    /** 
     * A random number generator to be used for returning random "cards" in a card deck.
     * */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls its run method.
     * @param args Not used.
     */
    public static void main(String args[]) {    
        new GameControl().run();
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each of the following actions:
     * - Create the players (one of them a Human)
     * - Deal the initial two cards to each player
     * - Control the play of the game
     * - Print the final results
     */
    public void run() {
    	
        Scanner scanner = new Scanner(System.in);
        
        // Students: your code goes here.
        System.out.println("Welcome to Simple 21!");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*****2121212121212121212121*****");
        System.out.println("*****2121212121212121212121*****");
        System.out.println("*****2121212121212121212121*****");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("The rules are essentially the same as blackjack.");
        System.out.println("You are playing against 3 computer players.");
        
        System.out.print("Please eneter your name here: ");
        String humansName = scanner.next();
        
        this.createPlayers(humansName);
        this.deal();
        this.controlPlay(scanner);
        this.printResults();

        
        scanner.close();
    }
    
    /**
     * Creates one human player with the given humansName, and three computer players with hard-coded names.
     * @param humansName for human player
     */
    public void createPlayers(String humansName) {
       // Students: your code goes here.

       this.human = new HumanPlayer(humansName);

    //    system.out.println(this.human.name);
    //    system.out.println(this.human.getHiddenCard());

       this.player1 = new ComputerPlayer("Player1");
       this.player2 = new ComputerPlayer("Player2");
       this.player3 = new ComputerPlayer("Player3");
    }
    
    /**
     * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
     * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
     * of each other player's cards, not the individual cards.)
     */
    public void deal() { 
        // Students: your code goes here.
       
        System.out.println(" ");
        
        // Assign a hidden card and a visible card to human player
        int hiddenCard = this.nextCard();
        int visibleCard = this.nextCard();

        this.human.takeHiddenCard(hiddenCard);
        this.human.takeVisibleCard(visibleCard);

        // Assign a hidden card and a visible card to player1
        hiddenCard = this.nextCard();
        visibleCard = this.nextCard();

        this.player1.takeHiddenCard(hiddenCard);
        this.player1.takeVisibleCard(visibleCard);

        // Assign a hidden card and a visible card to player2
        hiddenCard = this.nextCard();
        visibleCard = this.nextCard();

        this.player2.takeHiddenCard(hiddenCard);
        this.player2.takeVisibleCard(visibleCard);

        // Assign a hidden card and a visible card to player3
        hiddenCard = this.nextCard();
        visibleCard = this.nextCard();

        this.player3.takeHiddenCard(hiddenCard);
        this.player3.takeVisibleCard(visibleCard);
        System.out.println(" ");
    }
    
    /**
     * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
     * The odds of returning a 10 are four times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).
     * 
     * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
     * @return a random integer in the range 1 - 10.
     */
    public int nextCard() { 
        // Students: your code goes here.
        // Generate a list as shown below, face cards are 10 accroding to the rules of simple21
    	int[] cards = {1,2,3,4,5,6,7,8,9,10,10,10,10};
        int cardsIndex = random.nextInt(cards.length);
        int nextCard = cards[cardsIndex];
        return nextCard;
    }

    void controlPlay(Scanner scanner) { 
        // Students: your code goes here.
    	
        // checking if everyone has passed
    	while (!this.checkAllPlayersHavePassed()) {
    		
    		// If human chooses not to pass
    		if (human.passed == false) {
    			
    		    human.offerCard(human, player1, player2, player3, scanner);
    			
    			// If player passes, prints out a message on display
    			if (human.passed == true) {
    				System.out.println(human.name + " chooses to pass.");
    				
    			// If player take the card, take a visible card.
    			} else {
    				human.takeVisibleCard(this.nextCard());
    			}
    		}
    		
    		// Same here with computer player1
    		if (player1.passed == false) {
    			player1.offerCard(human, player1, player2, player3);
    			if (player1.passed == true) {
    				System.out.println(player1.name + " chooses to pass.");
    			} else {
    				player1.takeVisibleCard(this.nextCard());
    			}
    		}
    		
    		// Same here with computer player2
    		if (player2.passed == false) {
    			player2.offerCard(human, player1, player2, player3);
    			if (player2.passed == true) {
    				System.out.println(player2.name + " chooses to pass.");
    			} else {
    				player2.takeVisibleCard(this.nextCard());
    			}
    		}
    		
    		// Same here with computer player3
    		if (player3.passed == false) {
    			player3.offerCard(human, player1, player2, player3);
    			if (player3.passed == true) {
    				System.out.println(player3.name + " chooses to pass.");
    			} else {
    				player3.takeVisibleCard(this.nextCard());
    			}
    		}
    		
    		System.out.println(" ");
    	}
    }
     
    /**
     * Checks if all players have passed.
     * @return true if all players have passed
     */

    public boolean checkAllPlayersHavePassed() {
        // Students: your code goes here.
        boolean allplayers = false;
        if (human.passed == true && player1.passed == true && player2.passed == true && player3.passed == true){
            allplayers = true;
        }

        return allplayers;
    }
    
    /**
     * Prints a summary at the end of the game.
     * Displays how many points each player had, and if applicable, who won.
     */
    public void printResults() { 
        // Students: your code goes here.
        System.out.println(" ");
        System.out.println("The game is over.");
        
        System.out.println(human.name + " has scored " + human.getScore() + " points in total.");
        System.out.println(player1.name + " has scored " + player1.getScore() + " points in total.");
        System.out.println(player2.name + " has scored " + player2.getScore() + " points in total.");
        System.out.println(player3.name + " has scored " + player3.getScore() + " points in total.");

        this.printWinner();
    }

    /**
     * Determines who won the game, and prints the results.
     */
    public void printWinner() {

        int humanScore = 0 ;
    	int p1Score = 0;
    	int p2Score = 0;
		int p3Score = 0;
		
    	
    	// If any player's score is over 21, then assign 0 to its score.
    	if (human.getScore() > 21) {
    		humanScore = 0;
    	} else {
    		humanScore = human.getScore();
    	}
    	
    	if (player1.getScore() > 21) {
    		p1Score = 0;
    	} else {
    		p1Score = player1.getScore();
    	}
    	
    	if (player2.getScore() > 21) {
    		p2Score = 0;
    	} else {
    		p2Score = player2.getScore();
    	}
    	
    	if (player3.getScore() > 21) {
    		p3Score = 0;
    	} else {
    		p3Score = player3.getScore();
    	}
    	
    	// First check with player1. This discusses the case that human score is less than or equal to player1
    	if (humanScore <=  p1Score) {
    		
    		// Then compare player1 with player2.
    		if (p1Score <= p2Score) {
    			
    			// Then compare player2 with player3.
        		// If player2 win the game.
    			if (p2Score > p3Score) {
                    if (p2Score > p1Score) {
    				System.out.println("Player2 wins the game with " + player2.getScore()+ " point(s).");
                    } else if (p2Score == p1Score) {
                        System.out.println("It's a tie, and thus there is no winner for this round.");
                    }
    			// If player3 win the game.
    			} else if (p2Score < p3Score) {
    				System.out.println("Player3 wins the game with " + player3.getScore()+ " point(s).");
    			
    			// Tie the game.
    			} else if (p2Score == p3Score) {
    				System.out.println("It's a tie, and thus there is no winner for this round.");
    			}
    			
        	// If player1 scores more points than players2:
    		} else if (p1Score > p2Score) {

    			if (p1Score > p3Score) {
                    if (p1Score > humanScore) {
    				System.out.println("Player1 wins the game with " + player1.getScore()+ " point(s).");
                    } else if (p1Score == humanScore){
                        System.out.println("It's a tie, and thus there is no winner for this round.");
                }
            } else if (p1Score < p3Score) {
    				System.out.println("Player3 wins the game with " + player3.getScore()+ " point(s).");
    			} else if (p1Score == p3Score){
    				System.out.println("It's a tie, and thus there is no winner for this round.");
    			}
    		}   	
    	// If human player has a higher score than player1 to start with
    	} else if (humanScore > p1Score) {
    		// Then compare human player with player2.
    		// In case that player2 scores higher
    		if (humanScore <= p2Score) {
    			
    			// Then compare player2 with player3.
                if (p2Score > p3Score) {
                    if (p2Score > humanScore) {
    				    System.out.println("Player2 wins the game with " + player2.getScore()+ " point(s).");
                    } else if (p2Score == humanScore) {
                        System.out.println("It's a tie, and thus there is no winner for this round.");
                    }	
    			// Player3 wins the game
    			} else if (p2Score < p3Score) {
    				System.out.println("Player3 wins the game with " + player3.getScore()+ " point(s).");
    			
    			// Here we have a tie
    			} else if (p2Score == p3Score){
    				System.out.println("It's a tie, and thus there is no winner for this round.");
    			}
    			
        	// If human player scores higher than p2
    		} else if (humanScore > p2Score) {
                // Human wins the game
    			if (humanScore > p3Score) {
                    System.out.println(human.name+ " wins the game with " + player3.getScore()+ " point(s).");
                // Player 3 wins the game
    			} else if (humanScore < p3Score) {
    				System.out.println("Player3 wins the game with " + human.getScore()+ " point(s).");
                // Tie again
                } else if (humanScore == p3Score){
    				System.out.println("It's a tie, and thus there is no winner for this round.");
    			}
    		   		
    		}    	
    	
    	}
    }
}


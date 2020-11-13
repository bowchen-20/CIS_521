package simple21;

/**
 * Represents a computer player in this simplified version of the "21" card game.
 */
public class ComputerPlayer {

	/** 
	 * The name of the player.
	 */
    String name;
    
    /**
     * The player's one hidden card (a value from 1 - 10).
     */
    private int hiddenCard = 0;
    
    /** 
     * The sum of the player's cards, not counting the hidden card. 
     */
    private int sumOfVisibleCards = 0;
    
    /**
     * Flag indicating if the player has passed (asked for no more cards).
     */
    boolean passed = false;
    
    /**
     * Constructs a computer player with the given name.
     * @param name of the user.
     */
    public ComputerPlayer (String name) {
        this.name = name;
    }
    
    /**
     * Decides whether to take another card. In order to make this decision, this player considers 
     * their own total points (sum of visible cards + hidden card). 
     * This player may also consider other players' sum of visible cards, but not the value 
     * of other players' hidden cards.
     * @param human The other human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     * @return true if this player wants another card
     */
    boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) { 
        boolean response = false;
        // 14 is seen as a soft hand, if the sum of the existing cards is greater than 14, a player often would consider folding.
        if (this.getScore() > 14) {
            response = false;
        }
        // If any player draws a visible card that is greater than or equal to 7, there is an incentive to take a new card
        if (this.getScore() <= 14) {
            if ("Player1".equals(this.name)) {
                // 7 is chosen mainly because the expected value is 6.5 
                if (human.getSumOfVisibleCards() > 7 || player2.getSumOfVisibleCards() > 7 || player3.getSumOfVisibleCards() > 7){
                    response = true;
                }else {
                    response = false;
                }

            }else if ("Player2".equals(this.name)) {
                if (human.getSumOfVisibleCards() > 7 || player1.getSumOfVisibleCards() > 7 || player3.getSumOfVisibleCards() > 7){
                    response = true;
                }else {
                    response = false;
                }

            }else if ("Player3".equals(this.name)) {
                if (human.getSumOfVisibleCards() > 7 || player1.getSumOfVisibleCards() > 7 || player2.getSumOfVisibleCards() > 7){
                    response = true;
                }else {
                    response = false;
                }
            }
        }
        
        // If the response to taking another card is yes, then it is bascily saying no to passed.
        // in other words, it needs to be flip flopped to serve the purpose.

        this.passed =! response;
        return response; 
    }

    
    /**    
     * Puts the specified card in this human's hand as the hidden card.
     * Prints a message saying that the card is being taken, but does not print the value of the hidden card.
     * @param card being taken
     */
    public void takeHiddenCard(int card) {
    	// Students: your code goes here.
    	
    	// Assign the number of card to the hidden card.
    	this.hiddenCard = card;
    	
    	System.out.println(this.name + " takes a hidden card. ");
    }
    
    /**
     * Adds the given card to the sum of the visible cards for this player.
     * Prints a message saying that the card is being taken.
     * @param card being taken
     */
    void takeVisibleCard(int card) { 
    	// Students: your code goes here.
    	
    	// Add the number of card to the sum of visible cards.
    	this.sumOfVisibleCards += card;
    	System.out.println(this.name + " takes " + card);
    			
    }

    /**
     * Returns the total sum of this player's cards, not counting the hidden card. 
     * @return sumOfVisibleCards
     */
    int getSumOfVisibleCards() { 
    	// Students: your code goes here.
    	
    	// Return the sum of visible cards.
    	return this.sumOfVisibleCards;
    }
    
    /**
     * Return this player's total score (the total of all this human player's cards).
     * That is to say, the sum of the visible cards + the hidden card.
     * @return total score 
     */
    int getScore() { 
    	// Students: your code goes here.
    	
    	// the total score is the sum of the hidden card and the sum of visible cards.
    	return this.hiddenCard + this.sumOfVisibleCards;
    }

    
}
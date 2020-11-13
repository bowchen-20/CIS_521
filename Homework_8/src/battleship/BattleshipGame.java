package battleship;

import java.util.ArrayList;

import java.util.Scanner;
public class BattleshipGame {
 /**
  * Main class for a human vs. computer version of battleship
  * @author Tangji Li & Bowen Chen
  */
 
 
 /**
  * Game constructor
  */
 public BattleshipGame() {
 }
 

 public static void main(String[] args) {
  
  //scanner for game flag
  Scanner gameFlag = new Scanner(System.in);
  
  //scanner for player input
  Scanner input = new Scanner(System.in);
  
  
  
  while(true) {
	  
   //enter game
   System.out.print("Press 1 to enter the game "
     + ",press other numbers to quit the game: "); 
  
   if(gameFlag.nextInt() != 1) {
    System.out.println("See ya!");
    break;}
   
   
   Ocean ocean = new Ocean();
   ocean.placeAllShipsRandomly();
   System.out.println("Welcome to Battleship Game!");
   System.out.println("Your ocean looks like this:");
   ocean.print();
   
   while(!ocean.isGameOver()) {
    
    int row;
    int column;
    
    //get user input
    while(true) {
     System.out.println("Please enter the position"
       + "in the form of row, columnthat you want to fire at: " );
     System.out.println("using ',' to seperate. For example: 1,2 " );
      //get the input
		String str = input.next();
		//split the "," and add the row and column in an Array
		String[]s =str.split(",");
		//get the value of the player's row 
		row=Integer.parseInt(s[0]);
		//get the value of the player's column 
		column=Integer.parseInt(s[1]);
     ArrayList<Integer> intYourCR= new ArrayList<Integer>();
		//add the player's input in the ArrayList
		intYourCR.add(row);
		intYourCR.add(column);
		//add the player's input in the ArrayList in Ocean Class
		ocean.yourCR.add(intYourCR);

     if(row >= 0 && row <= 9 && column >=0 && column <= 9 )
      break;
     else
      System.out.println("Sorry you didn't enter"
        + "an acceptable row and column combo, please try again");
    }
    
    //shoot area and return result
    if(ocean.shootAt(row, column)) {
     if(ocean.getShipArray()[row][column].isSunk())
      System.out.println("You just sunk a ship!");
     else
      System.out.println("Your shot landed on a ship!");
    }
    else 
     System.out.println("Sorry, it seems that you missed.");

    System.out.println("You have fired " + 
    	    ocean.getShotsFired() + " shot(s).");
    	  System.out.println("The time(s) you hit is(are) " + 
    	    ocean.getHitCount());
    	  System.out.println("You have sunk " + 
    	    ocean.getShipsSunk() + " ship(s).");
    	  System.out.println("Now the ocean looks like this:");
    	  ocean.print();
   }
   System.out.println("Congrats! You just won!");
 //initialize a flag to decide whether to ask the player to play again
	boolean asking = true;
	boolean playing = true;
	//flag represents ask the player to play again
	while(asking) {
		//asking
	    System.out.println("Do you want to play again? y/n");
	    //get the input as a Sting
	    String input1 = gameFlag.next();
	    //if the input is y
	    if(input1.equals("y")) {
	    	//no more asking
	    	asking= false;
	    	//stop the loop
	    	break;
	    }
	    //if the input is n
	    else if(input1.equals("n")) {
	    	//no more asking
	    	asking = false;
	    	//quit the game
	    	playing = false;
	    	//close the scanner
	    	 gameFlag.close();
	    	  input.close();
	    	//stop the loop
	    	break;
	    }
	    //invalid input
	    else {
	    	//asking the player to input again
	    	System.out.println("Your input is incorrect. Input 'y' to play again. Input 'n' to quit.");
		    //continue asking until get a valid answer
	    	continue;
	    }
	}
}

   
  }
 
 }

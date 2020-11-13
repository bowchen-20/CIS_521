package battleship;

import java.util.ArrayList;

/**
 * Class Ocean
 */
public class Ocean {

 /**
  * instance variables
  */
 //Used to determine which ship is in any given location
 private Ship[][] ships = new Ship[10][10];
 //The total number of shots fired by the user
 private int shotsFired;
 //The number of times a shot hit a ship
 private int hitCount;
 //The number of ships sunk
 private int shipsSunk;
 //an empty sea
 //Ship emptySea = new EmptySea();
 //a battleship
 Ship battleShip1 = new Battleship();
 //a cruiser ship
 Ship cruiser1 = new Cruiser();
 //a cruiser ship
 Ship cruiser2 = new Cruiser();
 //a destroyer ship
 Ship destroyer1 = new Destroyer();
 //a destroyer ship
 Ship destroyer2 = new Destroyer();
 //a destroyer ship
 Ship destroyer3 = new Destroyer();
 //a submarine ship
 Ship submarine1 = new Submarine();
 //a submarine ship
 Ship submarine2 = new Submarine();
 //a submarine ship
 Ship submarine3 = new Submarine();
 //a submarine ship
    Ship submarine4 = new Submarine();
 //ArrayList to store user's input as a {row,column}
 ArrayList <ArrayList<Integer>> yourCR= new ArrayList <ArrayList<Integer>>();
 
 /**
  * constructor
  */
 
 public Ocean() {
  //iterate every row of the ocean
  for(int i=0;i<=9;i++) {
   //iterate every column of the ocean
   for(int j=0;j<=9;j++) {
    //fills the ships array with EmptySea objects
    this.getShipArray()[i][j]= new EmptySea() ;
    ships[i][j].setBowRow(i);
    ships[i][j].setBowColumn(j);
	
   }
  }
  
  //initializes the total number of shots fired by the user
  shotsFired=0;
  //initializes the number of times a shot hit a ship
  hitCount=0;
  //initializes the number of sunken ship
  shipsSunk=0;   
 }
 
 /**
  * Place all ten ships randomly on the (initially empty) ocean
  */
 void placeAllShipsRandomly() {
  //place each of 10 ships randomly by using helper methods in Ship Class
  battleShip1.placeRandomly(this);
  cruiser1.placeRandomly(this);
  cruiser2.placeRandomly(this);
  destroyer1.placeRandomly(this);
  destroyer2.placeRandomly(this);
  destroyer3.placeRandomly(this);
  submarine1.placeRandomly(this);
  submarine2.placeRandomly(this);
  submarine3.placeRandomly(this);
  submarine4.placeRandomly(this);  
 }
 
 /**
  * represents whether the given location contains a ship
  * @param a given row
  * @param a given column
  * @return true if the given location contains a ship
  */
 boolean isOccupied(int row, int column) {
  //if an emptySea object in the given location 
  if (ships[row][column].getShipType()== "empty"){
   //return false
   return false;
  }
  //the given location does not have an emptySea
  else {
   //return true
   return true;
  }
 }
 /**
  * represent if the given location contains a real ship
  * @param a given row
  * @param a given column
  * @return true if the given location contains a ”real” ship
  */
 boolean shootAt(int row, int column) {
  //the number of shot +1
  this.shotsFired++;
  //if the given location contains a ship
  if(isOccupied(row,column)) {
   //the number of times a shot hit a ship +1
   hitCount++;
   
   
   //if the ship is not sunk
   if(ships[row][column].isSunk()==false) {
    
    
    //if the ship is horizontal
    if(ships[row][column].isHorizontal()) {
     //set boolean hit
     ships[row][column].getHit()[ships[row][column].getBowColumn()-column]=true;
     
     if(ships[row][column].isSunk()==true) {
      //add the number of sunk ships
      this.shipsSunk++;
     }
     
    }else {
     //set boolean hit
     ships[row][column].getHit()[ships[row][column].getBowRow()-row]=true;
     
     if(ships[row][column].isSunk()==true) {
      //add the number of sunk ships
      this.shipsSunk++;
     }
    }
    
    //return true
    return true;
    
   }
   //if the ship is sunk
   else  {
    //return false
    return false;
   }
   
  }
  //if the given location is empty
  else {
   //return false
   return false;
  }
  
 }
 /**
  * 
  * @return the number of shots fired
  */
 int getShotsFired() {
  //return shotsFired
  return shotsFired;
 }
 /**
  * 
  * @return the number of hits recorded
  */
 int getHitCount() {
  //return hitCount
  return hitCount;
 }
 /**
  * 
  * @return the number of ships sunk
  */
 int getShipsSunk() {
  //return shipsSunk
  return shipsSunk;
 }
 /**
  * represent if the game is over
  * @return true if all ships have been sunk
  */
 boolean isGameOver() {
  //if the number of sunk ships is 10(all ships are sunk)
  if(shipsSunk==10) {
   //return true
   return true;
  }
  //not all 10 ships are sunk
  else {
   //return false
   return false;
  }
 }
 /**
  * get the 0x10 array of Ships
  * @return the 10x10 array of Ships
  */
 Ship[][] getShipArray(){
  //return ships
  return ships;
 }
 /**
  * Prints the Ocean
  */
 void print() {
  //for every row in the ocean
  for(int i = 0; i<=10;i++){
   if (i == 0) {
    System.out.println("  0 1 2 3 4 5 6 7 8 9");
    continue;
   }

   else {
  //print out left row
   System.out.print(i-1+" ");
   }
   //for every column in the ocean
   for(int j = 0; j <=9;j++) {
    
    //generating an ArrayList to store the coordinate {i,j}
    ArrayList<Integer> ij= new ArrayList<Integer>();
    //add i to the ArrayList
    ij.add(i-1);
    //add j to the ArrayList
    ij.add(j);
    
    //if the column and row which a player input = the (i,j)
    //this location is hit by the player. Show the location having an emptySea ,a ship or a sunk ship
    if(this.yourCR.contains(ij)){
     //print out this ship as string
     System.out.print(this.getShipArray()[i-1][j]+" ");
    }
    //other location in the ship which has not been hit by the player
    else {
     //print "." to represent unknown
     System.out.print(". ");
    }
      }
    System.out.println();
  }    
 }
 
 
}
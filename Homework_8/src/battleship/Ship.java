package battleship;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class Ship
 * @author Tangji Li & bowen chen 
 *
 */
public abstract class Ship {

	/**
	 * instance variables
	 */
	//the row that contains the bow
	private int bowRow;
	//the column that contains the bow
	private int bowColumn;
	//the length of the ship
	private int length;
	//represent whether the ship is placed horizontally or vertically
	private boolean horizontal;
	//An array of 4 booleans that indicate 
	//whether that part of the ship has been hit or not
	private boolean[] hit;
	
	/**
	 * constructor of the Ship class
	 */
	public Ship(int length) {
		//set the length of the ship
		this.length=length;
		//initialize the hit array
		hit = new boolean[length];
		//iterate every boolean in boolean[] hit
		for(int i = 0; i<length;i++) {
			//set the boolean to false
			hit[i]=false;
		}
		
	}
	/**
	 * getters
	 * @return
	 */
	
	/**
	 * 
	 * @return the ship length
	 */
	public int getLength() {
		//return the length of ship 
		return this.length;
	}
	/**
	 * 
	 * @return the row of the bow
	 */
	public int getBowRow(){
		//return the row of the bow
		return this.bowRow;
	}
	/**
	 * 
	 * @return the column of the bow
	 */
	public int getBowColumn() {
		//return the column of the bow
		return this.bowColumn;
	}
	/**
	 * 
	 * @return the hit array
	 */
	public boolean[] getHit() {
		//return the hit array
		return hit;
	}
	/**
	 * 
	 * @return whether the ship is horizontal or not 
	 */
	public boolean isHorizontal() {
		//return the instance variable horizontal
		return this.horizontal;
	}
	
	/**
	 * Setters
	 */
	
	/**
	 * set the value of bowRow
	 * @param a given row
	 */
	
	public void setBowRow(int row) {
		//give the given row to bowRow
		this.bowRow=row;
	}
	
	/**
	 * set the value of bowColumn
	 * @param a given column
	 */
	public void setBowColumn(int column) {
		//the bowColumn = the given column
		this.bowColumn=column;
	}
	/**
	 * set the value of the instance variable 
	 * @param a given boolean horizontal representing whether to set the ship horizontal or not
	 */
	public void setHorizontal(boolean horizontal) {
		//horizontal = the given horizontal
		this.horizontal=horizontal;
	}
	
	/**
	 * abstract methods
	 */
	
	
	/**
	 * the type of ship as a string
	 * @return the type of ship as a string
	 */
	public abstract String getShipType();
	
	/**
	 * returns true if it is okay to put a ship of this length with its bow in this location
	 * @param a given row
	 * @param a given column
	 * @param horizontal to representing the ship is horizontal or vertical
	 * @param a given ocean
	 * @return true if it is okay to put a ship of this length with its bow in this location
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal,Ocean ocean) {
		
		//initialize a boolean
		boolean b = true;
		
		//if the ship is horizontal
		if(horizontal) {
			//if the rows and columns of every part of the ship is between 0 - 9
			if(0<=row&&row<=9&&0<=(column-length+1)&&column<=9) {
				//initialize a list, add the row. the left row, the right row
				int[]rows = {row-1,row,row+1};
				//initialize an ArrayList representing the row is between 0-9
				ArrayList<Integer> rows09= new ArrayList<Integer>();
				//iterate the row in int[]rows
				for(int r:rows) {
					//if the row is between 0-9
					if (r >=0&&r<=9) {
						//add that number to the ArrayList
						rows09.add(r);
					}
				}
				//initialize an ArrayList representing the column is between 0-9
				ArrayList<Integer> columns09= new ArrayList<Integer>();
				//iterate every column of the ship, the column above, the column below
				for(int i = column-this.length; i <=column+1;i++) {
					//if the column is between 0-9
					if(i>=0&&i<=9) {
						//add that number to the ArrayList
						columns09.add(i);
					}
				}
				//iterate every row between 0-9
				for(int r:rows09) {
					//iterate every column between 0-9
					for(int c:columns09) {
						//a ship in the (row,column)
						if(ocean.isOccupied(r, c)) {
							//set boolean to false
							b = false;
							//stop the inner loop
							break;
					    }
						
					}//the ocean we want to set a ship and its surrounding ocean is occupied
					if(b==false) {
						//stop the outer loop
						break;
					}
			    }
			}
			//not every part of the ship's rows and columns is between 0 - 9
			else {
				//set boolean to false
				b=false;
			}
		}
			
		//if the ship is vertical
		if(horizontal==false) {
			//if the rows and columns of every part of the ship is between 0 - 9
			if(0<=column&&column<=9&&(row-length+1)>=0&&row<=9) {
				
				//initialize a list, add the column. the left column, the right column
				int[]columns = {column-1,column,column+1};
				//initialize an ArrayList representing the column is between 0-9
				ArrayList<Integer> columns09= new ArrayList<Integer>();
				//iterate the row in int[]columns
				for(int c:columns) {
					//if the column is between 0-9
					if (c >=0&&c<=9) {
						//add that number to the ArrayList
						columns09.add(c);
					}
				}
				//initialize an ArrayList representing the row is between 0-9
				ArrayList<Integer> rows09= new ArrayList<Integer>();
				//iterate every row of the ship, the row above, the row below
				for(int i = row-this.length; i <=row+1;i++) {
					//if the row is between 0-9
					if(i>=0&&i<=9) {
						//add that number to the ArrayList
						rows09.add(i);
					}
				}
				//iterate every column between 0-9
				for(int c:columns09) {
					//iterate every row between 0-9
					for(int r :rows09) {
						//a ship in that (row,column)
						if(ocean.isOccupied(r, c)) {
							//set the boolean b to false
							b=false;
							//stop the inner loop
							break;
						}
					}
					///the ocean we want to set a ship and its surrounding ocean is occupied
					if(b==false) {
						//stop the outer loop
						break;
					}
				}
			}
			//not every part of the ship's rows and columns is between 0 - 9
			else {
				//set boolean to false
				b = false;
			}
		}
		//return the boolean
		return b;
	}
	
	/**
	 * put the ship in the ocean
	 * @param a given row
	 * @param a given column
	 * @param a given horizontal representing whether to place the ship horizontal or not
	 * @param a given ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		//set the given row to bow
		setBowRow(row);
		//set the given column to bow
		setBowColumn(column);
		//set the horizontal to the ship
		setHorizontal(horizontal);
		
		//if the ship is horizontal
		if(horizontal) {
			//iterate every column of the ship
			for(int i = column-this.length+1; i <=column;i++) {
				//put a reference to every part of the ship in the given ocean 
				ocean.getShipArray()[row][i]= this;
		    }
		}
		//if the ship is vertical
		else {
			//iterate every row of the ship
			for(int i = row-this.length+1; i <=row;i++) {
				//put a reference to every part of the ship in the given ocean 
				ocean.getShipArray()[i][column]= this;
			}
		}
	}
	
	/**
	 * a helper method
	 * put a ship randomly in the given ocean
	 * @param a given ocean
	 */
	void placeRandomly(Ocean ocean){
		//set a Random r
		Random r = new Random();
		//set an integer representing the row
		int rowRandom;
		//set an integer representing the column
		int columnRandom;
		//set an boolean representing horizontal or not
		boolean horizontalRandom;
		//set an boolean representing not finding a valid place to put a ship
		boolean continuePlace = true;
		
		//while not finding a valid place to put a ship
		while (continuePlace) {
			//generate a random integer between 0-9. Give the value to row
			rowRandom = r.nextInt(10);
			//generate a random integer between 0-9. Give the value to column
			columnRandom = r.nextInt(10);
			//generate a random boolean. Give the value to horizontal
			horizontalRandom = r.nextBoolean();
			//if it is okay to put the ship 
			if(this.okToPlaceShipAt(rowRandom, columnRandom, horizontalRandom, ocean)) {
				//put the ship at the random generating place
				this.placeShipAt(rowRandom, columnRandom, horizontalRandom, ocean);
				//set an boolean representing finding a valid place to put a ship then stop the loop
				continuePlace = false;
			}
		}
	}
	/**
	 * 
	 * @param a given row
	 * @param a given column
	 * @return true if a part of the ship occupies the given row and column, and the ship hasn’t been sunk
	 */
	boolean shootAt(int row, int column) {
		//initialize a boolean
		boolean s = false;
		//it the ship is horizontal
		if(this.horizontal) {
			//if the given row and column are equal to any part of the ship 's coordinate
			if(row == this.bowRow && (this.bowColumn-this.length+1) <= column && column <= this.bowColumn && this.isSunk()==false) {
				//set that part of the ship's hit to true
				hit[this.bowColumn-column]=true;
				//return true
				s = true;
			}
		}
		//if the ship is vertical
		else {
			//if the given row and column are equal to any part of the ship 's coordinate
			if(column == this.bowColumn && (this.bowRow-this.length+1) <= row && row <= this.bowRow && this.isSunk()==false) {
				//set that part of the ship's hit to true
				hit[this.bowRow-row]=true;
				//return true
				s =  true;
			}
		}
		//else return false
		return s;
	}
	
	/**
	 * represents if every part of the ship has been hit
	 * @return true if every part of the ship has been hit, false otherwise
	 */
	boolean isSunk() {
		//initialize a boolean
		boolean sunk = true;
		//iterate every item in hit
		for(int i = 0; i<length;i++) {
			//if any part of the ship does not been hit
			if(hit[i]==false) {
				//return false
				sunk = false;
			}
		}
		//else, every part of the ship has been ship. Then return true
		return sunk;
	}
	
	/**
	 *  return ”s” if the ship has been sunk and ”x” if it has not been sunk
	 */
	@Override
	public String toString() {
		//if the ship has been sunk
		if(isSunk()) {
			//return "s" as String
			return "s";
		}
		//if the ship has not been sunk
		else {
			//return "x" as String
			return "x";
		}
	}
}

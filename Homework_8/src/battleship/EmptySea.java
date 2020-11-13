package battleship;

/**
 * Subclass EmptySea
 * @author Tangji Li & bowen chen
 *
 */
public class EmptySea extends Ship {

	/**
	 * instance variables
	 */
	//the length of empty sea is 1
	private static final int length = 1;
	//the type of empty sea is "empty"
	private static final String shipType="empty";

	/**
	 * constructor
	 */
	public EmptySea() {
		//inherits from superclass
		super(EmptySea.length);
		
	}

	/**
	 * returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column) {
		//returns false
		return false;
	}
	/**
	 * inherits from Ship, and always returns false to indicate that user didn’t sink anything
	 */
	@Override
	boolean isSunk() {
		//returns false
		return false;
	}
	/**
	 * Returns the single character “-“ 
	 */
	@Override
	public String toString() {
		//returns "-"
		return "-";
	}
	/**
	 * get the type of empty sea
	 * returns the string “empty” 
	 */
	@Override
	public String getShipType() {
		
		//return instance variable shipType
		return shipType;
		
	}
	
	

}


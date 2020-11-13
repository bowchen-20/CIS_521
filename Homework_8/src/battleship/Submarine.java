package battleship;

/**
 * Subclass Submarine
 * @author Tangji Li & bowen chen
 *
 */
public class Submarine extends Ship {

	/**
	 * instance variables
	 */
	//the length of a submarine is 1
	private static final int length = 1;
	//the type of a submarine is "submarine"
	private static final String shipType="submarine";

	/**
	 * constructor
	 */
	public Submarine() {
		//inherits from superclass
		super(length);
		
	}
	/**
	 * get the type of a submarine
	 */
	@Override
	public String getShipType() {
		// return the instance variable shipType
		return shipType;
	}

}

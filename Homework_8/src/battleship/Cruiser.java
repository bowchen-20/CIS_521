package battleship;
/**
 * Subclass Cruiser
 * @author Tangji Li & bowen chen
 *
 */
public class Cruiser extends Ship {

	/**
	 * instance variables
	 */
	//the length of a cruiser is 3
	private static final int length = 3;
	//the ship type of a cruiser is "cruiser"
	private static final String shipType="cruiser";

	/**
	 * constructor
	 */
	public Cruiser() {
		//inherits from superclass
		super(length);
		
	}
	/**
	 * get ship type of a cruiser
	 */
	@Override
	public String getShipType() {
		
		//return the instance variance- shipType of a cruiser
		return shipType;
	}

}

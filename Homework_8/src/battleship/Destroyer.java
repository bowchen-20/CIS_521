package battleship;
/**
 * Subclass Destroyer
 * @author Tangji Li & bowen chen
 *
 */
public class Destroyer extends Ship {

	/**
	 * instance variables
	 */
	//length of a destroyer is 2
	private static final int length = 2;
	//ship type of a destroyer is "destroyer"
	private static final String shipType="destroyer";

	/**
	 * constructor
	 */
	public Destroyer() {
		//inherits from superclass
		super(length);
		
	}
	
	/**
	 * get the type of a destroyer
	 */
	@Override
	public String getShipType() {
		// return the instance variable shipType of a destroyer 
		return shipType;
	}

}

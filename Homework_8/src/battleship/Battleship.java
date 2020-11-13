package battleship;
/**
 * Subclass Battleship
 * @author Tangji Li & bowen chen
 *
 */
public class Battleship extends Ship {
	/**
	 * instance variables
	 */
	//length is 4
	private static final int length = 4;
	//set shipType with "battleship"
	private static final String shipType="battleship";

	/**
	 * constructor
	 */
	public Battleship() {
		//inherits from superclass
		super(Battleship.length);
	}

	/**
	 * get ship type of battleship
	 */
	@Override
	public String getShipType() {
		
		//return instance variable-shipType
		return shipType;
	}

}

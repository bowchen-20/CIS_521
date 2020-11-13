package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Test for Subclass Battleship
 * @author Tangji Li & Bowen Chen
 *
 */
class BattleshipTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetShipType() {
		
		Ship ship1 = new Battleship();
		assertEquals("battleship", ship1.getShipType());
	}

	@Test
	void testBattleship() {
		
		Ship ship1 = new Battleship();
		assertEquals(4, ship1.getLength());
		assertEquals(4, ship1.getHit().length);
		assertEquals(false, ship1.getHit()[2]);
	}

}


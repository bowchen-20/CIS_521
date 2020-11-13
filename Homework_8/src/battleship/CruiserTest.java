package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test for Subclass Cruiser
 * @author Tangji Li & Bowen Chen
 *
 */
class CruiserTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetShipType() {
		Ship ship1 = new Cruiser();
		assertEquals("cruiser", ship1.getShipType());
	}

	@Test
	void testCruiser() {
		Ship ship1 = new Cruiser();
		assertEquals(3, ship1.getLength());
		assertEquals(3, ship1.getHit().length);
		assertEquals(false, ship1.getHit()[2]);
	}

}

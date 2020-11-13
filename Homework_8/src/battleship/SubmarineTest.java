package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Test for Subclass Submarine
 * @author Tangji Li & Bowen Chen
 *
 */
class SubmarineTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetShipType() {
		Ship ship1 = new Submarine();
		assertEquals("submarine", ship1.getShipType());
	}

	@Test
	void testSubmarine() {
		Ship ship1 = new Submarine();
		assertEquals(1, ship1.getLength());
		assertEquals(1, ship1.getHit().length);
		assertEquals(false, ship1.getHit()[0]);
	}

}

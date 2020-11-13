package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Test for Class Destroyer
 * @author Tangji Li & Bowen Chen
 *
 */
class DestroyerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetShipType() {
		
		Ship ship1 = new Destroyer();
		assertEquals("destroyer", ship1.getShipType());
	}

	@Test
	void testDestroyer() {
		Ship ship1 = new Destroyer();
		assertEquals(2, ship1.getLength());
		assertEquals(2, ship1.getHit().length);
		assertEquals(false, ship1.getHit()[0]);
		assertEquals(false, ship1.getHit()[1]);
	}

}

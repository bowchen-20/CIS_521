package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Test for Subclass EmptySea
 * @author Tangji Li & Bowen Chen
 *
 */
class EmptySeaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetShipType() {
		
		Ship empty = new EmptySea();
		assertEquals("empty", empty.getShipType());
		
	}

	@Test
	void testShootAt() {

		Ocean ocean = new Ocean();
		
		Ship empty = new EmptySea();
		
		assertEquals(false,empty.shootAt(8, 9));
		empty.shootAt(8, 9);
		assertEquals(false,empty.shootAt(4, 9));
		
	}

	@Test
	void testIsSunk() {
		Ship empty = new EmptySea();
		assertEquals(false,empty.isSunk());
	}

	@Test
	void testToString() {
		
		Ship empty = new EmptySea();
		assertEquals("-",empty.toString());
		
	}

	@Test
	void testEmptySea() {
		Ship empty = new EmptySea();
		assertEquals(1, empty.getLength());
		assertEquals(1, empty.getHit().length);
		assertEquals(false, empty.getHit()[0]);
	}

}

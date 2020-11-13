package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		Ship ship2 = new Cruiser();
		assertEquals(3, ship2.getLength());
		
		Ship emptySea = new EmptySea();
		assertEquals(1, emptySea.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		Ship cruiser = new Cruiser();
		int row2 = 1;
		int column2 = 4;
		boolean horizontal2 = true;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row2, cruiser.getBowRow());
		
		Ship submarine = new Submarine();
		submarine.setBowRow(4);
		assertEquals(4, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(4, battleship.getBowColumn());	
		
		//TODO
		//More tests
		Ship cruiser = new Cruiser();
		int row2 = 1;
		int column2 = 4;
		boolean horizontal2 = true;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(column2, cruiser.getBowColumn());
		
		Ship submarine = new Submarine();
		submarine.setBowColumn(4);
		assertEquals(4, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		assertEquals(false,ship1.getHit()[0]);
		
		Ship ship2 = new Cruiser();
		assertEquals(3, ship2.getHit().length);
	}

	@Test
	void testIsHorizontal​() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		ship1.setHorizontal(false);
		assertEquals(false,ship1.isHorizontal());
		
		Ship ship2 = new Cruiser();
		ship2.setHorizontal(true);
		assertEquals(true,ship2.isHorizontal());
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		assertEquals("battleship",ship1.getShipType());
		
		Ship ship2 = new Cruiser();
		assertEquals("cruiser",ship2.getShipType());
		
		Ship ship3 = new Destroyer();
		assertEquals("destroyer",ship3.getShipType());
		
		Ship ship4 = new Submarine();
		assertEquals("submarine",ship4.getShipType());
		
		Ship empty = new EmptySea();
		assertEquals("empty",empty.getShipType());
	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		ship1.setBowRow(8);
		assertEquals(8, ship1.getBowRow());
		
		Ship ship2 = new Cruiser();
		ship2.setBowRow(4);
		assertEquals(4, ship2.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		ship1.setBowColumn(9);
		assertEquals(9, ship1.getBowColumn());
		
		Ship ship2 = new Cruiser();
		ship2.setBowColumn(7);
		assertEquals(7, ship2.getBowColumn());

	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		Ship ship1 = new Battleship();
		ship1.setHorizontal(false);
		assertEquals(false,ship1.isHorizontal());
		
		Ship ship2 = new Cruiser();
		ship2.setHorizontal(true);
		assertEquals(true,ship2.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
        Ocean ocean = new Ocean();
		
		Ship ship1 = new Battleship();
		assertEquals(true, ship1.okToPlaceShipAt(9, 8, true, ocean));
		
		Ship ship2 = new Battleship();
		assertEquals(false, ship2.okToPlaceShipAt(2, 0, true, ocean));
		
		Ship ships1 = new Submarine();
		Ship ships2 = new Submarine();
		ships1.placeShipAt(2, 3, true, ocean);
		assertEquals(false, ships2.okToPlaceShipAt(2, 4, true, ocean));
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		Battleship battleship3 = new Battleship();
		row = 3;
		column = 4;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
		battleship3.placeShipAt(row, column, horizontal, ocean);
		
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		Ocean ocean = new Ocean();
		
		Ship ship1 = new Battleship();
		
		ship1.placeShipAt(8, 9, false, ocean);
		assertEquals(9, ship1.getBowColumn());
		assertEquals(8, ship1.getBowRow());
		assertSame(ship1, ocean.getShipArray()[7][9]);
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
        Ocean ocean = new Ocean();
		
		Ship ship1 = new Battleship();
		ship1.placeShipAt(8, 9, false, ocean);
		assertEquals(true,ship1.shootAt(8, 9));
		assertEquals(true,ship1.shootAt(7, 9));
		assertEquals(false,ship1.shootAt(4, 9));
		ship1.shootAt(6, 9);
		ship1.shootAt(5, 9);
		assertEquals(false,ship1.shootAt(8, 9));
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
        Ocean ocean = new Ocean();
		
		Ship ship1 = new Submarine();
		ship1.placeShipAt(8, 9, false, ocean);
		assertEquals(false,ship1.isSunk());
		ship1.shootAt(8, 9);
		assertEquals(true,ship1.isSunk());
		
		Ship ship2 = new Destroyer();
		ship2.placeShipAt(4, 5, true, ocean);
		assertEquals(false,ship2.isSunk());
		ship2.shootAt(4, 5);
		assertEquals(false,ship2.isSunk());
		ship2.shootAt(4, 4);
		assertEquals(true,ship2.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
        Ocean ocean = new Ocean();
		
		Ship ship1 = new Submarine();
		assertEquals("x",ship1.toString());
		ship1.placeShipAt(8, 9, false, ocean);
		
		ship1.shootAt(8, 9);
		assertEquals("s",ship1.toString());
	}

}

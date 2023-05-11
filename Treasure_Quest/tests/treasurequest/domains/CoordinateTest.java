package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateTest {

	@Test
	void EqualTest() {
		
		Coordinate coo1 = new Coordinate(1278,-1876);
		Coordinate coo2 = new Coordinate(coo1);
		Coordinate coo3 = new Coordinate(1278,-1876);
		Coordinate coo4 = new Coordinate();
		Coordinate coo5 = null;
		
		assertEquals(coo1.getRow(), 1278);
		assertEquals(coo1.getCol(), -1876);
		assertEquals(coo1, coo1);
		assertEquals(coo1, coo2);
		assertEquals(coo1, coo3);
		assertEquals(coo2, coo3);
		assertNotEquals(coo2, coo4);
		assertNotEquals(coo1, coo4);
		assertEquals(coo4, new Coordinate(0,0));
		
		assertEquals(coo1.hashCode(), coo2.hashCode());
		assertNotEquals(coo1.hashCode(), coo4.hashCode());
		
		assertNotEquals(coo1, 1);
		assertNotEquals(coo1, coo5);
		
		assertEquals(coo4.toString(), "Coordinate [posX=0, posY=0]");
	}
	
	@Test
	void addTest() {
		Coordinate coo1 = new Coordinate(0,0);
		Coordinate coo2 = new Coordinate(200,200);
		
		assertEquals(coo1.add(200, 200), coo2);
		assertNotEquals(coo1.add(201, 200), coo2);
		
		coo1 = new Coordinate(coo1).add(0, 1);
		
		assertNotEquals(coo1.add(200, 200), coo2);
	}

}

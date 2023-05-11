package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaseMapTest {

	private CaseMap map = new CaseMap(MapTestMaker.map_IT2_Carte1_2);
	
	@Test
	void test() {
		Coordinate coo = new Coordinate(0,0);
		
		
		assertEquals(map.getCaseMap().size(), 9);
		
		assertEquals(map.getTreasureRemaining(), 1);
		
		assertEquals(map.getTypeAt(new Coordinate(0,0)), TileType.WATER);
		assertEquals(map.getTypeAt(0,0), TileType.WATER);
		assertEquals(map.getTypeAt(0,1), TileType.SAND);
		assertEquals(map.getTypeAt(1,0), TileType.FOREST);
		assertEquals(map.getTypeAt(1,1), TileType.ROCK);

		assertEquals(map.getNameTypeAt(coo), TileType.WATER.getName());
		assertEquals(map.getCostAt(coo), TileType.WATER.getCost());
		
		assertEquals(map.getCaseAt(coo).getType(), TileType.WATER);
		
		assertEquals(map.isDigableAt(coo), false);
		assertEquals(map.isTreasureAt(coo), false);
		assertEquals(map.isDigableAt(new Coordinate(1,1)), true);
		assertEquals(map.isTreasureAt(new Coordinate(1,2)), true);
		assertEquals(map.getTreasureAt(new Coordinate(1,2)), 42);
		assertEquals(map.getTreasureAt(new Coordinate(1,2)), 0);
		
	}
	

}

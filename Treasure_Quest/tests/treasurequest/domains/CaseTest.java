package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CaseTest {

	@Test
	void initTest() {
		
		Case case1 = new Case(TileType.WATER);
		Case case2 = new Case(TileType.SAND);
		Case case3 = new Case(TileType.GRASSLAND);
		
		Case casetest = new Case(TileType.WATER);
		
		casetest.putTreasure(19);
		case2.putTreasure(12);
		assertEquals(casetest.getTreasure(), 19);
		assertEquals(casetest.getTreasureValue(), 0);
		casetest.dig();
		
		assertEquals(case1.getCost(), 0);
		assertEquals(case2.getCost(), 1);
		assertEquals(case3.getCost(), 2);
		
		assertEquals(case1.isDigable(), false);
		assertEquals(case2.isDigable(), true);
		assertEquals(case3.isDigable(), true);
		assertEquals(casetest.isDigable(), false);

		assertEquals(casetest.isTreasure(), false);
		assertEquals(case2.isTreasure(), true);
		assertEquals(case2.getTreasure(), 12);
		case2.dig();
		assertEquals(case2.isDigable(), false);
		assertEquals(case2.isTreasure(), false);
		
		case1.setHint("NORTH");
		assertEquals(case1.getHint(), "NORTH");
		assertEquals(case1.getType(), TileType.WATER);
		
		
	}
	
}

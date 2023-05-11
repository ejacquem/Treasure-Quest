package treasurequest.domains;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import treasurequest.io.CharArrayFileReader;


public class FindNearestAlgorithmTests {

	private final Map<Coordinate,Case> caseMap = MapTestMaker.map_Test_1;
	private final Algorithm algo = new Algorithm(caseMap);
	
	@Test
	void AllDirectionTest() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if(!(i== 3 && j == 3)) {
					assertEquals(algo.findNearestFrom(new Coordinate(i,j)), new Coordinate(3,3));
				}
			}
		}
		assertEquals(algo.findNearestFrom(new Coordinate(0,0)),null);
	}
	
	@Test
	void FindDirection() {
		Coordinate treasureCoo = new Coordinate(2,2);
		assertEquals(Algorithm.getDirectionFromTo(new Coordinate(3,2), treasureCoo),"NORTH");
		assertEquals(Algorithm.getDirectionFromTo(new Coordinate(1,2), treasureCoo),"SOUTH");
		assertEquals(Algorithm.getDirectionFromTo(new Coordinate(2,1), treasureCoo),"EAST");
		assertEquals(Algorithm.getDirectionFromTo(new Coordinate(2,3), treasureCoo),"WEST");
	}
	
	@Test
	void findClosestCenter() {
		Map<Coordinate,Case> map = TreasureQuestGameFactory.generateMap(
				CharArrayFileReader.parseFile("resources\\maps\\map-sample-hole.txt"));
		assertEquals(Algorithm.findMapCenter(map), new Coordinate(22,42));
	}

	
}

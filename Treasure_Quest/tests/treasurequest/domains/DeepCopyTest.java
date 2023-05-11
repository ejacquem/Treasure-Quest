package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class DeepCopyTest {

	@Test
	void CaseCopytest() {
		Case case1 = new Case(TileType.SAND);
		case1.putTreasure(20);
		case1.setHint("NORTH");
		
		Case case2 = case1.copyOf();
		
		assertEquals(case1.getTreasureValue(), case2.getTreasureValue());
		assertEquals(case1.getHint(), case2.getHint());
		assertEquals(case1.getType(), case2.getType());
		assertEquals(case1.getCost(), case2.getCost());
		assertEquals(case1.isDigable(), case2.isDigable());
		case1.getTreasure();
		case1.setHint("SOUTH");
		case1.dig();
		assertNotEquals(case1.getTreasureValue(), case2.getTreasureValue());
		assertNotEquals(case1.getHint(), case2.getHint());
		assertNotEquals(case1.isDigable(), case2.isDigable());
		assertEquals(case1.isDigable(), case1.copyOf().isDigable());
	}
	
	@Test
	void MapCopytest() {
		Map<Coordinate,Case> map1 = new HashMap<>();
		map1.put(new Coordinate(0,0),new Case(TileType.SAND));
		map1.put(new Coordinate(1,1),new Case(TileType.WATER));
		
		Map<Coordinate,Case> map2 = Algorithm.copyOf(map1);
		
		assertNotEquals(map1, map2);
		assertNotEquals(map1.get(new Coordinate(0,0)), map2.get(new Coordinate(0,0)));
		assertEquals(map1.get(new Coordinate(0,0)).getTreasureValue(), map2.get(new Coordinate(0,0)).getTreasureValue());
		assertEquals(map1.get(new Coordinate(0,0)).getHint(), map2.get(new Coordinate(0,0)).getHint());
		
		map1.get(new Coordinate(0,0)).putTreasure(10);
		map1.get(new Coordinate(0,0)).setHint("NORTH");
		
		assertNotEquals(map1.get(new Coordinate(0,0)).getTreasureValue(), map2.get(new Coordinate(0,0)).getTreasureValue());
		assertNotEquals(map1.get(new Coordinate(0,0)).getHint(), map2.get(new Coordinate(0,0)).getHint());

	}

}

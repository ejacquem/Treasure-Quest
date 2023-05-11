package treasurequest.domains;

import java.util.Map;

import treasurequest.io.CharArrayFileReader;

public class MapTestMaker {

	public static final Map<Coordinate, Case> map_IT2_Carte1_1 = makeMap1_1();
	public static final Map<Coordinate, Case> map_IT2_Carte1_2 = makeMap1_2();
	public static final Map<Coordinate, Case> map_IT2_Carte2 = makeMap2();
	public static final Map<Coordinate, Case> map_Test_1 = makeMapTest1();
	
	private static Map<Coordinate, Case> makeMapTest1() {
		Map<Coordinate, Case> map = TreasureQuestGameFactory.generateMap(
				CharArrayFileReader.parseFile("resources\\maps\\map-sample-test1.txt"));
		map.get(new Coordinate(3,3)).putTreasure(20);
		TreasureQuestGameFactory.generateHint(map);
		return Algorithm.copyOf(map);
	}
	private static Map<Coordinate, Case> makeMap1_1() {
		Map<Coordinate, Case> map = TreasureQuestGameFactory.generateMap(
				CharArrayFileReader.parseFile("resources\\maps\\map-sample-carte1.txt"));
		return Algorithm.copyOf(map);
	}
	public static Map<Coordinate, Case> makeMap1_2() {
		Map<Coordinate, Case> map = TreasureQuestGameFactory.generateMap(
				CharArrayFileReader.parseFile("resources\\maps\\map-sample-carte1.txt"));
		map.get(new Coordinate(1,2)).putTreasure(42);
		return map;
	}
	
	private static Map<Coordinate, Case> makeMap2() {
		Map<Coordinate, Case> map = TreasureQuestGameFactory.generateMap(
				CharArrayFileReader.parseFile("resources\\maps\\map-sample-carte2.txt"));
		
		map.get(new Coordinate(2,0)).putTreasure(42);
		map.get(new Coordinate(2,2)).putTreasure(42);
		map.get(new Coordinate(0,4)).putTreasure(50);
		map.get(new Coordinate(5,4)).putTreasure(84);
		map.get(new Coordinate(5,6)).putTreasure(84);
		
		TreasureQuestGameFactory.generateHint(map);
		
		return Algorithm.copyOf(map);
	}
	
}

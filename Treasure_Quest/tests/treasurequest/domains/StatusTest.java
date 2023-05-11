package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatusTest {

	private TreasureQuestGame game = TreasureQuestGameFactory.makeNewGame(MapTestMaker.map_IT2_Carte1_2);
//	TreasureQuestGame game = new TreasureQuestGameFactory().makeNewGame(Algorithm.copyOf(MapTestMaker.makeMap1_2()));
	
	@Test
	void StatuTest() {
		System.out.println('h');
		
		assertEquals(game.getStatus("Coins"), "Bourse du joueur : 2 P");
		assertEquals(game.getStatus("Treasure"), "Trésors restants : 1");
		assertEquals(game.getStatus("Cost"), "Coût de la case active : 5 P");
		assertEquals(game.getStatus("Type"), "Type de la case action : Rocher");
		assertEquals(game.getStatus("Hello World"), "");
		
		game.setPlayerPosition(new Coordinate(0,0));
		
		assertEquals(game.getStatus("Coins"), "Bourse du joueur : 2 P");
		assertEquals(game.getStatus("Treasure"), "Trésors restants : 1");
		assertEquals(game.getStatus("Cost"), "Coût de la case active : 0 P");
		assertEquals(game.getStatus("Type"), "Type de la case action : Eau");
		game.setPlayerPosition(new Coordinate(0,1));
		assertEquals(game.getStatus("Type"), "Type de la case action : Sable");
		assertEquals(game.getStatus("Cost"), "Coût de la case active : 1 P");
	}

}

/*
 * 	public String getStatus(String name) {
		switch (name) {
			case "Coins":return status.coins.makeStringStatus();
			case "Treasure":return status.tresr.makeStringStatus();
			case "Cost":return status.cost.makeStringStatus();
			case "Type":return status.type.makeStringStatus();
			default:return "";
		}
	}
	*/

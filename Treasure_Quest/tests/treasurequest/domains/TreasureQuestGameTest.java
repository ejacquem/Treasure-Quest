package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreasureQuestGameTest {

	char[][] mapData = new char[][] {
		{'X','X','X','X','X','X','X','S','S','S','X','S','S','S','X','X','X','S'},
		{'X','S','S','S','S','S','X','X','X','X','X','X','S','S','X','X','X','X'},
		{'X','S','S','P','P','S','S','S','S','S','X','X','X','X','X','X','X','X'},
		{'X','X','S','P','F','F','F','F','P','S','X','X','X','X','X','X','X','X'},
		{'X','X','X','S','P','P','F','F','F','F','F','P','S','S','X','X','X','X'},
		{'X','X','S','S','P','F','F','R','R','R','F','F','R','R','R','R','R','R'},
		{'X','X','S','P','P','S','X','X','S','S','S','S','S','P','P','R','R','R'},
		{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}
	};
	
	private TreasureQuestGame game = TreasureQuestGameFactory.makeNewGame(TreasureQuestGameFactory.generateMap(mapData));
	
	@Test
	void TQGametest() {
	
		game.setPlayerPosition(new Coordinate(0,0));
		
		assertEquals(game.getPlayerRow(), 0);
		assertEquals(game.getPlayerCol(), 0);
		
		
		assertEquals(game.dig(), "");
		
		game.setPlayerPosition(new Coordinate(2,2));
		
		game.map.getCaseAt(new Coordinate(2,2)).putTreasure(15);
		
		assertEquals(game.dig(), "TREASURE");
		
		game.map.getCaseAt(new Coordinate(3,3)).putTreasure(15);
		game.movePlayer(1, 1);;
		
		assertEquals(game.dig(), "TREASURE");
		game.map.getCaseAt(new Coordinate(4,4)).getTreasureValue();
		game.setPlayerPosition(new Coordinate(4,4));
		game.dig();
		
		game.map.getCaseAt(new Coordinate(0,17)).getTreasureValue();
		game.setPlayerPosition(new Coordinate(0,17));
		game.dig();
		
		assertTrue(new TreasureQuestGameFactory().makeNewGame() != null);
	}

}

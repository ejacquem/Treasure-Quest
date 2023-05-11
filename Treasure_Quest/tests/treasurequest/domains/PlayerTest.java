package treasurequest.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class PlayerTest {
	@Test
	void initTest() {
		Player player1 = new Player();
		Player player2 = new Player();
		player2.setPosition(5,5);
		player1.setCoins(100);
		
		assertEquals(player1.getCoordinate(), new Coordinate(0,0));
		assertEquals(player2.getCol(), 5);
		player2.setPosition(new Coordinate(6,5));
		assertEquals(player2.getRow(), 6);
		assertEquals(player1.getCoins(), 100);
		player1.addCoins(100);
		assertEquals(player1.getCoins(), 200);
		player1.dig(new Case(TileType.FOREST));
		assertEquals(player1.getCoins(), 197);
		
	}
}

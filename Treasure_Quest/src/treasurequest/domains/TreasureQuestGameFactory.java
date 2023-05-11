package treasurequest.domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treasurequest.io.CharArrayFileReader;
/**
 * RÉPONSE QUESTION 3
 * 
 * L'algorithme à une CTT de O(n). L'algorithme va parcourir une première fois toutes les cases de la carte
 * pour déterminer celle qui sont creusable pour les mettre dans une list. Il va mélanger cette list grâce à la méthode Collections.shuffle
 * qui éxecute l'opération en O(n). Enfin il va parcourir les x premières cases de la list pour y mettre un trésor.
 * 
 * x = nbr de trésor = case creusable /10
 * 
 * Crée et initialise la partie avec des données de départ
 * 
 * */
public class TreasureQuestGameFactory {
	
	private static final String DEFAULT_MAP = "resources\\maps\\map-sample-mario.txt";
	
	/**
	 * Nouvelle partie avec une carte générée à l'avance avec des trésor prédéfini.
	 * */
	public static TreasureQuestGame makeNewGame(Map<Coordinate, Case> caseMap) {
		
		TreasureQuestGame game = new TreasureQuestGame(caseMap);
		
		game.player.setCoins(2* game.map.getTreasureRemaining());
		
		game.player.setPosition(Algorithm.findMapCenter(caseMap));
		
		return game;
	}
	/**
	 * Nouvelle partie avec une carte spécifique, trésor aléatoire
	 * */
	public TreasureQuestGame makeNewGame(char[][] mapData) {
		Map<Coordinate, Case> caseMap = generateMap(mapData);
		generateTreasure(caseMap);
		generateHint(caseMap);
		
		return makeNewGame(caseMap);
	}
	/**
	 * Nouvelle partie avec une carte spécifique d'un fichier, trésor aléatoire
	 * */
	public TreasureQuestGame makeNewGame(String mapFile) {
		return makeNewGame(CharArrayFileReader.parseFile(mapFile));
	}
	/**
	 * Nouvelle partie avec la carte de base
	 * */
	public TreasureQuestGame makeNewGame() {
		return makeNewGame(DEFAULT_MAP);
	}
	
	/**
	 * Génère la carte à partir d'un tableau2D de char
	 * */
	public static Map<Coordinate, Case> generateMap(char[][] mapData) {
		Map<Coordinate, Case> caseMap = new HashMap<>();
		for (int i = 0; i < mapData.length; i++) {
			for (int j = 0; j < mapData[i].length; j++) {
				TileType type = TileType.getType(mapData[i][j]);
				if (type != TileType.UNKNOWN)
					caseMap.put(new Coordinate(i, j), new Case(type));
			}
		}
		return caseMap;
	}

	/**
	 * Génère les trésor sur la carte
	 * */
	public static void generateTreasure(Map<Coordinate, Case> caseMap) {
		//place toute les cases creusables de la carte dans une liste
		List<Case> list = new ArrayList<>();
		for (Case c : caseMap.values()) {
			if (c.getType().isDigable()) {
				list.add(c);
			}
		}
		int digableCase = list.size();
		//obtient le nombre de trésor sur la map
		int numberOfTreasure = (int) Math.ceil(digableCase / 10.);// will always be > 0

		Collections.shuffle(list); // randomize the list

		//place un trésor sur les x premières cases de la list 
		for (int i = 0; i < numberOfTreasure; i++) {
			int value = (int) (Math.random() * 11) + 10;// generate value between 10 & 20
			list.get(i).putTreasure(value);
		}
	}
	
	/**
	 * génère les indices sur la carte
	 * @param caseMap
	 */
	public static void generateHint(Map<Coordinate, Case> caseMap) {
		Algorithm algo = new Algorithm(caseMap);
		for (Coordinate coo : caseMap.keySet()) {
			Coordinate treasureCoo = algo.findNearestFrom(coo);
			String hint = treasureCoo == null? "DUG" : Algorithm.getDirectionFromTo(coo, treasureCoo);
			caseMap.get(coo).setHint(hint);
		}
	}
}

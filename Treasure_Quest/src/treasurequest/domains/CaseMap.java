package treasurequest.domains;

import java.util.Map;

/**
 * RÉPONSE QUESTION 2
 * La collection la plus approprié pour manipuler une carte 2D est une HashMap<Key,Value>. En mettant en clé les coordonnées de la case,
 * elle devient faciel d'accès. Pour déplacer le joueur et vérifier qu'il ne va pas hors de la carte, il suffit de vérifier si une coordonnée
 * est bien sur la carte.
 * 
 * Carte du jeu, s'occupe des actions principales relative à la carte.
 * */
public class CaseMap {

	private final Map<Coordinate, Case> caseMap;

	private int numberOfTreasure = 0; // (this can't be static for some reason)

	CaseMap(Map<Coordinate, Case> caseMap) {
		this.caseMap = caseMap;
		
		for(Case cas : caseMap.values()) {
			if (cas.isTreasure())numberOfTreasure++;
		}
//		caseMap.values().forEach(CaseMap::countTreasure);//doesn't work bc can't be static
	}
	
	/**
	 * Retourne le nombre de trésor restant sur la carte
	 * */
	public int getTreasureRemaining() {
		return numberOfTreasure;
	}

	/**
	 * Retourne le type de la carte à une coordonnée spécifique
	 * */
	public TileType getTypeAt(int posX, int posY) {
		return caseMap.get(new Coordinate(posX, posY)).getType();
	}

	/**
	 * Retourne le type de la carte à une coordonnée spécifique
	 * */
	public TileType getTypeAt(Coordinate coo) {
		return caseMap.get(coo).getType();
	}

	/**
	 * Retourne le nom du type de la carte à une coordonnée spécifique
	 * */
	public String getNameTypeAt(Coordinate coo) {
		return caseMap.get(coo).getType().getName();
	}
	/**
	 * Retourne le coût à une coordonnée spécifique
	 * */
	public int getCostAt(Coordinate coo) {
		return caseMap.get(coo).getCost();
	}
	/**
	 * Retourne la carte de case
	 * */
	public Map<Coordinate, Case> getCaseMap() {
		return caseMap;
	}
	/**
	 * Retourne la case à une coordonnée spécifique
	 * */
	public Case getCaseAt(Coordinate coo) {
		return caseMap.get(coo);
	}
	/**
	 * Retourne si une case est creusable à une coordonnée spécifique
	 * */
	public boolean isDigableAt(Coordinate coo) {
		return caseMap.get(coo).isDigable();
	}
	/**
	 * Retourne si il y a un trésor à une coordonnée spécifique
	 * */
	public boolean isTreasureAt(Coordinate coo) {
		return caseMap.get(coo).isTreasure();
	}
	/**
	 * Retourne la valeur du trésor à une coordonnée spécifique
	 * */
	public int getTreasureAt(Coordinate coo) {
		numberOfTreasure--;
		return caseMap.get(coo).getTreasure();
	}


}

package treasurequest.domains;

/**
 * Définit les types de terrain à destination de la carte.
 * */
public enum TileType {
	SAND(		"Sable",	1),
	GRASSLAND(	"Prairie",	2),
	FOREST(		"Forêt",	3),
	ROCK(		"Rocher",	5),
	WATER(		"Eau",		0), //(la valeur 0 est attribuée aux types qui ne sont pas creusable)
	UNKNOWN(	"Inconnu",	0);
	
	private final String name;
	private final int cost;
	
	TileType(String name, int value) {
		this.name = name;
		this.cost = value;
	}

	/**
	 * Retourne le type de Tile selon sa lettre correspondante
	 * 
	 * @param char letter
	 * */
	public static TileType getType(char letter) {
		switch (letter) {
		case 'X':return WATER;
		case 'S':return SAND;
		case 'P':return GRASSLAND;
		case 'F':return FOREST;
		case 'R':return ROCK;
		default :return UNKNOWN;
		}
	}
	
	/**
	 * Retourne vrai si ce type de case est "creusable"
	 * 
	 * (est creusable si son coût est positif)
	 * */
	public boolean isDigable(){
		return cost > 0;
	}
	
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}
}

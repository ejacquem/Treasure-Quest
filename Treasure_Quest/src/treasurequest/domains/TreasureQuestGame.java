package treasurequest.domains;

import java.util.Map;

/**
 * Cette classe contient les données générales de la partie, tel que l'état du joueur, la carte et le status.
 *
 * Permet de gérer les actions "lourdes" (comme creuser ou chercher un trésor à proximité).
 * 
 * RÉPONSE QUESTION (IT2) 2
 * CTT de l'opération de génération d'indice ? -> fonction generateHint() dans TQGFactory
 * 
 * Mon algorithme va parcourir chaque coordonées de la map, trouver le meilleur trésor aux alentours, et donne une valeur à l'indice de la carte.
 * 
 * 1. Parcourir chaque coordonées de la carte O(N)
 * 2. 	Chercher le meilleur trésor O(1)
 * 3. 	Calcul l'orientation O(1)
 * 
 * L'algorithme est donc de CTT O(N)
 * 
 * 1. Parcourir chaque données prend O(N) opérations où N = nombre de coordonées
 * 2. chercher le meilleur trésor ne dépend pas de variable, l'algorithme recherche parmi les 24 cases autour du joueur, 
 * la CTT est de O(24M) où M est le nombre d'étapes pour comparer deux trésors entre eux (M est une constante), donc O(1).
 * 3. Calculer l'orientation ne dépend pas de variable et aura toujours un nombre fix d'opération, O(1)
 * 
 * 
 * RÉPONSE QUESTION (IT2) 3
 * J'utilise principalement un HashSet pour itérer à travers chaque élément d'une liste de coordonées dont l'ordre n'a pas d'importance.
 * Un set est utilisable ici car les coordonées sont uniques.
 * Accèder à un données dans un set à un CTT de O(1)
 * 
 * */
public class TreasureQuestGame {
	public CaseMap map;
	public Player player;
	public Status status;

	/**
	 * Initialise les paramètres de la partie
	 * */
	public TreasureQuestGame(Map<Coordinate, Case> caseMap) {
		map = new CaseMap(caseMap);
		player = new Player();
		status = new Status(this);
	}

	/**
	 * Essaie de creuser à la position du joueur et agis en fonction de la case ou du résultat du creusage.
	 * 
	 * @return le résultat du creusage sous forme de String. <p>
	 * Le résultat peut être vide si case invalide ou peut être égale aux noms de l'enum SpriteType comme par exemple 
	 * DUG,TREASURE ou une direction : NORTH_EAST, SOUTH,...
	 * 
	 * */
	public String dig() {
		String digResult = "";
		if (map.isDigableAt(player.getCoordinate())) {//check si la case est valide
			player.dig(map.getCaseAt(player.getCoordinate()));//la case est valide, le joueur creuse le sol
			if (map.isTreasureAt(player.getCoordinate())) {//check si il y a un trésor
				//trésor trouvé, le trésor est récupéré et le butin donné au joueur
				player.addCoins(map.getTreasureAt(player.getCoordinate()));
				digResult = "TREASURE";
			} 
			else digResult = map.getCaseAt(player.getCoordinate()).getHint();//Pas de trésor, le résultat est un indice
		}
		return digResult;
	}
	
	/**
	 * déplace le joueur sur une case valide
	 * 
	 * @param int posX du joueur
	 * @param int posY du joueur
	 * */
	public void movePlayer(int posX, int posY) {
		Coordinate newPos = player.getCoordinate().add(posX,posY);
		if(map.getCaseMap().get(newPos) != null)player.setPosition(newPos);
	}

	/**
	 * @return la position du joueur en X
	 * */
	public int getPlayerRow() {
		return player.getRow();
	}
	/**
	 * @return la position du joueur en Y
	 * */
	public int getPlayerCol() {
		return player.getCol();
	}
	/**
	 * Déplace le joueur à des coordonnées précises si celles-ci sont valides
	 * */
	public void setPlayerPosition(Coordinate coo){
		if(map.getCaseMap().get(coo) != null)player.setPosition(coo);
	}
	/**
	 * @return la position du joueur en Coordonnées
	 * */
	public Coordinate getPlayerCoordinate() {
		return player.getCoordinate();
	}
	/**
	 * @return la bourse du joueur
	 * */
	public int getCoins() {
		return player.getCoins();
	}
	/**
	 * @return la coût de la case où se trouve le joueur
	 * */
	public int getCostCurrentCase() {
		return map.getCostAt(getPlayerCoordinate());
	}
	/**
	 * @return le nom du type de case où se trouve le joueur
	 * */
	public String getTypeCurrentCase() {
		return map.getNameTypeAt(getPlayerCoordinate());
	}
	
	/**
	 * Retourne le status formaté
	 * 
	 * @param String name of status
	 * */
	public String getStatus(String name) {
		switch (name) {
			case "Coins":return status.coins.makeStringStatus();
			case "Treasure":return status.tresr.makeStringStatus();
			case "Cost":return status.cost.makeStringStatus();
			case "Type":return status.type.makeStringStatus();
			default:return "";
		}
	}
}

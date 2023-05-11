package treasurequest.domains;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Cette classe permet de traiter les actions les plus lourdes et les algorithmes plus complexes du jeu.
 * Elle contient des algorithmes tel que: trouver le trésor le plus proche, comparer et trouver le "meilleur" trésor, trouver l'orientation
 * d'un vecteur,...
 * 
 * Ces algorithmes ne provoqueront pas de changement sur la carte.
 * 
 * *Le meilleur trésor est défini comme suit : le plus proche du joueur, à la plus grande valeur, le plus proche de 0,0
 * 
 * */
public class Algorithm {

	private final Map<Coordinate,Case> caseMap;
	private Coordinate playerCoo;
	private static final int RANGE = 2;
	
	Algorithm(Map<Coordinate,Case> map){
		this.caseMap = new HashMap<Coordinate,Case>(map);
	}
	
	/**
	 * Algorithme de détection des trésors à proximité, séléctionne les trésor à proximité du joueur et retourne le meilleur* d'entre eux
	 * 
	 * @param Coordinate coo Position du joueur
	 * 
	 * @return Coordinate position du trésor ou null si aucun trésor
	 * */
	public Coordinate findNearestFrom(Coordinate playerCoo) {
		Coordinate treasureCoo = null;
		this.playerCoo = new Coordinate(playerCoo);
		
		for(Coordinate coo : getTreasureList(playerCoo)) //Liste des trésor à proximité du joueur
			treasureCoo = bestBetween(treasureCoo,coo);//compare chaque trésor de la liste et séléctionne le meilleur*
		
		return treasureCoo;
	} 
	
	/**
	 * Compare deux trésors et retourne le meilleur*
	 * @param treasure1
	 * @param treasure2
	 * @return le meilleur des deux trésors
	 */
	private Coordinate bestBetween(Coordinate treasure1 ,Coordinate treasure2) {
		if(treasure1 == null)return treasure2;
		int minDistPlayer = 0;
		int maxValue = 0;
		int minDist00 = 0;
		
		minDistPlayer = minDistance(treasure1, treasure2, playerCoo);
		if (minDistPlayer == 0) {
			maxValue = maxValue(treasure1, treasure2);
			if (maxValue == 0) {
				minDist00 = minDistance(treasure1, treasure2, new Coordinate());
			}
		}
		if (minDistPlayer == 1 || maxValue == 1 || minDist00 == 1)return treasure1;
		return treasure2;
	}
	
	/**
	 * Compare 2 coordonées selon leur distance à un point données
	 * 
	 * @param coo_1
	 * @param coo_2
	 * 
	 * @return 
	 * <ul>
	 * 	<li>+1 si coo_1 est plus proche que coo_2</li>
	 * 	<li>0 si coo_1 est à égale distance que coo_2</li>
	 * 	<li>-1 si coo_1 est plus loin que coo_2</li>
	 * </ul>
	 */
	private static int minDistance(Coordinate coo_1 ,Coordinate coo_2, Coordinate distance) {
		double distT1 = distanceBetween(coo_1, distance);
		double distT2 = distanceBetween(coo_2, distance);
		return distT1 == distT2 ? 0 : (distT1 < distT2 ? 1 : -1);
	}
	/**
	 * Compare 2 coordonées de trésors selon leur valeur
	 * Les coordonées doivent être des coordonées valide dans la carte
	 * 
	 * @param treasure1 T1
	 * @param treasure2 T2
	 * 
	 * @return 
	 * <ul>
	 * 	<li>+1 si T1 vaut plus que T2</li>
	 * 	<li>0 si T1 à la même valeur que T2</li>
	 * 	<li>-1 si T1 vaut moins que T2</li>
	 * </ul>
	 */
	private int maxValue(Coordinate treasure1 ,Coordinate treasure2) {
		int valueT1 = caseMap.get(treasure1).getTreasureValue();
		int valueT2 = caseMap.get(treasure2).getTreasureValue();
		return valueT1 == valueT2 ? 0 : (valueT1 > valueT2 ? 1 : -1);
	}
	
	/**
	 * Génère une liste de tous les trésors dans une zone de 5x5 autour d'une coordonnée.
	 * @param playerCoo
	 * @return treasureList
	 */
	private Set<Coordinate> getTreasureList(Coordinate coo) {
		Set<Coordinate> treasureList = new HashSet<>();
		for(int i = -RANGE; i<= RANGE; i++) {
			for(int j = -RANGE; j <= RANGE; j++) {
				Coordinate treasureCoo = new Coordinate(coo.add(i, j));
				if(caseMap.get(treasureCoo) != null && caseMap.get(treasureCoo).isTreasure())
					treasureList.add(treasureCoo);
			}
		}
		return treasureList;
	}
	
	private static double distanceBetween(Coordinate point1, Coordinate point2) {
		// Pythagore
		return Math.sqrt(
				Math.pow(point1.getRow()-point2.getRow(), 2)+ 
				Math.pow(point1.getCol()-point2.getCol(), 2));
	}
	
	/**
	 * Retourne la direction d'un vecteur à partir d'une coordonées d'origine et une destination
	 * 
	 * @param origin
	 * @param destination
	 * @return SpriType direction
	 */
	public static String getDirectionFromTo(Coordinate origin, Coordinate destination) {
		//substract origin to get value relative to origin
		Coordinate coo = destination.add(- origin.getRow(), - origin.getCol());
		
		return getDirectionFrom00(coo.getRow(), coo.getCol());
	}
	/**
	 * Retourne la direction d'un vecteur qui a comme point de départ 0,0 et unestination donnée
	 * La direction est égale aux noms de l'enum SpriteType comme par exemple NORTH_EAST, SOUTH,...
	 * 
	 * @param row
	 * @param col
	 * @return La direction sous forme de string
	 */
	private static String getDirectionFrom00(int row, int col) {
		//l'axe des Y (row) pointe vers le bas, Nord et Sud sont inversé, le Nord est <0
		String direction = "";
		if (row < 0) direction+= "NORTH";
		if (row > 0) direction+= "SOUTH";
		if ((row*col)!=0) direction+= "_";//si row ou col est égale à 0, on est sur un des 4 axes principales (N,S,E,W), donc pas de tirets
		if (col > 0) direction+= "EAST";
		if (col < 0) direction+= "WEST";
		
		return direction;
	}
	/**
	 * Algorithme permettant de trouver le centre de la carte. si celui-ci est une case vide, il recherche et renvoie la case valide la plus proche
	 * du centre. N'influence pas la map donnée.
	 * @param mapCooList
	 * @return le centre valide de la carte
	 */
	public static Coordinate findMapCenter(Map<Coordinate,Case> map) {
		Set<Coordinate> mapCooList = new HashSet<Coordinate>(map.keySet());
		
		int maxCol = 0;
		int maxRow = 0;
		for (Coordinate coo : mapCooList) {
			maxCol = Math.max(maxCol, coo.getCol());
			maxRow = Math.max(maxRow, coo.getRow());
		}
		Coordinate center = new Coordinate((maxRow+1)/2,(maxCol+1)/2); // le centre est la moitié des row et la moitié des col, +1 car on commence à 0

		return mapCooList.contains(center) == false ? getClosestToCenter(mapCooList, center) : center;
		//si le centre n'est pas dans la liste des cases de la carte, on retourne la case valide la plus proches
	}
	
	/**
	 * Parcours la lists de coordonées pour trouver celle qui est le plus proche du centre
	 * 
	 * @param mapCooList
	 * @param center
	 * @return les coordonées de la case valide la plus proche
	 */
	private static Coordinate getClosestToCenter(Set<Coordinate> mapCooList, Coordinate center) {
		Coordinate closestToCenter = new Coordinate(0,0);
		for (Coordinate potentialClosest : mapCooList) {
			if (minDistance(potentialClosest, closestToCenter, center) == 1) closestToCenter = potentialClosest;
		}
		return closestToCenter;
	}
	/**
	 * @param map
	 * @return deep copy of map
	 */
	public static Map<Coordinate,Case> copyOf(Map<Coordinate,Case> map) {
		Map<Coordinate,Case> newMap = new HashMap<Coordinate,Case>();
		for (Coordinate key : map.keySet()) {
			newMap.put(key, map.get(key).copyOf());
		}
		return newMap;
	}
}
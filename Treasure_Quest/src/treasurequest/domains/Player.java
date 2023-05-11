package treasurequest.domains;


/**
 * Mémorise les données relatives au joueur (sa position, sa bourse)
 * */
public class Player {
	
	private Coordinate coo;
	private int coins;
	
	Player(){
		coo = new Coordinate(0,0);
	}
	
	/**
	 * Retourne les coordonnées du joueur
	 * */
	public Coordinate getCoordinate(){
		return coo;
	}
	/**
	 * Retourne la position en X du joueur
	 * */
	public int getRow(){
		return coo.getRow();
	}
	/**
	 * Retourne la position en Y du joueur
	 * */
	public int getCol(){
		return coo.getCol();
	}
	/**
	 * déplace le joueur à un endroit spécifique
	 * */
	public void setPosition(int row, int col) {
		coo = new Coordinate(row,col);
	}
	/**
	 * déplace le joueur à un endroit spécifique
	 * */
	public void setPosition(Coordinate coo) {
		setPosition(coo.getRow(), coo.getCol());
	}
	/**
	 * Retourne la bourse du joueur
	 * */
	public int getCoins() {
		return coins;
	}
	
	public void setCoins(int amount) {
		this.coins = Math.max(0, amount);
	}
	/**
	 * Ajoute un somme à la bourse du joueur
	 * */
	public void addCoins(int amount) {
		setCoins(coins+amount);
	}
	/**
	 * Le joueur creuse une case spécifique, le coût de la case est déduis de la bourse du joueur
	 * */
	public void dig(Case caz) {
		coins -= caz.getType().getCost();
		caz.dig();
	}
}

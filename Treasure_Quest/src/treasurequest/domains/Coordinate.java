package treasurequest.domains;

import java.util.Objects;


/**
 * Mémorise la position d'un objet, (joueur, case,...)
 * */
public class Coordinate {

	private int row;
	private int col;
	
	Coordinate(){
		this(0,0);
	}
	
	Coordinate(Coordinate coo){
		this(coo.row,coo.col);
	}

	/**
	 * Initialise les coordonées
	 * */
	Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}
	/**
	 * Retourne la position en X
	 * */
	public int getRow() {
		return row;
	}

	/**
	 * Retourne la position en Y
	 * */
	public int getCol() {
		return col;
	}	
	/**
	 * Retourne l'addition des coordonnées
	 * */
	public Coordinate add(int row, int col) {
		return new Coordinate(this.row+row,this.col+col);
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return row == other.row && col == other.col;
	}

	@Override
	public String toString() {
		return "Coordinate [posX=" + row + ", posY=" + col + "]";
	}
	
}

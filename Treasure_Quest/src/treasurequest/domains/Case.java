package treasurequest.domains;

/**
 * Mémorise l'état d'une case (son type, valeur du trésor, si déjà creusée, son indice)
 * */

public class Case {
	
	private String hint; //l'indice sous forme de string de la case (sa direction/un trous)
	private final TileType type;
	private int treasure; //valeur du trésor
	private boolean isDug;
	
	Case(TileType type){
		this.type = type;
		treasure = 0;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public String getHint() {
		return hint;
	}
	
	public TileType getType() {
		return type;
	}
	
	/**
	 * Place un trésor sur cette case
	 * 
	 * @param int value valeur du trésor
	 * */
	public void putTreasure(int value) {
		treasure = value;
	}
	
	/**
	 * retourne le coût pour creuser cette case
	 * */
	public int getCost() {
		return type.getCost();
	}
	
	/**
	 * Creuse la case
	 * */
	public void dig() {
		isDug = true;
	}
	
	/**
	 * retourne vrai si la case est "creusable", faux dans le cas contraire
	 * 
	 * */
	public boolean isDigable() {
		return !isDug && type.isDigable();
	}
	public boolean isTreasure() {
		return treasure>0;
	}
	
	/**
	 * retourne la valeur du trésor
	 * 
	 * */
	public int getTreasureValue() {
		return treasure;
	}
	
	/**
	 * retourne la valeur du trésor et retire le trésor de cette case
	 * 
	 * */
	public int getTreasure() {
		int value = treasure;
		treasure = 0;
		return value;
	}
	
	/**
	 * @return deep copy of this case
	 */
	public Case copyOf() {
		Case cas = new Case(TileType.valueOf(type.name()));
		cas.setHint(hint);
		cas.putTreasure(treasure);
		if(isDug)cas.dig();
		return cas;
	}

//	@Override
//	public String toString() {
//		return "Case [type=" + type + ", treasure=" + treasure + ", isDug=" + isDug + "]";
//	}
	
}

package treasurequest.domains;

/**
 * Mise en page des données du jeu pour faciliter l'affichage
 * */
public class Status {

	public MakeStatus coins;
	public MakeStatus tresr;
	public MakeStatus cost;
	public MakeStatus type;
	
	Status(TreasureQuestGame game) {
		coins = () -> toString("Bourse du joueur : %d P", game.getCoins());
		tresr = () -> toString("Trésors restants : %d", game.map.getTreasureRemaining());
		cost = () -> toString("Coût de la case active : %d P", game.getCostCurrentCase());
		type = () -> toString("Type de la case action : %s", game.getTypeCurrentCase());
	}
	
	/**
	 * Format String with 1 variable
	 * 
	 * (L'affiche de "Impossible" autant pour le coût et la bourse est un choix)
	 * */
	private <T> String toString(String status, T value) {
//		if (value instanceof Integer && (int) value <= -1)
//			return String.format(status.substring(0,status.length()-2), "Impossible").
		return String.format(status, value);
	}

	/**
	 * Interface facilitant l'accès aux phrases du status. à chaque appel d'un objet MakeStatus celui-ci s'actualise grâce
	 * à l'expression lambda. Ce qui évite de faire une fonction par status.
	 * */
	public interface MakeStatus {
		/**
		 * Génère la phrase du status
		 * */
		public String makeStringStatus();
	}
}

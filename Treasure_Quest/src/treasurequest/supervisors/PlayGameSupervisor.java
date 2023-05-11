package treasurequest.supervisors;

import treasurequest.domains.Coordinate;
//import treasurequest.domains.MapTestMaker;
import treasurequest.domains.TreasureQuestGame;
import treasurequest.domains.TreasureQuestGameFactory;
import treasurequest.supervisors.views.PlayGameView;
import treasurequest.supervisors.views.SpriteType;
import treasurequest.supervisors.views.TileType;
import treasurequest.supervisors.views.ViewNames;

/**
 * Réagit aux événements utilisateurs de sa vue en mettant à jour une partie en cours et fournit à sa vue les données à afficher.
 * */
public class PlayGameSupervisor {

	private PlayGameView view;
	
	private TreasureQuestGame game;
	private final TreasureQuestGameFactory gameFactory;
	
	/**
	 * 
	 */
	public PlayGameSupervisor(TreasureQuestGameFactory gameFactory){
		this.gameFactory = gameFactory;
	}
	
	
	/**
	 * Définit la vue de ce superviseur à {@code view}.
	 * */
	public void setView(PlayGameView view) {
		if(view == null) {
			return;
		}
		
		this.view = view;
	}

	/**
	 * Méthode appelée juste avant que la vue de ce superviseur soit affichée à l'écran.
	 * 
	 * Le superviseur affiche les données de départ du jeu (cout de la case active, nombre de trésors, bourse du joueur, etc.).
	 * Il dessine également les cases et indique quelle case est active.
	 * 
	 * RÉPONSE QUESTION 1
	 * Les paramètres de la partie (Player, CaseMap, chaque Case de CaseMap) doivent être correctement initialisé et ne pas être null
	 * ou contenir de valeurs inatendues.
	 * */
	public void onEnter(String fromView) {
		game = gameFactory.makeNewGame();
//		game = gameFactory.makeNewGame(MapTestMaker.map_IT2_Carte1_2);
		
		if (ViewNames.MAIN_MENU.equals(fromView)) {
			// TODO : faire le rendu initial de l'écran de jeu
			view.clearTiles();
			setStatus();
			
			for(Coordinate coo : game.map.getCaseMap().keySet()) {
				TileType type = TileType.valueOf(game.map.getTypeAt(coo).toString());
				setTileAt(type, coo);
			}
		}
	}

	private void setTileAt(TileType type, Coordinate coo) {
		view.setTileAt(type, coo.getRow(),coo.getCol());
		view.setSpriteAt(SpriteType.NONE, coo.getRow(),coo.getCol());
	}

	private void setStatus() {
		view.setPlayerCoins(game.getStatus("Coins"));
		view.setTreasuresCount(game.getStatus("Treasure"));
		view.setActiveCaseCost(game.getStatus("Cost"));
		view.setActiveCaseType(game.getStatus("Type"));
		
		view.setActiveCase(game.getPlayerRow(),game.getPlayerCol());
	}

	/**
	 * Tente de déplacer la case active de {@code deltaRow} lignes et de {@code deltaRow} colonnes.
	 * 
	 * Cette méthode doit vérifier que les coordonnées calculées correspondent bien à une case du terrain.
	 * 
	 * Déplace le joueur 
	 * */
	public void onMove(int deltaRow, int deltaCol) {
		game.movePlayer(deltaRow, deltaCol);
		System.out.println("Player Position :" + game.getPlayerCoordinate());
		setStatus();
	}

	/**
	 * Tente de creuser la case active et met à jour l'affichage en conséquence.
	 * 
	 * Ne fais rien si la case active a déjà été creusee ou si elle est de type Eau.
	 * 
	 * RÉPONSE QUESTION (IT2) 1
	 * quelle(s) postcondition(s) seront respectées par le gain minimum d’un creusage et le gain maximum d’un creusage ?
	 * 
	 * Affichage du résultat du creusage et de la bourse.
	 * */
	public void onDig() {
		String digResult = game.dig();

		if( ! (digResult == null || digResult.isEmpty())) {
			//affichage du résultat du creusage
			view.setSpriteAt(SpriteType.valueOf(digResult), game.getPlayerRow(), game.getPlayerCol());
		}
		setStatus();
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite interrompre la partie.
	 * 
	 * Ce superviseur demande à sa vue de naviguer vers le menu principal.
	 * */
	public void onStop() {
		view.goTo(ViewNames.MAIN_MENU);
	}

}

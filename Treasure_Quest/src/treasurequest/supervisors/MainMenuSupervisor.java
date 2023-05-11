package treasurequest.supervisors;

import java.util.List;

import treasurequest.domains.TreasureQuestGameFactory;
import treasurequest.supervisors.views.*;


/**
 * Fournit les données qu'une vue représenter le menu principal doit afficher.
 * 
 * Réagit aux événements de haut niveau signalé par sa vue.
 * */
public class MainMenuSupervisor {

	private static final int NEW_GAME_ITEM = 0;
//	private static final int RAND_GAME_ITEM = 1; "Partie Aléatoire"
	private static final int EXIT_ITEM = 1;
	
	private final TreasureQuestGameFactory gameFactory;
	
	private MainMenuView view;

	/**
	 * 
	 */
	public MainMenuSupervisor(TreasureQuestGameFactory gameFactory){
		this.gameFactory = gameFactory;
	}
	
	public void setView(MainMenuView view) {
		if(view == null) {
			return;
		}
		
		this.view = view;
		this.view.setItems(List.of("Nouvelle Partie","Quitter"));
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur sélectionne un item.
	 * 
	 * @param itemPos la position de l'item sélectionné. {@code item in [0; items.length[}
	 * */
	public void onItemSelected(int itemPos) {
		if(NEW_GAME_ITEM == itemPos) {
			view.goTo(ViewNames.PLAY_GAME);
			view.onEnter(ViewNames.MAIN_MENU);
		} 
		//TODO later
//		if(RAND_GAME_ITEM == itemPos) {
//			view.goTo(ViewNames.PLAY_GAME);
//		} 
		if(EXIT_ITEM == itemPos) {
			view.confirmExit();
		} 
	}

}

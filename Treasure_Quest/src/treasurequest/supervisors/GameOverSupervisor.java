package treasurequest.supervisors;

import treasurequest.domains.TreasureQuestGameFactory;
import treasurequest.supervisors.views.GameOverView;

/**
 * Réagit aux événements de haut-niveau de sa vue et lui fournit des données à afficher.
 * 
 * */
public class GameOverSupervisor {
	
	private final TreasureQuestGameFactory gameFactory;
	private GameOverView view;

	/**
	 * 
	 * */
	public GameOverSupervisor(TreasureQuestGameFactory gameFactory) {
		this.gameFactory = gameFactory;
	}
	
	public void setView(GameOverView view) {
		if(view == null) {
			return;
		}
		
		this.view = view;
	}
	
	/**
	 * Méthode appelée par la vue quand une navigation vers elle est en cours.
	 * 
	 * @param fromView la vue d'origine. Correspond a priori à une constante définie dans ViewNames.
	 * */
	public void onEnter(String fromView) {
		//TODO : générer les résultats et les afficher.
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite retourner au menu principal.
	 * */
	public void onGoToMain() {
		//TODO : retourner au menu principal
	}
}

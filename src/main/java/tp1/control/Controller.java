package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	


	public void run() {
		view.showWelcome();

		while (!game.isFinished()){
			//pinta marcador y tablero
			view.showGame();

			//lee enter, help o exit
			String[] words = view.getPrompt();
			String cmd = (words.length == 0) ? "": words[0].trim().toLowerCase();

			if ("help".equals(cmd)) {
            	view.showMessage(tp1.view.Messages.HELP);
        	} else if ("exit".equals(cmd)) {
            	break; //sale del juego
        	}

			game.update(); //baja time en 1

			if (game.playerLoses()) {
				view.showMessage("Player loses");
			} else {
				view.showEndMessage();
			}
		}
		
		
		view.showEndMessage();
	}

}

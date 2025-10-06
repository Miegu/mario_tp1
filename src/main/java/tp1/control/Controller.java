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
		boolean repaint = true;
		boolean running = true;
		String finalMsg = null;

		while (running && !game.isFinished()) {
			// pinta marcador y tablero
			if (repaint)
				view.showGame();
			repaint = false;

			// lee enter, help o exit
			String[] words = view.getPrompt();
			String cmd = (words.length == 0) ? "" : words[0].trim().toLowerCase();

			boolean doUpdate = false;

			if ("exit".equals(cmd) || "e".equals(cmd)) {
				finalMsg = "Player leaves game.";
				running = false;

			} else if ("help".equals(cmd) || "h".equals(cmd)) {
				view.showMessage(tp1.view.Messages.HELP);

			} else if ("reset".equals(cmd) || "r".equals(cmd)) {
				if (words.length >= 2) {
					try {
						int lvl = Integer.parseInt(words[1]); //0 o 1
						game.reset(lvl);
					} catch (NumberFormatException e) {
						game.reset();
					}
				} else {
					game.reset();
				}
				repaint = true; //cambia tablero y tiempo

			} else if ("".equals(cmd) || "update".equals(cmd) || "u".equals(cmd)) {
				doUpdate = true;

			} else {
				view.showMessage("Error: Unknown command: " + String.join(" ", words));
			}
			if (doUpdate) {
				game.update();
				repaint = true;
			}
			view.showMessage("Error: Unknown command: " + String.join(" ", words));
		}

		if (finalMsg != null) view.showMessage(finalMsg);//exit
		
		else view.showEndMessage();
	}

}

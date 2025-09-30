package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.logic.Position;
import tp1.logic.gameobjects.Land;
import tp1.view.Messages;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;

	//marcador
	private int time = 10;
	private int points = 0;
	private int lives = 3;

	//estado final
	private boolean finished = false;
	private boolean playerWon = false;
	private boolean playerLost = false;

	private final List<Land> lands = new ArrayList<>();


	
	public Game(int nLevel) {
		for (int col = 0; col < DIM_X; col++) {
			lands.add(new Land(new Position(DIM_Y - 1, col)));
		}

	}
	
	public String positionToString(int col, int row) {
		Position p = new Position(row, col);
    for (Land land : lands) {
        if (land.getPosition().equals(p)) return land.getIcon();
    }
    return Messages.EMPTY;
	}

	public boolean isFinished() { return finished; }

	public boolean playerWins() { return playerWon; }

	public boolean playerLoses() { return playerLost; }  

	public int remainingTime() { return time; }

	public int points() { return points; }

	public int numLives() { return lives; }

	public void update() { // baja el tiempo uno si no ha acabado
		if (finished)
			return;

		if (time > 0) {
			time--;
			if (time == 0) { //game over si no hay tiempo
				finished = true;
				playerLost = true;
			}
		}
	}

	public void loseLife() { // quita vida y si llega a 0 pierde
		if (finished)
			return;
		if (lives > 0)
			lives--;
		if (lives == 0) {
			finished = true;
			playerLost = true;
		}
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}
	
	/*
	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(new Position(13,col)));
			gameObjects.add(new Land(new Position(14,col)));		
		}

		gameObjects.add(new Land(new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(new Position(9,2)));
		gameObjects.add(new Land(new Position(9,5)));
		gameObjects.add(new Land(new Position(9,6)));
		gameObjects.add(new Land(new Position(9,7)));
		gameObjects.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);

		gameObjects.add(new Goomba(this, new Position(0, 19)));
	}
	*/
}

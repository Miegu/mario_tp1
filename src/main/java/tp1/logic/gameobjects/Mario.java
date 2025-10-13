package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.Game;

public class Mario {

	public enum Facing{ LEFT, RIGHT};

	private final Game game;
	private Position pos;
	private int dx = +1;
	private Facing facing = Facing.RIGHT;
	private boolean big = false;  //si fuera true, ocuparia la tile de arriba

	
	public Mario(Game game, Position pos) {
		this.game = game;
		this.pos= pos;
	}
	public Position getPosition() {
   	 	return pos;
	}
	public Facing getFacing(){
		return facing;
	}
	public void setFacing(Facing f){
		this.facing = f;
	}

	public boolean occupies(Position p) { //si es big tmb el la de arriba
        if (p.equals(pos)) return true;
        return big && p.equals(pos.translate(0, -1));
   	}

	public boolean isBig() {
		return big;
	}


	public void setBig(boolean b) {
		this.big = b;
	}

	public String getIcon() {
		return (facing == Facing.LEFT) // condicion
				? tp1.view.Messages.MARIO_LEFT // si true
				: tp1.view.Messages.MARIO_RIGHT; // si false
	}
	
	/**
	 *  Implements the automatic update	
	 */
	
	public void update() {
		boolean moved = false;

		while (!game.getActions().isEmpty()) {
			Action act = game.getActions().extract();

			switch (act) {
				case LEFT:
					dx = -1;
					pos = pos.translate(dx, 0);
					moved = true;
					break;

				case RIGHT:
					dx = 1;
					pos = pos.translate(dx, 0);
					moved = true;
					break;

				case UP:
					pos = pos.translate(0, -1);
					moved = true;
					break;

				case DOWN:
					while (!tp1.view.Messages.LAND.equals(
							game.positionToString(pos.getCol(), pos.getRow() + 1))) {
						pos = pos.translate(0, 1);
					}
					dx = 0; //para x
					moved = true;
					break;

				case STOP:
					dx = 0;
					break;
			}
		}

		//movimiento automático
		if (!moved) {
			int r = pos.getRow();
			int c = pos.getCol();

			String below = game.positionToString(c, r + 1);
			boolean hasFloor = tp1.view.Messages.LAND.equals(below);

			if (!hasFloor) {
				pos = new Position(r + 1, c);
				if (pos.getRow() >= Game.DIM_Y) {
					game.marioDies();
				}
				return;
			}

			int nextC = c + dx;
			boolean hitsWall = (nextC < 0 || nextC >= Game.DIM_X);
			boolean landAhead = !hitsWall && tp1.view.Messages.LAND.equals(
					game.positionToString(nextC, r));

			if (hitsWall || landAhead) {
				dx = -dx;
			} else {
				pos = new Position(r, nextC);
			}
		}

		if (game.getGameObjectContainer().goombaAt(pos)) {  	//compruebo si le da a un bicho
			game.marioDies();
			return;
		}

		if (big) {  //si es grande tmb casilla de arriba
			Position up = pos.translate(0, -1);
			if (game.getGameObjectContainer().goombaAt(up)) {
				game.marioDies();
			}
		}

		if (game.getGameObjectContainer().exitDoorAt(pos)) {
			game.setPlayerWon();
		}

		game.doInteractionsFrom(this);


	}

	public boolean interactWith(Goomba other) {
		if (pos.equals(other.getPosition()) ||
				(isBig() && pos.translate(0, -1).equals(other.getPosition()))) {

			boolean falling = !game.getGameObjectContainer().isSolidAt(pos.translate(0, 1));

			if (falling) {
				other.receiveInteraction(this);
			} else if (isBig()) {
				big = false; //se hace chiquito
				other.receiveInteraction(this);
			} else {
				game.marioDies(); //pierde vida y reinicia
				return true;
			}
			return true;
		}
		return false;
	}

}

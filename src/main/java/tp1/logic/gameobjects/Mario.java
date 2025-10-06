package tp1.logic.gameobjects;

import tp1.logic.Position;

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

	public String getIcon() {
		return (facing == Facing.LEFT) // condicion
				? tp1.view.Messages.MARIO_LEFT // si true
				: tp1.view.Messages.MARIO_RIGHT; // si false
	}
	
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		int r = pos.getRow();
		int c = pos.getCol();

		String below = game.positionToString(c, r + 1);
		boolean hasFloor = tp1.view.Messages.LAND.equals(below);

		if (!hasFloor) {
			pos = new Position(r + 1, c);//cae 1

			//fuera tablero muere jaja
			if (pos.getRow() >= Game.DIM_Y) {
				game.marioDies(); //pierde vida y reset
			}

			return;
		}

		//suelo
		int nextC = c + dx;
		boolean hitsWall = (nextC < 0 || nextC >= Game.DIM_X);
		boolean landAhead = !hitsWall && tp1.view.Messages.LAND.equals(
				game.positionToString(nextC, r));

		if (hitsWall || landAhead) {
			dx = -dx; //se giraa
			return;
		}
		pos = new Position(r, nextC); //avanza
	}
}

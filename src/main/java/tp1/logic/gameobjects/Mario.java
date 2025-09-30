package tp1.logic.gameobjects;

import tp1.logic.Position;

import tp1.logic.Game;

public class Mario {

	public enum Facing{ LEFT, RIGHT};

	private Position pos;
	private Facing facing = Facing.RIGHT;
	private boolean big = false;  //si fuera true, ocuparia la tile de arriba

	public Mario(Position pos) {
		this.pos = pos;
	}
	public Mario(Position pos, boolean big){
		this.pos = pos;
		this.big = big;
	}
	public Position getPosition() {
		return pos;
	}
	public boolean isBig(){
		return big;
	}
	public void setBig(boolean b){
		this.big = b;
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
		//TODO fill your code
	}
}

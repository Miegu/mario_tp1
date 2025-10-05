package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public class Goomba {
    private Position pos;

    public Goomba(Position pos) {
        this.pos = pos;
    }
    public Goomba(Game game, Position pos) { this(pos); }

    public Position getPosition() {
        return pos;
    }

    public void setPosition(Position p) {
        this.pos = p;
    } //movimiento mas adelante

    public String getIcon() {
        return tp1.view.Messages.GOOMBA;
    }
}


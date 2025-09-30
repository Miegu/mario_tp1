package tp1.logic;

import java.util.Objects;

/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public final class Position {

	private final int col;
	private final int row;

	public Position(int row, int col){
		this.row = row;
		this.col = col;
	}

	public int getRow() { return row; }
	public int getCol() { return col; }
	
	public Position translate(int dx, int dy){
		return new Position(row + dy, col + dx);  //dy filas, dx columnas
	}

	public boolean isInBounds(int width, int height){
		return (row >= 0 && row < height && col >= 0 && col < width);
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return  true; //mismo objeto, iguales
		if (!(o instanceof Position)) return false; //si no es position no
        Position p = (Position) o; 
        return row == p.row && col == p.col;  //comparo que sean iguales
	}
	@Override 
	public int hashCode() { return Objects.hash(row, col); }
    @Override 
	public String toString() { return "(" + row + "," + col + ")"; }

}

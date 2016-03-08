import java.util.ArrayList;

public abstract class Validator {
	
	protected boolean hasMoved;
	
	public abstract ArrayList<int[]> highlightBoard(int x, int y, boolean isWhite);
	
	public void inCheck() {
	}
	
	public void checkMate() {
	}
	
	public void setHasMoved(boolean moved) {
		hasMoved = moved;
	}
	public boolean getEnPassant() {
		return false;
	}
	public void setEnPassant(boolean b) {
	}
	public boolean canCastle() {
		return false;
	}
}

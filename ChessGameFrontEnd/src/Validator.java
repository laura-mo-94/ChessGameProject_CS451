//package src;

public abstract class Validator {
	
	protected boolean hasMoved;
	
	public abstract void highlightBoard(int x, int y, boolean isWhite);
	
	public void inCheck() {
	}
	
	public void checkMate() {
	}
	
	public void setHasMoved(boolean moved) {
		hasMoved = moved;
	}
}
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

	public void print() {
		// TODO Auto-generated method stub
		
	}
}

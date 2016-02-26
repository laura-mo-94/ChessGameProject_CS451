import java.util.ArrayList;

public class PawnValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	// Constructor
	public PawnValidator() {

	}
	
	public boolean enPassant() {
		return isWhite;
	}
	
	public void promotion() {
		
	}

	@Override
	public void highlightBoard(int x, int y, boolean isWhite) {

	}
}

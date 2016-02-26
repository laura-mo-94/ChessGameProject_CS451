import java.util.ArrayList;

public class KingValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	// Constructor
	public KingValidator() {

	}
	
	public boolean canCastle() {
		return isWhite;
	}

	@Override
	public void highlightBoard(int x, int y, boolean isWhite) {

	}
}

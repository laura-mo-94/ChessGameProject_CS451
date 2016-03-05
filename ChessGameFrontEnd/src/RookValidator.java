import java.util.ArrayList;

public class RookValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	// Constructor
	public RookValidator() {
	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		for(int i=0; i<=7; i++)
		{
			returnMoves.add(new int [] {xPos, i});
		}
		
		for(int i=0; i<=7; i++)
		{
			returnMoves.add(new int [] {i, yPos});
		}
		
		return returnMoves;
	}
}

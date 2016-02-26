import java.util.ArrayList;

public class PawnValidator extends Validator  {
	
	/* EXTEND VALIDATION TO INCLUDE PROMOTION VARIABLE
	 * CHECKING WHEN THE PAWN REACHES THE END OF IT'S RANK.
	 */

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	private boolean enPassant;
	
	// Constructor
	public PawnValidator() {

	}
	
	public boolean getEnPassant()
	{
		return enPassant;
	}
	
	public void setEnPassant(boolean b)
	{
		this.enPassant = b;
	}
	
	public boolean enPassant() {
		return isWhite;
	}
	
	public void promotion() {
		
	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		if(isWhite)
		{
			if(yPos == 1)
			{
				returnMoves.add(new int [] {xPos, 3});
				enPassant = true;
			}
			
			returnMoves.add(new int [] {xPos, 2});
		}
		else if(!isWhite)
		{
			if(yPos == 6)
			{
				returnMoves.add(new int [] {5, yPos});
				enPassant = true;
			}
			
			returnMoves.add(new int [] {4, yPos});
		}
		
		return returnMoves;
	}
}

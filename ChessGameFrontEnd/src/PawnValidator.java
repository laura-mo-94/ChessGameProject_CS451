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
			if(y == 6)
			{
				if(Board.boardState[x][y-2] == null)
					returnMoves.add(new int [] {x, y-2});
				enPassant = true;
			}
			
			if(Board.boardState[x][y-1] == null)
				returnMoves.add(new int [] {x, y-1});
		}
		else if(!isWhite)
		{
			if(y == 1)
			{
				if(Board.boardState[x][y+2] == null)
					returnMoves.add(new int [] {x, y+2});
				enPassant = true;
			}
			
			if(Board.boardState[x][y+1] == null)
				returnMoves.add(new int [] {x, y+1});
		}
		
		
		
		
		return returnMoves;
	}
}

import java.util.ArrayList;

public class BishopValidator extends Validator  {
	
	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	private int [][] moveRange = new int [][] {{-1, 1},{1, 1},{-1, -1},{1, -1}};
	
	PieceType type;
	
	// Constructor
	public BishopValidator() {

	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		for(int i=0; i <= 7; i++)
		{
			for(int j=0; j <= 7; j++)
			{
				int diffRow = Math.abs(i-xPos);
				int diffCol = Math.abs(j-yPos);
				
				if(diffRow == diffCol)
					returnMoves.add(new int [] {i, j});
			}
		}
		
		return returnMoves;
	}
}

import java.util.ArrayList;

public class QueenValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	// Constructor
	public QueenValidator() {

	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		for(int i=0; i <= 7; i++)
		{
			for(int j=0; j <= 7; j++)
			{
				int diffRow = Math.abs(i-x);
				int diffCol = Math.abs(j-y);
				
				if(diffRow == diffCol)
					returnMoves.add(new int [] {i, j});
			}
		}
		
		for(int i=0; i<=7; i++)
		{
			returnMoves.add(new int [] {x, i});
		}
		
		for(int i=0; i<=7; i++)
		{
			returnMoves.add(new int [] {i, y});
		}
		
		for (int i = 0; i < returnMoves.size(); i++) {
			//ChessButton tempButton = ChessGUI.chessBoardSquares[returnMoves.get(i)[0]][returnMoves.get(i)[1]];
			System.out.println("(" + returnMoves.get(i)[0] + ", " + returnMoves.get(i)[1] + ")");
		}
		
		return returnMoves;
	}
}

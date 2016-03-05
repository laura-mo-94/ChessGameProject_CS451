import java.util.ArrayList;
import java.util.List;

public class KnightValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	private int [][] moveRange = new int [][] {{2,1},{1,2},{1,-2},{2,-1},{-2,1},{-1,2},{-1,-2},{-2,1}};
	
	PieceType type;
	
	// Constructor
	public KnightValidator() {
	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		for(int i=0; i < 7; i++)
		{
			if((0 <= (x + moveRange[i][0])) && ((x + moveRange[i][0]) <= 7)) {
				if((0 <= (y + moveRange[i][1])) && ((y + moveRange[i][1]) <= 7)) {
					returnMoves.add(moveRange[i]);
				}
			}
		}
		
		for (int i = 0; i < returnMoves.size(); i++) {
			//ChessButton tempButton = ChessGUI.chessBoardSquares[returnMoves.get(i)[0]][returnMoves.get(i)[1]];
			System.out.println("(" + returnMoves.get(i)[0] + ", " + returnMoves.get(i)[1] + ")");
		}
		
		return returnMoves;
	}
}

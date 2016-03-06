import java.util.ArrayList;
import java.util.List;

public class KnightValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	private int [][] moveRange = new int [][] {{2,1},{1,2},{1,-2},{2,-1},{-2,-1},{-1,2},{-1,-2},{-2,1}};
	
	PieceType type;
	
	// Constructor
	public KnightValidator() {
	}

	@Override
	public ArrayList<int []> highlightBoard(int x, int y, boolean isWhite) {
		
		ArrayList<int []> returnMoves = new ArrayList<int []>();
		
		for (int i=0; i <= 7; i++)
		{
			if ((0 <= (x + moveRange[i][0])) && ((x + moveRange[i][0]) <= 7)) {
				if ((0 <= (y + moveRange[i][1])) && ((y + moveRange[i][1]) <= 7)) {
					System.out.println(moveRange[i][0] + ", " + moveRange[i][1] +  " added");
					int[] tempArr = new int[] {x + moveRange[i][0], y + moveRange[i][1]};
					returnMoves.add(tempArr);
				}
			}
		}
		
		for (int i = 0; i < returnMoves.size(); i++) {
			//ChessButton tempButton = ChessGUI.chessBoardSquares[returnMoves.get(i)[0]][returnMoves.get(i)[1]];
			System.out.println("(" + returnMoves.get(i)[0] + ", " + returnMoves.get(i)[1] + ")");
		}
		
		ArrayList<int []> tempArr = new ArrayList<int []>();
		for (int i=0; i < returnMoves.size(); i++)
		{
			if(isWhite)
			{
				if(Board.boardState[returnMoves.get(i)[0]][returnMoves.get(i)[1]] == null)
					tempArr.add(new int [] {returnMoves.get(i)[0], returnMoves.get(i)[1]});
				else if(Board.boardState[returnMoves.get(i)[0]][returnMoves.get(i)[1]].getIsWhite() == false)
					tempArr.add(new int [] {returnMoves.get(i)[0], returnMoves.get(i)[1]});
				
			}
			else if(!isWhite)
			{
				if(Board.boardState[returnMoves.get(i)[0]][returnMoves.get(i)[1]] == null)
					tempArr.add(new int [] {returnMoves.get(i)[0], returnMoves.get(i)[1]});
				else if(Board.boardState[returnMoves.get(i)[0]][returnMoves.get(i)[1]].getIsWhite() == true)
					tempArr.add(new int [] {returnMoves.get(i)[0], returnMoves.get(i)[1]});
				
			}
		}
		
		returnMoves = tempArr;
		
		return returnMoves;
	}
}

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
		
		if (isWhite) {
			
			// x up
			for(int i=x; i<=7; i++) {
				if (Board.boardState[i][y] != null)
					returnMoves.add(new int [] {i, y});
				if (Board.boardState[i][y].getIsWhite() == false)
					break;
				if (i < 7) {
					if (Board.boardState[i+1][y].getIsWhite() == true)
						break;
				}
			}
			
			// x down
			for(int i=x; i>=0; i--) {
				if (Board.boardState[i][y] != null)
					returnMoves.add(new int [] {i, y});
				if (Board.boardState[i][y].getIsWhite() == false)
					break;
				if (i > 0) {
					if (Board.boardState[i-1][y].getIsWhite() == true)
						break;
				}
			}
			
			// y up
			for(int i=y; i<=7; i++) {
				if (Board.boardState[x][i] != null)
					returnMoves.add(new int [] {x, i});
				if (Board.boardState[x][i].getIsWhite() == false)
					break;
				if (i < 0) {
					if (Board.boardState[x][i+1].getIsWhite() == true)
						break;
				}
			}
			
			// y down
			for(int i=y; i>=0; i--) {
				if (Board.boardState[x][i] != null)
					returnMoves.add(new int [] {x, i});
				if (Board.boardState[x][i].getIsWhite() == false || Board.boardState[x][i] != null)
					break;
				if (i > 7) {
					if (Board.boardState[x][i-1].getIsWhite() == true)
						break;
						break;
				}
			}
		}
		
		if (!isWhite) {
			// x
			for(int i=x; i<=7; i++) {
				if (Board.boardState[i][y].getIsWhite() == true)
					break;
				if (Board.boardState[i][y] != null)
					returnMoves.add(new int [] {i, y});
				if (Board.boardState[i][y].getIsWhite() == false)
					break;
			}
			
			for(int i=x; i>=0; i--) {
				if (Board.boardState[i][y].getIsWhite() == true)
					break;
				if (Board.boardState[i][y] != null)
					returnMoves.add(new int [] {i, y});
				if (Board.boardState[i][y].getIsWhite() == false)
					break;
			}
			
			// y
			for(int i=y; i<=7; i++) {
				if (Board.boardState[x][i].getIsWhite() == true)
					break;
				if (Board.boardState[x][i] != null)
					returnMoves.add(new int [] {x, i});
				if (Board.boardState[x][i].getIsWhite() == false)
					break;
			}
			
			for(int i=y; i>=0; i--) {
				if (Board.boardState[x][i].getIsWhite() == true)
					break;
				if (Board.boardState[x][i] != null)
					returnMoves.add(new int [] {x, i});
				if (Board.boardState[x][i].getIsWhite() == false)
					break;
			}
		}
		
		return returnMoves;
	}
}

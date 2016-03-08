import java.util.ArrayList;

public class PawnValidator extends Validator  {
	
	/* EXTEND VALIDATION TO INCLUDE PROMOTION VARIABLE
	 * CHECKING WHEN THE PAWN REACHES THE END OF IT'S RANK.
	 */

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	boolean canMoveTwo = false;
	
	PieceType type;
	
	private boolean enPassant;
	
	// Constructor
	public PawnValidator() {

	}
	@Override
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
			if(y-1 > -1)
				if(Board.boardState[x][y-1] == null)
				{
					if((y != 4) && enPassant == true) {
						enPassant = false;
					}
					returnMoves.add(new int [] {x, y-1});
					canMoveTwo = true;
				}
			if((y < 7) && (y > 0))
			{
				if(x == 0) {
					if((Board.boardState[x+1][y-1] != null) && (Board.boardState[x+1][y-1].getIsWhite() == false) && (x+1 < 8))
						returnMoves.add(new int [] {x+1, y-1});
				} else {
					if(x == 7) {
						if((Board.boardState[x-1][y-1] != null) && (Board.boardState[x-1][y-1].getIsWhite() == false) && (x-1 > -1))
							returnMoves.add(new int [] {x-1, y-1});
					} else {
						if((Board.boardState[x-1][y-1] != null) && (Board.boardState[x-1][y-1].getIsWhite() == false) && (x-1 > -1))
							returnMoves.add(new int [] {x-1, y-1});
						if((Board.boardState[x+1][y-1] != null) && (Board.boardState[x+1][y-1].getIsWhite() == false) && (x+1 < 8))
							returnMoves.add(new int [] {x+1, y-1});
					}
				}
			}
			if(y == 6 && canMoveTwo == true)
			{
				if(Board.boardState[x][y-2] == null && Board.boardState[x][y-1] == null) {
					returnMoves.add(new int [] {x, y-2});
					enPassant = true;
				}
			}
			if(y == 3) {
				if(x == 0) {
					if((Board.boardState[x+1][y] != null) && (Board.boardState[x+1][y].getIsWhite() == false) && (Board.boardState[x+1][y].getType() == PieceType.PAWN)) {
						if(Board.boardState[x+1][y].getValidator().getEnPassant()) {
							returnMoves.add(new int [] {x+1, y});
						}
					}
				} else {
					if(x == 7) {
						if((Board.boardState[x-1][y] != null) && (Board.boardState[x-1][y].getIsWhite() == false) && (Board.boardState[x-1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x-1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x-1, y});
							}
						}
					} else {
						if((Board.boardState[x+1][y] != null) && (Board.boardState[x+1][y].getIsWhite() == false) && (Board.boardState[x+1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x+1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x+1, y});
							}
						}
						if((Board.boardState[x-1][y] != null) && (Board.boardState[x-1][y].getIsWhite() == false) && (Board.boardState[x-1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x-1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x-1, y});
							}
						}
					}
				}
			}
		}
		else if(!isWhite)
		{
			if(y+1 < 8)
				if(Board.boardState[x][y+1] == null)
				{	
					if((y != 3) && enPassant == true) {
						enPassant = false;
					}
					returnMoves.add(new int [] {x, y+1});
					canMoveTwo = true;
				}
			if((y < 7) && (y > 0))
			{
				if(x == 0) {
					if((Board.boardState[x+1][y+1] != null) && (Board.boardState[x+1][y+1].getIsWhite() == true) && (x+1 < 8))
						returnMoves.add(new int [] {x+1, y+1});
				} else {
					if(x == 7) {
						if((Board.boardState[x-1][y+1] != null) && (Board.boardState[x-1][y+1].getIsWhite() == true) && (x-1 > -1))
							returnMoves.add(new int [] {x-1, y+1});
					} else {
						if((Board.boardState[x-1][y+1] != null) && (Board.boardState[x-1][y+1].getIsWhite() == true) && (x-1 > -1))
							returnMoves.add(new int [] {x-1, y+1});
						if((Board.boardState[x+1][y+1] != null) && (Board.boardState[x+1][y+1].getIsWhite() == true) && (x+1 < 8))
							returnMoves.add(new int [] {x+1, y+1});
					}
				}
			}
			if(y == 1 && canMoveTwo == true)
			{
				if(Board.boardState[x][y+2] == null && Board.boardState[x][y+1] == null) {
					returnMoves.add(new int [] {x, y+2});
					enPassant = true;
				}
			}
			if(y == 4) {
				if(x == 0) {
					if((Board.boardState[x+1][y] != null) && (Board.boardState[x+1][y].getIsWhite() == true) && (Board.boardState[x+1][y].getType() == PieceType.PAWN)) {
						if(Board.boardState[x+1][y].getValidator().getEnPassant() == true) {
							returnMoves.add(new int [] {x+1, y});
						}
					}
				} else {
					if(x == 7) {
						if((Board.boardState[x-1][y] != null) && (Board.boardState[x-1][y].getIsWhite() == true) && (Board.boardState[x-1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x-1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x-1, y});
							}
						}
					} else {
						if((Board.boardState[x+1][y] != null) && (Board.boardState[x+1][y].getIsWhite() == true) && (Board.boardState[x+1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x+1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x+1, y});
							}
						}
						if((Board.boardState[x-1][y] != null) && (Board.boardState[x-1][y].getIsWhite() == true) && (Board.boardState[x-1][y].getType() == PieceType.PAWN)) {
							if(Board.boardState[x-1][y].getValidator().getEnPassant() == true) {
								returnMoves.add(new int [] {x-1, y});
							}
						}
					}
				}
			}
		}
		return returnMoves;
	}
}

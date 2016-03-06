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
			for(int i=x+1; i<=7; i++) {
				if (Board.boardState[i][y] == null) {
					returnMoves.add(new int [] {i, y});
				} else {
					if(Board.boardState[i][y].getIsWhite() == false) {
						returnMoves.add(new int [] {i, y});
						break;
					} else {
						if (Board.boardState[i][y].getIsWhite() == true) {
							break;
						}
					}
				}
			}
			
			// x down
			for(int i=x-1; i>=0; i--) {
				if (Board.boardState[i][y] == null) {
					returnMoves.add(new int [] {i, y});
				} else {
					if(Board.boardState[i][y].getIsWhite() == false) {
						returnMoves.add(new int [] {i, y});
						break;
					} else {
						if (Board.boardState[i][y].getIsWhite() == true) {
							break;
						}
					}
				}
			}
			
			// y up
			for(int i=y+1; i<=7; i++) {
				if (Board.boardState[x][i] == null) {
					returnMoves.add(new int [] {x, i});
				} else {
					if(Board.boardState[x][i].getIsWhite() == false) {
						returnMoves.add(new int [] {x, i});
						break;
					} else {
						if (Board.boardState[x][i].getIsWhite() == true) {
							break;
						}
					}
				}
			}
			
			// y down
			for(int i=y-1; i>=0; i--) {
				if (Board.boardState[x][i] == null) {
					returnMoves.add(new int [] {x, i});
				} else {
					if(Board.boardState[x][i].getIsWhite() == false) {
						returnMoves.add(new int [] {x, i});
						break;
					} else {
						if (Board.boardState[x][i].getIsWhite() == true) {
							break;
						}
					}
				}
			}
		}
		
		if (!isWhite) {
			// x
			for(int i=x+1; i<=7; i++) {
				if (Board.boardState[i][y] == null) {
					returnMoves.add(new int [] {i, y});
				} else {
					if(Board.boardState[i][y].getIsWhite() == true) {
						returnMoves.add(new int [] {i, y});
						break;
					} else {
						if (Board.boardState[i][y].getIsWhite() == false) {
							break;
						}
					}
				}
			}
			
			for(int i=x-1; i>=0; i--) {
				if (Board.boardState[i][y] == null) {
					returnMoves.add(new int [] {i, y});
				} else {
					if(Board.boardState[i][y].getIsWhite() == true) {
						returnMoves.add(new int [] {i, y});
						break;
					} else {
						if (Board.boardState[i][y].getIsWhite() == false) {
							break;
						}
					}
				}
			}
			
			// y
			for(int i=y+1; i<=7; i++) {
				if (Board.boardState[x][i] == null) {
					returnMoves.add(new int [] {x, i});
				} else {
					if(Board.boardState[x][i].getIsWhite() == true) {
						returnMoves.add(new int [] {x, i});
						break;
					} else {
						if (Board.boardState[x][i].getIsWhite() == false) {
							break;
						}
					}
				}
			}
			
			for(int i=y-1; i>=0; i--) {
				if (Board.boardState[x][i] == null) {
					returnMoves.add(new int [] {x, i});
				} else {
					if(Board.boardState[x][i].getIsWhite() == true) {
						returnMoves.add(new int [] {x, i});
						break;
					} else {
						if (Board.boardState[x][i].getIsWhite() == false) {
							break;
						}
					}
				}
			}
		}
		
		return returnMoves;
	}
}

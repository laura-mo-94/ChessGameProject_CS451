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
		if(isWhite) {
			// UpLeft
			for(int i=x-1; i>=0; i--) {
				for(int j=y-1; j>=0; j--) {
					int diffRow = Math.abs(i-x);
					int diffCol = Math.abs(j-y);
					if(diffRow == diffCol) {
						if (Board.boardState[i][j] == null) {
							returnMoves.add(new int [] {i, j});
						} else {
							if(Board.boardState[i][j].getIsWhite() == false) {
								returnMoves.add(new int [] {i, j});
								break;
							} else {
								if (Board.boardState[i][j].getIsWhite() == true) {
									break;
								}
							}
						}
					}
				}
			}
			// UpRight
			for(int i=x+1; i<=7; i++) {
				for(int j=y-1; j>=0; j--) {
					int diffRow = Math.abs(i-x);
					int diffCol = Math.abs(j-y);
					if(diffRow == diffCol) {
						if (Board.boardState[i][j] == null) {
							returnMoves.add(new int [] {i, j});
						} else {
							if(Board.boardState[i][j].getIsWhite() == false) {
								returnMoves.add(new int [] {i, j});
								break;
							} else {
								if (Board.boardState[i][j].getIsWhite() == true) {
									break;
								}
							}
						}
					}
				}
			}
			// DownRight
			for(int i=x+1; i<=7; i++) {
				for(int j=y+1; j<=7; j++) {
					int diffRow = Math.abs(i-x);
					int diffCol = Math.abs(j-y);
					if(diffRow == diffCol) {
						if (Board.boardState[i][j] == null) {
							returnMoves.add(new int [] {i, j});
						} else {
							if(Board.boardState[i][j].getIsWhite() == false) {
								returnMoves.add(new int [] {i, j});
								break;
							} else {
								if (Board.boardState[i][j].getIsWhite() == true) {
									break;
								}
							}
						}
					}
				}
			}
			// DownLeft
			for(int i=x-1; i>=0; i--) {
				for(int j=y+1; j<=7; j++) {
					int diffRow = Math.abs(i-x);
					int diffCol = Math.abs(j-y);
					if(diffRow == diffCol) {
						if (Board.boardState[i][j] == null) {
							returnMoves.add(new int [] {i, j});
						} else {
							if(Board.boardState[i][j].getIsWhite() == false) {
								returnMoves.add(new int [] {i, j});
								break;
							} else {
								if (Board.boardState[i][j].getIsWhite() == true) {
									break;
								}
							}
						}
					}
				}
			}
		} else {
			if(!isWhite) {
				// UpLeft
				for(int i=x-1; i>=0; i--) {
					for(int j=y-1; j>=0; j--) {
						int diffRow = Math.abs(i-x);
						int diffCol = Math.abs(j-y);
						if(diffRow == diffCol) {
							if (Board.boardState[i][j] == null) {
								returnMoves.add(new int [] {i, j});
							} else {
								if(Board.boardState[i][j].getIsWhite() == true) {
									returnMoves.add(new int [] {i, j});
									break;
								} else {
									if (Board.boardState[i][j].getIsWhite() == false) {
										break;
									}
								}
							}
						}
					}
				}
				// UpRight
				for(int i=x+1; i<=7; i++) {
					for(int j=y-1; j>=0; j--) {
						int diffRow = Math.abs(i-x);
						int diffCol = Math.abs(j-y);
						if(diffRow == diffCol) {
							if (Board.boardState[i][j] == null) {
								returnMoves.add(new int [] {i, j});
							} else {
								if(Board.boardState[i][j].getIsWhite() == true) {
									returnMoves.add(new int [] {i, j});
									break;
								} else {
									if (Board.boardState[i][j].getIsWhite() == false) {
										break;
									}
								}
							}
						}
					}
				}
				// DownRight
				for(int i=x+1; i<=7; i++) {
					for(int j=y+1; j<=7; j++) {
						int diffRow = Math.abs(i-x);
						int diffCol = Math.abs(j-y);
						if(diffRow == diffCol) {
							if (Board.boardState[i][j] == null) {
								returnMoves.add(new int [] {i, j});
							} else {
								if(Board.boardState[i][j].getIsWhite() == true) {
									returnMoves.add(new int [] {i, j});
									break;
								} else {
									if (Board.boardState[i][j].getIsWhite() == false) {
										break;
									}
								}
							}
						}
					}
				}
				// DownLeft
				for(int i=x-1; i>=0; i--) {
					for(int j=y+1; j<=7; j++) {
						int diffRow = Math.abs(i-x);
						int diffCol = Math.abs(j-y);
						if(diffRow == diffCol) {
							if (Board.boardState[i][j] == null) {
								returnMoves.add(new int [] {i, j});
							} else {
								if(Board.boardState[i][j].getIsWhite() == true) {
									returnMoves.add(new int [] {i, j});
									break;
								} else {
									if (Board.boardState[i][j].getIsWhite() == false) {
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return returnMoves;
	}
}

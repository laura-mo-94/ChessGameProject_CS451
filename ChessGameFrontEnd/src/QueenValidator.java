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
		boolean breakForLoop = false;
		if(isWhite) {
			// UpLeft
			for(int i=x-1; i>=0; i--) {
				if(breakForLoop == false) {
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
										breakForLoop = true;
										break;
									}
								}
							}
						}
					}
				} else {
					breakForLoop = false;
					break;
				}
			}
			// UpRight
			for(int i=x+1; i<=7; i++) {
				if(breakForLoop == false) {
					for(int j=y-1; j>=0; j--) {
						System.out.println("Poop!");
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
										breakForLoop = true;
										break;
									}
								}
							}
						}
					}
				} else {
					breakForLoop = false;
					break;
				}
			}
			// DownRight
			for(int i=x+1; i<=7; i++) {
				if(breakForLoop == false) {
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
										breakForLoop = true;
										break;
									}
								}
							}
						}
					}
				} else {
					breakForLoop = false;
					break;
				}
			}
			// DownLeft
			for(int i=x-1; i>=0; i--) {
				if(breakForLoop == false) {
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
										breakForLoop = true;
										break;
									}
								}
							}
						}
					}
				} else {
					breakForLoop = false;
					break;
				}
			}
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
		} else {
			if(!isWhite) {
				// UpLeft
				for(int i=x-1; i>=0; i--) {
					if(breakForLoop == false) {
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
											breakForLoop = true;
											break;
										}
									}
								}
							}
						}
					} else {
						breakForLoop = false;
						break;
					}
				}
				// UpRight
				for(int i=x+1; i<=7; i++) {
					if(breakForLoop == false) {
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
											breakForLoop = true;
											break;
										}
									}
								}
							}
						}
					} else {
						breakForLoop = false;
						break;
					}
				}
				// DownRight
				for(int i=x+1; i<=7; i++) {
					if(breakForLoop == false) {
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
											breakForLoop = true;
											break;
										}
									}
								}
							}
						}
					} else {
						breakForLoop = false;
						break;
					}
				}
				// DownLeft
				for(int i=x-1; i>=0; i--) {
					if(breakForLoop == false) {
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
											breakForLoop = true;
											break;
										}
									}
								}
							}
						}
					} else {
						breakForLoop = false;
						break;
					}
				}
			}
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

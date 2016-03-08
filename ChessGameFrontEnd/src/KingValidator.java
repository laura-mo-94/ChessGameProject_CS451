import java.util.ArrayList;

public class KingValidator extends Validator  {

	// Fields
	int xPos;
	int yPos;
	
	boolean isWhite;
	
	PieceType type;
	
	// Constructor
	public KingValidator() {

	}
	@Override
	public boolean canCastle() {
		return isWhite;
	}

	@Override
	public ArrayList<int[]> highlightBoard(int x, int y, boolean isWhite) {
		ArrayList<int[]> returnMoves = new ArrayList<int[]>();
		
		for(int i=0; i<=7; i++) {
			if ((0 <= (x - 1)) && ((x - 1) <= 7) && (0 <= (y + 1)) && ((y + 1) <= 7)) 
				returnMoves.add(new int[] {x-1, y+1});
			if ((0 <= (x)) && ((x) <= 7) && (0 <= (y + 1)) && ((y + 1) <= 7)) 
				returnMoves.add(new int[] {x, y+1});
			if ((0 <= (x + 1)) && ((x + 1) <= 7) && (0 <= (y + 1)) && ((y + 1) <= 7)) 
				returnMoves.add(new int[] {x+1, y+1});
			
			if ((0 <= (x - 1)) && ((x - 1) <= 7) && (0 <= (y)) && ((y) <= 7)) 
				returnMoves.add(new int[] {x-1, y});
			if ((0 <= (x + 1)) && ((x + 1) <= 7) && (0 <= (y)) && ((y) <= 7)) 
				returnMoves.add(new int[] {x+1, y});
			
			if ((0 <= (x - 1)) && ((x - 1) <= 7) && (0 <= (y - 1)) && ((y - 1) <= 7)) 
				returnMoves.add(new int[] {x-1, y-1});
			if ((0 <= (x)) && ((x) <= 7) && (0 <= (y - 1)) && ((y - 1) <= 7)) 
				returnMoves.add(new int[] {x, y-1});
			if ((0 <= (x + 1)) && ((x + 1) <= 7) && (0 <= (y - 1)) && ((y - 1) <= 7)) 
				returnMoves.add(new int[] {x+1, y-1});
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
		/*Iterate through entire board state
		check each space if it is the opponent’s color
		if it is opponent’s color, check oppPiece’s validation (call highlight board)
		if oppPiece validator array contains a space in the King’s possible movepool, 
			delete that space from the King’s movepool

		saves places king can’t move to
		afterward, iterate through king’s possible moves and discard any commonalities with the can’t moves.
		*/	
		ArrayList<int []> interferingMoves = new ArrayList<int []>();
		
		if(isWhite)
		{
			//iterates through rows
			for(int i=0; i<8; i++)
			{
				//iterates through columns
				for(int j=0; j < 8; j++)
				{
					//check if is opponent piece
					if(Board.boardState[i][j] != null)
					{
						if(!Board.boardState[i][j].getIsWhite())
						{
							//AVOID INFINITE LOOPS just s'cause
							if(Board.boardState[i][j].getType().toString() == "KING")
							{
								System.out.println("LOL");
							}
							else 
							{
								//save array of opponent possible moves
								ArrayList<int []> oppMoves = new ArrayList<int []>();
								oppMoves = Board.boardState[i][j].getValidator().highlightBoard(i, j, false);
								//run through king array
								for(int k=0; k < tempArr.size(); k++)
								{
									//check for duplicates in opponent moves
									for(int l=0; l < oppMoves.size(); l++)
									{
										//check x coordinates
										if(tempArr.get(k)[0] == oppMoves.get(l)[0])
										{
											//check y coordinates
											if(tempArr.get(k)[1] == oppMoves.get(l)[1])
											{
												System.out.println("\n" + "\n" + "--------------------------" + "\n" + "THIS SHIT IS HERE");
												System.out.println(oppMoves.get(l));
												tempArr.get(k)[0] = xPos;
												tempArr.get(k)[1] = yPos;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(!isWhite)
		{
			//iterates through rows
			for(int i=0; i<8; i++)
			{
				//iterates through columns
				for(int j=0; j < 8; j++)
				{
					//check if is opponent piece
					if(Board.boardState[i][j] != null)
					{
						if(Board.boardState[i][j].getIsWhite() == true)
						{
							//AVOID INFINITE LOOPS just s'cause
							if(Board.boardState[i][j].getType().toString() == "KING")
							{
								System.out.println("LOL");
							}
							else {
								//save array of opponent possible moves
								ArrayList<int []> oppMoves = new ArrayList<int []>();
								oppMoves = Board.boardState[i][j].getValidator().highlightBoard(i, j, true);
								//run through king array
								for(int k=0; k < tempArr.size(); k++)
								{
									//check for duplicates in opponent moves
									for(int l=0; l < oppMoves.size(); l++)
									{
										//check x coordinates
										if(tempArr.get(k)[0] == oppMoves.get(l)[0])
										{
											//check y coordinates
											if(tempArr.get(k)[1] == oppMoves.get(l)[1])
											{
												System.out.println("\n" + "\n" + "--------------------------" + "\n" + "THIS SHIT IS HERE");
												System.out.println(oppMoves.get(l));
												tempArr.get(k)[0] = xPos;
												tempArr.get(k)[1] = yPos;
												//interferingMoves.add(oppMoves.get(l));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		/*ArrayList<int []> temperTempArr = new ArrayList<int []>();
		
		for(int i=0; i<tempArr.size(); i++)
		{
			for(int j=0; j<interferingMoves.size(); j++)
			{
				if((tempArr.get(i)[0] == interferingMoves.get(j)[0]) && (tempArr.get(i)[1] == interferingMoves.get(j)[1]))
				{
					
				}
				else
				{
					temperTempArr.add(tempArr.get(i));
				}
						
			}
		}*/
		
		returnMoves = tempArr;
		
		return returnMoves;
	}
}

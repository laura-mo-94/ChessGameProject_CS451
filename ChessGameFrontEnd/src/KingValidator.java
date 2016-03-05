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
		
		return returnMoves;
	}
}

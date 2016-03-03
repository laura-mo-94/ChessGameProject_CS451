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
			if ((0 <= (xPos - 1)) && ((xPos - 1) <= 7) && (0 <= (yPos + 1)) && ((yPos + 1) <= 7)) 
				returnMoves.add(new int[] {xPos-1, yPos+1});
			if ((0 <= (xPos)) && ((xPos) <= 7) && (0 <= (yPos + 1)) && ((yPos + 1) <= 7)) 
				returnMoves.add(new int[] {xPos, yPos+1});
			if ((0 <= (xPos + 1)) && ((xPos + 1) <= 7) && (0 <= (yPos + 1)) && ((yPos + 1) <= 7)) 
				returnMoves.add(new int[] {xPos+1, yPos+1});
			
			if ((0 <= (xPos - 1)) && ((xPos - 1) <= 7) && (0 <= (yPos)) && ((yPos) <= 7)) 
				returnMoves.add(new int[] {xPos-1, yPos});
			if ((0 <= (xPos + 1)) && ((xPos + 1) <= 7) && (0 <= (yPos)) && ((yPos) <= 7)) 
				returnMoves.add(new int[] {xPos+1, yPos});
			
			if ((0 <= (xPos - 1)) && ((xPos - 1) <= 7) && (0 <= (yPos - 1)) && ((yPos - 1) <= 7)) 
				returnMoves.add(new int[] {xPos-1, yPos-1});
			if ((0 <= (xPos)) && ((xPos) <= 7) && (0 <= (yPos - 1)) && ((yPos - 1) <= 7)) 
				returnMoves.add(new int[] {xPos, yPos-1});
			if ((0 <= (xPos + 1)) && ((xPos + 1) <= 7) && (0 <= (yPos - 1)) && ((yPos - 1) <= 7)) 
				returnMoves.add(new int[] {xPos+1, yPos-1});
		}
		
		return returnMoves;
	}
}

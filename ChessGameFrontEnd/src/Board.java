import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

public class Board {

	private Space spaces[][];
	public Board instance;
	public List<Piece> blackPieces;
	public List<Piece> whitePieces;
	
	public void updateBoard() {
		
	}
	
	public boolean isInCheck(int player) {
		return false;
	}
	 
	//showLegalMoves: Returns a 2D arraylist of Integers each signifying different types of spaces on the board.
	//Current Piece		0(
	//Enemy Piece		1(
	//Player Piece		2(
	//Empty Space		3(
	public ArrayList<ArrayList<Integer>> showLegalMoves(ArrayList<ArrayList<Integer>> spaces) {
		return spaces;		
	}

}

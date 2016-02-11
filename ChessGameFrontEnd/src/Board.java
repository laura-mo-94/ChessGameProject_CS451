import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Object;

public class Board {

	public ArrayList<ArrayList<Piece>> board;
	public Board instance;
	public List<Piece> blackPieces;
	public List<Piece> whitePieces;
	public int turnCounter = 0;
	
	public Board(){
		board = new ArrayList<ArrayList<Piece>>();

		board.add(new ArrayList<Piece>(Arrays.asList((new Rook(0,0,"b")), (new Knight(0,1,"b")), (new Bishop(0,2,"b")), (new Queen(0,3,"b")), (new King(0,4,"b")), (new Bishop(0,5,"b")), (new Knight(0,6,"b")), (new Rook(0,7,"b")))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Pawn(1,0,"b")), (new Pawn(1,1,"b")), (new Pawn(1,2,"b")), (new Pawn(1,3,"b")), (new Pawn(1,4,"b")), (new Pawn(1,5,"b")), (new Pawn(1,6,"b")), (new Pawn(1,7,"b")))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Space(2,0)), (new Space(2,1)), (new Space(2,2)), (new Space(2,3)), (new Space(2,4)), (new Space(2,5)), (new Space(2,6)), (new Space(2,7)))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Space(3,0)), (new Space(3,1)), (new Space(3,2)), (new Space(3,3)), (new Space(3,4)), (new Space(3,5)), (new Space(3,6)), (new Space(3,7)))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Space(4,0)), (new Space(4,1)), (new Space(4,2)), (new Space(4,3)), (new Space(4,4)), (new Space(4,5)), (new Space(4,6)), (new Space(4,7)))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Space(5,0)), (new Space(5,1)), (new Space(5,2)), (new Space(5,3)), (new Space(5,4)), (new Space(5,5)), (new Space(5,6)), (new Space(5,7)))));		
		board.add(new ArrayList<Piece>(Arrays.asList((new Pawn(6,0,"w")), (new Pawn(6,1,"w")), (new Pawn(6,2,"w")), (new Pawn(6,3,"w")), (new Pawn(6,4,"w")), (new Pawn(6,5,"w")), (new Pawn(6,6,"w")), (new Pawn(6,7,"w")))));
		board.add(new ArrayList<Piece>(Arrays.asList((new Rook(7,0,"w")), (new Knight(7,1,"w")), (new Bishop(7,2,"w")), (new Queen(7,3,"w")), (new King(7,4,"w")), (new Bishop(7,5,"w")), (new Knight(7,6,"w")), (new Rook(7,7,"w")))));
	}
	
	public void updateBoard() {
		
	}
	
	public void print(){
		System.out.println("");			
		System.out.println("");
		System.out.println("TURN: " + turnCounter++);
		for(int i = 0; i < board.size(); i++){
			System.out.print(i+1 + " ");
			for(int j = 0; j < board.get(0).size(); j++){
				System.out.print(board.get(i).get(j).getColor() + board.get(i).get(j).getType());
				if (j != board.get(0).size()-1)
					System.out.print(",");
			}
			System.out.println("");
		}
		System.out.print("  " + 'a' + "  " + 'b' + "  " + 'c' + "  " + 'd' + "  " + 'e' + "  " + 'f' + "  " + 'g' + "  " + 'h');
	}
	
	public boolean isInCheck(int player) {
		return false;
	}
	 
	//showLegalMoves: Returns a 2D ArrayList of Integers each signifying different types of spaces on the board.
	//Current Piece		0
	//Enemy Piece		1
	//Player Piece		2
	//Empty Space		3
	public ArrayList<ArrayList<Integer>> showLegalMoves(ArrayList<ArrayList<Integer>> spaces) {
		return spaces;	
	}

	public void move(int c, int g, int k, int l) {
		
		board.get(k).get(l).setX(c);
		board.get(k).get(l).setY(g);
		board.get(c).get(g).setX(k);
		board.get(c).get(g).setX(l);
		
		Piece tempPiece = board.get(k).get(l);
		board.get(k).set(l, board.get(c).get(g));
		board.get(c).set(g, tempPiece);
	}
}

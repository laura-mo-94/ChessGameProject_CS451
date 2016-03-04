import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.Object;

public class Board {

	public static Board instance;

	private static ChessGUI gui;

	private Piece[][] boardState;

	public List<Piece> blackPieces;
	public List<Piece> whitePieces;

	public int turn = 0;
	//public static boolean isHighlighted = false; //Add

	public Board() {
		
	}

	public void updateBoard() {

	}

	public boolean isChecked(int player) {
		return false;
	}

	public boolean isCheckMate(int player) {
		return false;
	}

	public ArrayList<ArrayList<Integer>> getMoves(ArrayList<ArrayList<Integer>> spaces) {
		return spaces;
	}

	public Piece[][] getState() {
		return boardState;
	}

	public void incrementTurn() {
		turn++;
	}

	public void makeMove() {

	}

	/*public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Board board = new Board();
			}
		};
		// Swing GUIs should be created and updated on the EDT
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
<<<<<<< HEAD
	}

//	public static void clearHighlight() {
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				chessBoardSquares[i][j].setBackground(normalColor);
//			}	
//		}
//	}
=======
	}*/
>>>>>>> origin/master
}

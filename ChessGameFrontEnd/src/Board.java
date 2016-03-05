import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.Object;

public class Board {

	public static Board instance;

	private static ChessGUI gui;

	public static Piece[][] boardState = new Piece[8][8];

	public List<Piece> blackPieces;
	public List<Piece> whitePieces;

	public int turn = 0;
	public static boolean isHighlighted = false; //Add

	public Board() {
		gui = new ChessGUI();
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
}

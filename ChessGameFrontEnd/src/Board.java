import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.lang.Object;

public class Board {

	public Board instance;
	
	private ChessGUI gui;
	
	private Piece[][] boardState;
	
	public List<Piece> blackPieces;
	public List<Piece> whitePieces;
	
	public int turn = 0;	
	
	public Board(){
		ChessGUI cg = new ChessGUI();

        JFrame f = new JFrame("Chess Game");
        f.add(cg.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
	}
	
	public void updateBoard() {
		
	}
	
	public boolean isChecked(int player) {
		return false;
	}
	
	public boolean isCheckMate(int player) {
		return false;
	}
	
	public ArrayList<ArrayList<Integer>> showLegalMoves(ArrayList<ArrayList<Integer>> spaces) {
		return spaces;	
	}

	public Piece[][] getState(){
		return boardState;
	}
	
	public void incrementTurn(){
		turn++;
	}
	
	public void makeMove() {
		
	}
	
	 public static void main(String[] args) {
	        Runnable r = new Runnable() {

	            @Override
	            public void run() {
	            	Board board = new Board();
	            }
	        };
	        // Swing GUIs should be created and updated on the EDT
	        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
	        SwingUtilities.invokeLater(r);
	 }
}

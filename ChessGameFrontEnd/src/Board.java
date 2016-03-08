import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.Object;

public class Board {

	public static Board instance;

	public static ChessGUI gui;

	public static Piece[][] boardState = new Piece[8][8];
	
	public static int blackKingX = 4;
	public static int blackKingY = 0;
	public static int whiteKingX = 4;
	public static int whiteKingY = 7;

	public List<Piece> blackPieces;
	public List<Piece> whitePieces;

	public int turn = 0;
	public static boolean isHighlighted = false; //Add
	public static boolean pendingMove = false;
	
	public Board() {
		gui = new ChessGUI();
	}

	public void updateBoard() {

	}

	public static boolean isChecked() {
		if(ChessGUI.updater.getIsWhite())
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
						if(Board.boardState[i][j].getIsWhite() == false)
						{
							ArrayList<int []> oppMoves = new ArrayList<int []>();
							oppMoves = Board.boardState[i][j].getValidator().highlightBoard(i, j, false);
							for(int k=0; k<oppMoves.size(); k++)
							{
								if((oppMoves.get(k)[0] == whiteKingX) && (oppMoves.get(k)[1] == whiteKingY))
									return true;
							}
						}
					}
				}
			}
		}
		
		if(!(ChessGUI.updater.getIsWhite()))
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
							ArrayList<int []> oppMoves = new ArrayList<int []>();
							oppMoves = Board.boardState[i][j].getValidator().highlightBoard(i, j, true);
							for(int k=0; k<oppMoves.size(); k++)
							{
								if((oppMoves.get(k)[0] == blackKingX) && (oppMoves.get(k)[1] == blackKingY))
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean isCheckMate() {
		if(ChessGUI.updater.getIsWhite())
		{
			ArrayList<int []> kingMoves = new ArrayList<int []>();
			kingMoves = Board.boardState[whiteKingX][whiteKingY].getValidator().highlightBoard(whiteKingX, whiteKingY, true);
			if((kingMoves.size() < 1) || ((kingMoves.get(0)[0] == 0) && (kingMoves.get(0)[1] == 0)))
			{
				return true;
			}
		}
		if(!(ChessGUI.updater.getIsWhite()))
		{
			ArrayList<int []> kingMoves = new ArrayList<int []>();
			kingMoves = Board.boardState[blackKingX][blackKingY].getValidator().highlightBoard(blackKingX, blackKingY, false);
			if(kingMoves.size() < 1 || ((kingMoves.get(0)[0] == 0) && (kingMoves.get(0)[1] == 0)))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean kingExists() {
		int numKings = 0;;
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if(boardState[i][j] != null) {
					if(boardState[i][j].getType() == PieceType.KING)
						numKings++;
				}
			}
		}
		if(numKings < 2)
			return false;
		return true;
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
}

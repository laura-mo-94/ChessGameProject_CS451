import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MakeMoveButton extends JButton {

	private static final long serialVersionUID = 1L;
	public static int selectedPieceX = -1;
	public static int selectedPieceY = -1;
	public static int selectedSpaceX = -1;
	public static int selectedSpaceY = -1;
	
	public MakeMoveButton (String string) {

		this.setText("Make Move");
		
        addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                	if(selectedPieceX != -1 && selectedPieceY != -1 && selectedSpaceX != -1 && selectedSpaceY != -1) {
	                	
                		// Console printed statement
	                	String color = "BLACK";
	            		if (Board.boardState[selectedPieceX][selectedPieceY].getIsWhite())
	            			color = "WHITE";
	        			System.out.println("Moving " + color + " " + Board.boardState[selectedPieceX][selectedPieceY].getType().toString() + 
	        					" from (" + selectedPieceX + ", " + selectedPieceY +
	        					") to (" + selectedSpaceX + ", " + selectedSpaceY + ")");
	
	        			// TODO: Remove	
	        			System.out.println("Before Image Switch");
	        			
	        			// Get reference to the Buttons and Pieces
	        			ChessButton fromChessBtn = ChessGUI.chessBoardSquares[selectedPieceX][selectedPieceY];
	        			ChessButton toChessBtn = ChessGUI.chessBoardSquares[selectedSpaceX][selectedSpaceY];
	        			Piece fromPiece = Board.boardState[selectedPieceX][selectedPieceY];
	        			Piece toPiece = Board.boardState[selectedSpaceX][selectedSpaceY];
	        			
	        			// Set the button where the piece will be. (isOccupied, icon)
	        			toChessBtn.isOccupied = true;
	        			toChessBtn.setImage(fromChessBtn.getImage());
	        			
	        			// Set the button where the piece currently is. (isOccupied, icon)
	        			fromChessBtn.isOccupied = false;
	        			fromChessBtn.setImage();
	                    
	        			// TODO: Remove
	        			System.out.println("After Image Switch, Before Piece Switch");
	        		
	        			Board.boardState[selectedSpaceX][selectedSpaceY] = new Piece(fromPiece, selectedSpaceX, selectedSpaceY);
	        			Board.boardState[selectedPieceX][selectedPieceY] = null;
	        			
	        			System.out.println("After Piece Switch");
	        			
	        			selectedPieceX = -1;
	        			selectedPieceY = -1;
	        			selectedSpaceX = -1;
	        			selectedSpaceY = -1;
	            		ChessGUI.clearHighlight();
                	}
        		}
            }
        });
	}
}

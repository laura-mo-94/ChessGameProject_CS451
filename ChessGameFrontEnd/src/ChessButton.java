import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChessButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Color pressedColor = Color.GREEN;
    public Color normalColor;
    private int xPos;
    private int yPos;
    public boolean isOccupied;
    
    public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public ChessButton () {
        setBorderPainted(true);
        setFocusPainted(true);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(normalColor);
        setForeground(Color.WHITE);


        addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                	if (Board.isHighlighted) {
                		//if () {
                		//move piece
                		//else Board.clearHighlight();
                		//}
                	}
                	if(isOccupied){
                		ChessGUI.clearHighlight();
                		setBackground(pressedColor);
                		Piece piece = Board.instance.boardState[xPos][yPos];
                		
                		//Selection Print
                		String color = "BLACK";
                		if (piece.getIsWhite())
                			color = "WHITE";
                		System.out.println(color + " " + piece.getType().toString() + " at (" + piece.getX() + ", " + piece.getY() + ") has been selected.");
                		
                		ArrayList<int[]> returnMoves = piece.v.highlightBoard(piece.getX(), piece.getY(), piece.getIsWhite());
                		
                		System.out.println("\t" + color + " " + piece.getType().toString() + " can move to:");
                		for (int i = 0; i < returnMoves.size(); i++) {
                			ChessButton tempButton = ChessGUI.chessBoardSquares[returnMoves.get(i)[0]][returnMoves.get(i)[1]];
                			tempButton.setBackground(tempButton.pressedColor);
                			System.out.println("\t\t" + "(" + returnMoves.get(i)[0] + ", " + returnMoves.get(i)[1] + ")");
                		}
                		Board.isHighlighted = true;
                	}
                }
            }
        }

        );
    }
    
    public void resetSpaceColor() {
    	setBackground(normalColor);
    }
    
    public void setIsOccupied(boolean bool) {
    	isOccupied = bool;
    }
    
    public boolean getIsOccupied() {
    	return isOccupied;
    }
}
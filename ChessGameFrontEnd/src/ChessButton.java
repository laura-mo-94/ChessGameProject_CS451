import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChessButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private Color pressedColor = Color.GREEN;
	private Color moveColor = Color.BLUE;
    public Color normalColor;
    private int xPos;
    private int yPos;
    public boolean isOccupied;
    private ImageIcon image;
    
    public ImageIcon getImage() {
		return image;
	}

    public void setImage(ImageIcon image) {
		this.image = image;
		this.setIcon(image);
	}
    
    public void setImage() {
    	ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		this.image = icon;
		this.setIcon(icon);
	}
    
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
                		if(getBackground() == pressedColor && Board.pendingMove == false) {
                			setBackground(moveColor);
                			MakeMoveButton.selectedSpaceX = xPos;
                			MakeMoveButton.selectedSpaceY = yPos;
                			System.out.println("Selected Space (" + xPos + ", " + yPos + ")");
                			Board.pendingMove = true;
                		} else {
                			ChessGUI.clearHighlight();
                			Board.pendingMove = false;
                		}
                	}
                	if(isOccupied){
                		ChessGUI.clearHighlight();
                		setBackground(pressedColor);
                		Piece piece = Board.instance.boardState[xPos][yPos];

                		//Set Selected Piece
                		MakeMoveButton.selectedPieceX = piece.getX();
                		MakeMoveButton.selectedPieceY = piece.getY();
            			System.out.println("Selected Piece (" + piece.getX() + ", " + piece.getY() + ")");

                		
            			// Console printed statement
                		String color = "BLACK";
                		if (piece.getIsWhite())
                			color = "WHITE";
                		System.out.println(color + " " + piece.getType().toString() + " at (" + piece.getX() + ", " + piece.getY() + ") has been selected.");
                		
                		// Movement Validation
                		ArrayList<int[]> returnMoves = piece.validator.highlightBoard(piece.getX(), piece.getY(), piece.getIsWhite());
                		System.out.println("\t" + color + " " + piece.getType().toString() + " can move to:");
                		for (int i = 0; i < returnMoves.size(); i++) {
                			ChessGUI.chessBoardSquares[returnMoves.get(i)[0]][returnMoves.get(i)[1]].setBackground(pressedColor);
                			System.out.println("\t\t" + "(" + returnMoves.get(i)[0] + ", " + returnMoves.get(i)[1] + ")");
                		}
                		Board.isHighlighted = true;
                	}
                }
            }
        });
    }
    
    public ChessButton(int selectedSpaceX, int selectedSpaceY, boolean b) {
    	xPos = selectedSpaceX;
    	yPos = selectedSpaceY;
    	isOccupied = b;
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
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChessButton extends JButton {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pressedColor = Color.GREEN;
    public Color normalColor;
    private boolean isOccupied;
    
    public ChessButton () {
        setBorderPainted(false);
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(normalColor);
        setForeground(Color.WHITE);


        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                	if(isOccupied){
                		setBackground(pressedColor);
                		showPossibleMoves();
                	}
                } else {
                    setBackground(normalColor);
                    resetBoardColors();
                }
            }

            // 0 empty
            // 1 friendly
            // 2 enemy
			private void showPossibleMoves() {
				//int[][] = getValidationOutput;
				for (int i = 0; i < ChessGUI.chessBoardSquares.length; i++) {
		            for (int j = 0; j < ChessGUI.chessBoardSquares[i].length; j++) {
		            	//if(int[i][j] == 0 || int[i][j] == 2){
		            	if(i == 3 && j == 3){
		            		ChessGUI.chessBoardSquares[i][j].setBackground(pressedColor);
		            	}
		            }
				}
			}
			
			private void resetBoardColors() {
				for (int i = 0; i < ChessGUI.chessBoardSquares.length; i++) {
		            for (int j = 0; j < ChessGUI.chessBoardSquares[i].length; j++) {
		            	if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
		            		ChessGUI.chessBoardSquares[i][j].setBackground(Color.WHITE);
		            		ChessGUI.chessBoardSquares[i][j].normalColor = Color.WHITE;
		                } else {
		                	ChessGUI.chessBoardSquares[i][j].setBackground(Color.BLACK);
		                	ChessGUI.chessBoardSquares[i][j].normalColor = Color.BLACK;
		                }
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
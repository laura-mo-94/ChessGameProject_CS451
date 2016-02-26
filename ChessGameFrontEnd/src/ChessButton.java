import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
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
        setBorderPainted(true);
        setFocusPainted(true);

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
                	} else {
                		setBackground(normalColor);
                	}
                }
            }
            void mouseExited(MouseEvent e) {
        		setBackground(normalColor);

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
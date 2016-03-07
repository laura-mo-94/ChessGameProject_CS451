import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MakeMoveButton extends JButton {

	private static final long serialVersionUID = 1L;
	ChessGUI gui = Board.gui;
	public static int selectedPieceX = -1;
	public static int selectedPieceY = -1;
	public static int selectedSpaceX = -1;
	public static int selectedSpaceY = -1;
	
	public MakeMoveButton (String string) {

		this.setText("Make Move");
		
        addChangeListener(new ChangeListener() {
            
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                	if (gui.updater.getIsActive()) {
                		gui.makeMove(selectedPieceX, selectedPieceY, selectedSpaceX, selectedSpaceY);
                	}
        		}
            }
        });
	}
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    public static ChessButton[][] chessBoardSquares = new ChessButton[8][8];
    private static Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private ImageIcon queen; 
    
  
    private static final String COLS = "ABCDEFGH";
    
    public static GameUpdater updater;
    private JLabel message;
    private JLabel state;
    
    public static final int QUEEN = 0, 
    						KING = 1,
    						ROOK = 2, 
    						KNIGHT = 3, 
    						BISHOP = 4, 
    						PAWN = 5;
    public static final int[] STARTING_ROW = {
        ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    
    public static final int BLACK = 0, 
    						WHITE = 1;

    ChessGUI() {
        initializeGui();
    }

    public final void initializeGui() {
        // create the images for the chess pieces

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
//        Action newGameAction = new AbstractAction("New") {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setupNewGame();
//            }
//        };
//      tools.add(newGameAction);
        tools.addSeparator();
        tools.add(new MakeMoveButton("Make Move")); // TODO - add functionality!
        tools.addSeparator();
        
        JButton forfeit = new JButton("Forfeit");
		forfeit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					updater.forfeit();
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
        
		tools.add(forfeit); // TODO - add functionality!
		tools.addSeparator();
        
		JButton draw = new JButton("Draw");
		
        draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					updater.draw();
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		
        tools.add(draw); // TODO - add functionality!
        
        tools.addSeparator();
        // Look into switching top message.
       
        message = new JLabel("Welcome");
        tools.add(message);

        tools.addSeparator();
        state = new JLabel("not your turn");
        tools.add(state);
        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null && c.getWidth()>d.getWidth() && c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        
        // Set the BG to be ochre
        Color ochre = new Color(200,120,40);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
            	ChessButton b = new ChessButton();
            	b.setxPos(j);
            	b.setyPos(i);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setImage(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                    b.normalColor = Color.WHITE;
                } else {
                    b.setBackground(Color.BLACK);
                    b.normalColor = Color.BLACK;
                }
                chessBoardSquares[j][i] = b;
            }
        }

        /*
         * fill the chess board
         */
        createImages();

        //Blank Space
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int i = 0; i < 8; i++) {
            chessBoard.add(
                    new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9-(i + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }
    }

    public final JPanel getGui() {
        return gui;
    }

    // Initialize all pieces into an array from an image file found online.
    private final void createImages() {
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = bi.getSubimage(j * 64, i * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    
        // Initializes the icons of the initial chess board piece places 
        // Initializing the boardState 2Darray
        // set up the black pieces
    	Board.boardState[0][0] = new Piece(PieceType.ROOK,0,0,false);
    	Board.boardState[1][0] = new Piece(PieceType.KNIGHT,1,0,false);
    	Board.boardState[2][0] = new Piece(PieceType.BISHOP,2,0,false);
    	Board.boardState[3][0] = new Piece(PieceType.QUEEN,3,0,false);
    	Board.boardState[4][0] = new Piece(PieceType.KING,4,0,false);
    	Board.boardState[5][0] = new Piece(PieceType.BISHOP,5,0,false);
    	Board.boardState[6][0] = new Piece(PieceType.KNIGHT,6,0,false);
    	Board.boardState[7][0] = new Piece(PieceType.ROOK,7,0,false);
        for (int i = 0; i < STARTING_ROW.length; i++) {
        	chessBoardSquares[i][0].setIsOccupied(true);
            chessBoardSquares[i][0].setImage(new ImageIcon(
                    chessPieceImages[BLACK][STARTING_ROW[i]]));
        }
        Board.boardState[0][1] = new Piece(PieceType.PAWN,0,1,false);
    	Board.boardState[1][1] = new Piece(PieceType.PAWN,1,1,false);
    	Board.boardState[2][1] = new Piece(PieceType.PAWN,2,1,false);
    	Board.boardState[3][1] = new Piece(PieceType.PAWN,3,1,false);
    	Board.boardState[4][1] = new Piece(PieceType.PAWN,4,1,false);
    	Board.boardState[5][1] = new Piece(PieceType.PAWN,5,1,false);
    	Board.boardState[6][1] = new Piece(PieceType.PAWN,6,1,false);
    	Board.boardState[7][1] = new Piece(PieceType.PAWN,7,1,false);
        for (int i = 0; i < STARTING_ROW.length; i++) {
        	chessBoardSquares[i][1].setIsOccupied(true);
            chessBoardSquares[i][1].setImage(new ImageIcon(
                    chessPieceImages[BLACK][PAWN]));
        }
        
        // set up the white pieces
    	Board.boardState[0][6] = new Piece(PieceType.PAWN,0,6,true);
    	Board.boardState[1][6] = new Piece(PieceType.PAWN,1,6,true);
    	Board.boardState[2][6] = new Piece(PieceType.PAWN,2,6,true);
    	Board.boardState[3][6] = new Piece(PieceType.PAWN,3,6,true);
    	Board.boardState[4][6] = new Piece(PieceType.PAWN,4,6,true);
    	Board.boardState[5][6] = new Piece(PieceType.PAWN,5,6,true);
    	Board.boardState[6][6] = new Piece(PieceType.PAWN,6,6,true);
    	Board.boardState[7][6] = new Piece(PieceType.PAWN,7,6,true);
        for (int i = 0; i < STARTING_ROW.length; i++) {
        	chessBoardSquares[i][6].setIsOccupied(true);
            chessBoardSquares[i][6].setImage(new ImageIcon(
                    chessPieceImages[WHITE][PAWN]));
        }
    	Board.boardState[0][7] = new Piece(PieceType.ROOK,0,7,true);
    	Board.boardState[1][7] = new Piece(PieceType.KNIGHT,1,7,true);
    	Board.boardState[2][7] = new Piece(PieceType.BISHOP,2,7,true);
    	Board.boardState[3][7] = new Piece(PieceType.QUEEN,3,7,true);
    	Board.boardState[4][7] = new Piece(PieceType.KING,4,7,true);
    	Board.boardState[5][7] = new Piece(PieceType.BISHOP,5,7,true);
    	Board.boardState[6][7] = new Piece(PieceType.KNIGHT,6,7,true);
    	Board.boardState[7][7] = new Piece(PieceType.ROOK,7,7,true);
        for (int i = 0; i < STARTING_ROW.length; i++) {
        	chessBoardSquares[i][7].setIsOccupied(true); 
            chessBoardSquares[i][7].setImage(new ImageIcon(
                    chessPieceImages[WHITE][STARTING_ROW[i]]));
        }
        
        for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Board.boardState[i][j] != null) {
					Piece tempPiece = Board.boardState[i][j];
					if (tempPiece.getType() == PieceType.PAWN)
						tempPiece.validator = new PawnValidator();
					if (tempPiece.getType() == PieceType.ROOK)
						tempPiece.validator = new RookValidator();
					if (tempPiece.getType() == PieceType.KNIGHT)
						tempPiece.validator = new KnightValidator();
					if (tempPiece.getType() == PieceType.BISHOP)
						tempPiece.validator = new BishopValidator();
					if (tempPiece.getType() == PieceType.QUEEN)
						tempPiece.validator = new QueenValidator();
					if (tempPiece.getType() == PieceType.KING)
						tempPiece.validator = new KingValidator();
				}
			}
        }
    }
    
    public static void makeMove(int x1, int y1, int x2, int y2) {

		// Console printed statement
    	String color = "BLACK";
    	if (Board.boardState[x1][y1] != null && !Board.isChecked()) {
			if (Board.boardState[x1][y1].getIsWhite())
				color = "WHITE";
			System.out.println("Moving " + color + " " + Board.boardState[x1][y1].getType().toString() + 
					" from (" + x1 + ", " + y1 +
					") to (" + x2 + ", " + y2 + ")");
			
			// Get reference to the Buttons and Pieces
			ChessButton fromChessBtn = chessBoardSquares[x1][y1];
			ChessButton toChessBtn = chessBoardSquares[x2][y2];
			Piece fromPiece = Board.boardState[x1][y1];
			Piece toPiece = Board.boardState[x2][y2];
			
			// Set the button where the piece will be. (isOccupied, icon)
			toChessBtn.isOccupied = true;
			toChessBtn.setImage(fromChessBtn.getImage());
			
			// Set the button where the piece currently is. (isOccupied, icon)
			fromChessBtn.isOccupied = false;
			fromChessBtn.setImage();
	
			Board.boardState[x2][y2] = new Piece(fromPiece, x2, y2);
			
			// Convert to string
			String x1c = Integer.toString(x1);
			String y1c = Integer.toString(y1);
			String x2c = Integer.toString(x2);
			String y2c = Integer.toString(y2);
					
			String toMove = (x1c + y1c + x2c + y2c);
			
			try {
				updater.sendAction(toMove);
			} catch (Exception e) {
				System.out.println("I TRIED TO SEND MOVE");	
				e.printStackTrace();
			}

			if (fromPiece.getType() == PieceType.PAWN) {
				if (fromPiece.getIsWhite()) {
					if (y2 == 0) {
						Board.boardState[x2][y2] = null;
						Board.boardState[x2][y2] = new Piece(PieceType.QUEEN, x2, y2, true);
						Board.boardState[x2][y2].validator = new QueenValidator();
						chessBoardSquares[x2][y2].setImage(new ImageIcon(
			                    chessPieceImages[WHITE][STARTING_ROW[3]]));
					}
					if (y1 == y2) {
						Board.boardState[x2][y2] = null;
						chessBoardSquares[x2][y2].isOccupied = false;
						chessBoardSquares[x2][y2].setImage();
						chessBoardSquares[x2][y2-1].isOccupied = true;
						Board.boardState[x2][y2-1] = new Piece(fromPiece, x2, y2-1);
						chessBoardSquares[x2][y2-1].setImage(new ImageIcon(
			                    chessPieceImages[WHITE][PAWN]));
					}
				}
				if (!fromPiece.getIsWhite()) {
					if (y2 == 7) {
						Board.boardState[x2][y2] = null;
						Board.boardState[x2][y2] = new Piece(PieceType.QUEEN, x2, y2, false);
						Board.boardState[x2][y2].validator = new QueenValidator();
						chessBoardSquares[x2][y2].setImage(new ImageIcon(
			                    chessPieceImages[BLACK][STARTING_ROW[3]]));
					}
					if (y1 == y2) {
						Board.boardState[x2][y2] = null;
						chessBoardSquares[x2][y2].isOccupied = false;
						chessBoardSquares[x2][y2].setImage();
						chessBoardSquares[x2][y2-1].isOccupied = true;
						Board.boardState[x2][y2+1] = new Piece(fromPiece, x2, y2-1);
						chessBoardSquares[x2][y2+1].setImage(new ImageIcon(
			                    chessPieceImages[BLACK][PAWN]));
					}
				}
			}
			
			Board.boardState[x1][y1] = null;

			if (fromPiece.getType().toString() == "KING") {
				if (fromPiece.getIsWhite()) {
					Board.whiteKingX = x2;
					Board.whiteKingY = y2;
				} else {
					Board.blackKingX = x2;
					Board.blackKingY = y2;
				}
			}
    	}
    	MakeMoveButton.resetSelection();
		ChessGUI.clearHighlight();
	}
	
	// Get move from other player via string.
	public static void makeMove(String move) {
		
		// Parse from string		
		int x1 = Integer.parseInt(move.substring(0,1));
		int y1 = Integer.parseInt(move.substring(1,2));
		int x2 = Integer.parseInt(move.substring(2,3));
		int y2 = Integer.parseInt(move.substring(3,4));
				       
		System.out.println(x1 + ", " + y1 + " to " + x2 + ", " + y2);
		if (Board.boardState[x1][y1] != null && !Board.isChecked()) {
		
			// Console printed statement
	    	String color = "BLACK";
			if (Board.boardState[x1][y1].getIsWhite())
				color = "WHITE";
			System.out.println("Moving " + color + " " + Board.boardState[x1][y1].getType().toString() + 
					" from (" + x1 + ", " + y1 +
					") to (" + x2 + ", " + y2 + ")");
			
			// Get reference to the Buttons and Pieces
			ChessButton fromChessBtn = ChessGUI.chessBoardSquares[x1][y1];
			ChessButton toChessBtn = ChessGUI.chessBoardSquares[x2][y2];
			Piece fromPiece = Board.boardState[x1][y1];
			Piece toPiece = Board.boardState[x2][y2];
			
			// Set the button where the piece will be. (isOccupied, icon)
			toChessBtn.isOccupied = true;
			toChessBtn.setImage(fromChessBtn.getImage());
			
			// Set the button where the piece currently is. (isOccupied, icon)
			fromChessBtn.isOccupied = false;
			fromChessBtn.setImage();
	
			Board.boardState[x2][y2] = new Piece(fromPiece, x2, y2);
			Board.boardState[x1][y1] = null;
			
			if (fromPiece.getType() == PieceType.PAWN) {
				if (fromPiece.getIsWhite()) {
					if (y2 == 0) {
						Board.boardState[x2][y2] = null;
						Board.boardState[x2][y2] = new Piece(PieceType.QUEEN, x2, y2, true);
						Board.boardState[x2][y2].validator = new QueenValidator();
						chessBoardSquares[x2][y2].setImage(new ImageIcon(
			                    chessPieceImages[WHITE][STARTING_ROW[3]]));
					}
					if (y1 == y2) {
						Board.boardState[x2][y1] = null;
						chessBoardSquares[x2][y2].isOccupied = false;
						chessBoardSquares[x2][y2].setImage();
						Board.boardState[x2][y1-1] = new Piece(fromPiece, x2, y2-1);
						chessBoardSquares[x2][y2-1].setImage(new ImageIcon(
			                    chessPieceImages[WHITE][PAWN]));
					}
				}
				if (!fromPiece.getIsWhite()) {
					if (y2 == 7) {
						Board.boardState[x2][y2] = null;
						Board.boardState[x2][y2] = new Piece(PieceType.QUEEN, x2, y2, false);
						Board.boardState[x2][y2].validator = new QueenValidator();
						chessBoardSquares[x2][y2].setImage(new ImageIcon(
			                    chessPieceImages[BLACK][STARTING_ROW[3]]));
					}
					if (y1 == y2) {
						Board.boardState[x2][y1] = null;
						chessBoardSquares[x2][y2].isOccupied = false;
						chessBoardSquares[x2][y2].setImage();
						Board.boardState[x2][y1+1] = new Piece(fromPiece, x2, y2-1);
						chessBoardSquares[x2][y2+1].setImage(new ImageIcon(
			                    chessPieceImages[BLACK][PAWN]));
					}
				}
			}
			if (fromPiece.getType().toString() == "KING") {
				if (fromPiece.getIsWhite()) {
					Board.whiteKingX = x2;
					Board.whiteKingY = y2;
				} else {
					Board.blackKingX = x2;
					Board.blackKingY = y2;
				}
			}
    	}
    	MakeMoveButton.resetSelection();
		ChessGUI.clearHighlight();
	}
    
	public static void clearHighlight() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chessBoardSquares[i][j].setBackground(chessBoardSquares[i][j].normalColor);
			}	
		}
	}
	
	public void buildFrame(JFrame frame, String userName, String gameName)
	{
		frame.dispose();
		
		JFrame f = new JFrame("Chess game");
		
		 f.getContentPane().add(getGui());
		  
		 updater = new GameUpdater(state, message, userName, gameName);
		
		 updater.setActiveFrame(f);
			
			try{
				Timer timer = new Timer(1000, updater);
				timer.start();
				
				updater.attachTimer(timer);
				
			}catch(Exception exp)
			{
				System.out.println("Error! Failed to send button action. " + exp);
				exp.printStackTrace();
			}
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationByPlatform(true);
		f.pack();
		f.setMinimumSize(f.getSize());
		f.setVisible(true);
	}
}
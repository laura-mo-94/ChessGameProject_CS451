package src;

public abstract class Piece {

	private int xpos = 0;
	private int ypos = 0;
	private boolean isWhite = true;
	//private PieceType type = PAWN;
	//private String identifier = "";  IS THIS NEEDED?
	//private Validator validator = new Validator();
	private boolean isActive = true;
	
	public Piece(int x, int y, boolean color)
	{
		xpos = x;
		ypos = y;
		isWhite = color;
	}

	public int getX() {
		return xpos;
	}

	public void setX(int x) {
		this.xpos = x;
	}

	public int getY() {
		return ypos;
	}

	public void setY(int y) {
		this.ypos = y;
	}

	public boolean getisWhite() {
		return isWhite;
	}

	/*public void getIdentifier(String color) {
		return color;
	}

	public PieceType getType() {
		return type;
	}*/

	public boolean getisActive() {
		return isActive;
	}

	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}
	

}

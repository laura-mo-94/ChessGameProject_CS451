public class Piece {

	private int xPos = 0;
	private int yPos = 0;
	private boolean isWhite = true;
	private PieceType type;
	private Validator v;
	
	//private boolean isActive = true; IS THIS NEEDED?
	
	public Piece(PieceType type, int x, int y, boolean color, Validator v)
	{
		xPos = x;
		yPos = y;
		isWhite = color;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int x) {
		this.xPos = x;
	}

	public int getY() {
		return yPos;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public boolean getIsWhite() {
		return isWhite;
	}

	public PieceType getType() {
		return type;
	}
	
	public void setType(PieceType t)
	{
		this.type = t;
	}
	

}

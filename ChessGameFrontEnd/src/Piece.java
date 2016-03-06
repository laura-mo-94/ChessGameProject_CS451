public class Piece {

	private int xPos;
	private int yPos;
	private boolean isWhite = true;
	private PieceType type;
	public Validator validator;
	//private boolean isActive = true; IS THIS NEEDED?
	
	public Piece(PieceType type, int x, int y, boolean color)
	{
		this.type = type;
		this.xPos = x;
		this.yPos = y;
		this.isWhite = color;
	}
	
	public Piece(Piece piece)
	{
		this.type = piece.getType();
		this.xPos = piece.getX();
		this.yPos = piece.getY();
		this.isWhite = piece.getIsWhite();
		this.validator = piece.getValidator();
	}
	
	public Piece(Piece piece, int x, int y)
	{
		this.type = piece.getType();
		this.xPos = x;
		this.yPos = y;
		this.isWhite = piece.getIsWhite();
		this.validator = piece.getValidator();
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator v) {
		this.validator = v;
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

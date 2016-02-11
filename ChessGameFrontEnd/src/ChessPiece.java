import java.util.ArrayList;

public interface ChessPiece {

	public int x = 0;
	public int y = 0;
	public String color = "White";
	public String type = "King";
	public boolean isActive = true;
	
	public ArrayList<ArrayList<Integer>> getMoves();
}

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BoardTest {
	@Test
	public void testAction() {
		Board board = new Board();
		ArrayList<ArrayList<Integer>> spaces = new ArrayList<ArrayList<Integer>>();
		assertEquals(board.isInCheck(0), false);
		assertEquals(board.showLegalMoves(spaces), spaces);
	}
}
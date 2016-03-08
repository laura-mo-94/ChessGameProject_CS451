import org.junit.Test;

public class BoardTest {
	@Test
	public void DriverTest() {
		Driver d = new Driver();
		Driver.main(new String[] {"arg1", "arg2", "arg3"});
	}
	@Test
	public void ValidatorTest() {
		Board b = new Board();
		Piece wPawn = b.instance.boardState[0][6];
		Piece wRook = b.instance.boardState[0][7];
		Piece wKnight = b.instance.boardState[1][7];
		Piece wBishop = b.instance.boardState[2][7];
		Piece wQueen = b.instance.boardState[3][7];
		Piece wKing = b.instance.boardState[4][7];
		wPawn.validator.highlightBoard(0, 6, true);
		wRook.validator.highlightBoard(0, 7, true);
		wKnight.validator.highlightBoard(1, 7, true);
		wBishop.validator.highlightBoard(2, 7, true);
		wQueen.validator.highlightBoard(3, 7, true);
		wKing.validator.highlightBoard(4, 7, true);
		wRook.validator.highlightBoard(3, 4, true);
		wBishop.validator.highlightBoard(3, 4, true);
		wQueen.validator.highlightBoard(3, 4, true);
		wQueen.validator.highlightBoard(1, 5, true);
		wQueen.validator.highlightBoard(6, 5, true);
		wKing.validator.highlightBoard(4, 1, true);
		wPawn.validator.highlightBoard(1, 2, true);
		wPawn.validator.highlightBoard(0, 3, true);
		wPawn.validator.highlightBoard(7, 3, true);
		wPawn.validator.highlightBoard(1, 3, true);
		Piece bPawn = b.instance.boardState[0][1];
		Piece bRook = b.instance.boardState[0][0];
		Piece bKnight = b.instance.boardState[1][0];
		Piece bBishop = b.instance.boardState[2][0];
		Piece bQueen = b.instance.boardState[3][0];
		Piece bKing = b.instance.boardState[4][0];
		bPawn.validator.highlightBoard(0, 1, false);
		bRook.validator.highlightBoard(0, 0, false);
		bKnight.validator.highlightBoard(1, 0, false);
		bBishop.validator.highlightBoard(2, 0, false);
		bQueen.validator.highlightBoard(3, 0, false);
		bKing.validator.highlightBoard(4, 0, false);
		bRook.validator.highlightBoard(3, 4, false);
		bBishop.validator.highlightBoard(3, 4, false);
		bQueen.validator.highlightBoard(3, 4, false);
		bQueen.validator.highlightBoard(1, 5, false);
		bQueen.validator.highlightBoard(6, 5, false);
		bKing.validator.highlightBoard(4, 6, false);
		bPawn.validator.highlightBoard(1, 5, false);
		bPawn.validator.highlightBoard(0, 4, false);
		bPawn.validator.highlightBoard(7, 4, false);
		bPawn.validator.highlightBoard(1, 4, false);
		bPawn.validator.setEnPassant(true);
		wPawn.validator.setEnPassant(true);
		wKing.validator.canCastle();
		wPawn.validator.canCastle();
		wPawn.validator.inCheck();
		wPawn.validator.checkMate();
		wPawn.validator.setHasMoved(false);
		wRook.validator.setEnPassant(false);
		wRook.validator.getEnPassant();
		b.gui.makeMove(0, 6, 0, 4);
	}
}

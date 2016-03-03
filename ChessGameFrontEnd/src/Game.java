
public class Game {

	int activePlayer;
	Board board;
	String playerName;
	String opponentName;
	GameUpdater updater;
	
	public Game(int activePlayer, Board board, String playerName, String opponentName, GameUpdater updater) {
		super();
		this.activePlayer = activePlayer;
		this.board = board;
		this.playerName = playerName;
		this.opponentName = opponentName;
		this.updater = updater;
	}
	
	public void startGame() {
		
	}
	
	public void forfeit() {
		
	}
	
	public void draw() {
		
	}
}

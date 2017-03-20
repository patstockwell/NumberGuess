package numberGuess;

public enum Arena {	
	OFDOOM;
	
	private Game currentGame = new Game();
	private int count = 13;

	public Game createNewGame() {
		currentGame = new Game();
		count++;
		return currentGame;
	}
	
	public Game getGame() {
		return currentGame;
	}
	
	public int getGameCount() {
		return count;
	}
}

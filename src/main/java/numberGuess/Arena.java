package numberGuess;

public enum Arena {	
	OFDOOM;
	
	private Game currentGame;
	private int count = 0;

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

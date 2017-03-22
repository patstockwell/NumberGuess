package numberGuess;

public class Guess {
	
	private final long guessID;
	private final long userID;
    private final String content;
    private int currentPoints;
    private int changeInPoints;
    private int gameCount;

    public Guess(long guessID, String content, int currentPoints, int changeInPoints, long userID) {
        this.guessID = guessID;
        this.content = content;
        this.currentPoints = currentPoints;
        this.changeInPoints = changeInPoints;
        this.userID = userID;
        this.gameCount = Arena.OFDOOM.getGameCount();
    }

    public long getguessID() {
        return guessID;
    }

    public String getContent() {
        return content;
    }

	public int getCurrentPoints() {
		return currentPoints;
	}

	public int getChangeInPoints() {
		return changeInPoints;
	}

	public long getUserID() {
		return userID;
	}
	
	public long getGameCount() {
		return gameCount;
	}

}
/*
 * Inputs for API
 * - number (guess)
 * - gameID
 * - userID
 * 
 * Outputs for API
 * - Message string
 * - Current Points
 * - Amount of points won or lost in that guess
 * - userID
 * - game count
 * 
 * */

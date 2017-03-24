package numberGuess;

public class GuessResponse {
	
	private final long guessID;
    private final String content;
    private int currentPoints;
    private int changeInPoints;
    private final long userID;
    private int gameCount;

    public GuessResponse(long guessID, String content, int currentPoints, int changeInPoints, long userID) {
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

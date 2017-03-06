package numberGuess;

public class Guess {
	
	private final long guessID;
	private final long userID;
    private final String content;
    private int currentPoints;
    private int changeInPoints;

    public Guess(long guessID, String content, int currentPoints, int changeInPoints, long userID) {
        this.guessID = guessID;
        this.userID = userID;
        this.content = content;
        this.currentPoints = currentPoints;
        this.changeInPoints = changeInPoints;
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

}
/*
 * Inputs for API
 * - number (guess)
 * - user guessID/token
 * 
 * Outputs for API
 * - Message string
 * - Current Points
 * - Amount of points won or lost in that guess
 * - user guessID/token
 * - guess guessID
 * 
 * */

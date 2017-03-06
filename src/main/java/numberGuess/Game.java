package numberGuess;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
	
	private int min = 1;
	private int max = 100;
	private int secretNum; 
	
	public Game() {
		setSecretNum();
	}

	public int getSecretNum() {
		return secretNum;
	}

	public void setSecretNum() {
		//nextInt is normally exclusive of the top value,
		//so add 1 to make it inclusive
		this.secretNum = ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public int guessNumber(int guess) {
		int guessedCorrectly;
		// 0 == correct guess
		//-1 == guess too low
		// 1 == guess too high
		if (guess == this.secretNum) {
			guessedCorrectly = 0;
		}
		else if (guess < this.secretNum) {
			guessedCorrectly = -1;
		}
		else
			guessedCorrectly = 1;
		return guessedCorrectly;
	}

}

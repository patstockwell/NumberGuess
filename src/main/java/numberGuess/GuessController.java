package numberGuess;

import java.util.concurrent.atomic.AtomicLong;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessController {

	private final AtomicLong counter = new AtomicLong();
	
	@CrossOrigin
	@RequestMapping("/guess")
	public Guess makeGuess(
			@RequestParam(value="guessedNum", defaultValue="99") String guessedNum,
			@RequestParam(value="gameCount", defaultValue="-1") String gameCount,
			@RequestParam(value="userID", defaultValue="1211") String userID
			) {
		int x = Arena.OFDOOM.getGame().getSecretNum();
		System.out.println(x);
		
		String message;
		int currentPoints = 100; //currentPoints should initialise from the database.
		int changeInPoints;
		
		//check guess matches the right game
		if (isNotCurrentGame(Integer.parseInt(gameCount))) {
			message = "Someone guessed the number before you. A new game has started, try again!";
			changeInPoints = 0;
		}

		//guess too low
		else if(Integer.parseInt(guessedNum) < Arena.OFDOOM.getGame().getSecretNum()) {
			message = "Higher than " + guessedNum + ", try again";
			changeInPoints = -1;
		}
		//guess too high
		else if(Integer.parseInt(guessedNum) > Arena.OFDOOM.getGame().getSecretNum()) {
			message = "Lower than " + guessedNum + ", try again";
			changeInPoints = -1;
		}
		//guess correct
		else {
			Arena.OFDOOM.createNewGame();
			message = "Winner! " + guessedNum + " is the secret Number";
			changeInPoints = Integer.parseInt(guessedNum);
			SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
			api.call(new SlackMessage("User:" + userID + "\n " + message + "\nChange in points: " + changeInPoints + "\nCurrent Points: " + currentPoints));
		}
		
		currentPoints = currentPoints + changeInPoints;
		//write info to the database
		
		
	
		return new Guess(counter.incrementAndGet(), message, currentPoints, changeInPoints, Integer.parseInt(userID));
	}
	
	public boolean isNotCurrentGame(int gameCount) {
		boolean isNotCurrentGame = true;
		if(gameCount == Arena.OFDOOM.getGameCount()) {
			isNotCurrentGame = false;
		}
		return isNotCurrentGame;
	}
}

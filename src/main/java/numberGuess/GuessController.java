package numberGuess;

import java.util.concurrent.atomic.AtomicLong;

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
			@RequestParam(value="number", defaultValue="99") String guessedNum,
			@RequestParam(value="game", defaultValue="32") String gameCount,
			@RequestParam(value="user", defaultValue="1211") String userID
			) {
		String message;
		int currentPoints = 100; //currentPoints should initialise from the database.
		int changeInPoints;
		
		//check guess matches the right game
		if (Integer.parseInt(gameCount) != Arena.OFDOOM.getGameCount()) {
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
			message = "Winner! " + guessedNum + "is the secret Number";
			changeInPoints = Integer.parseInt(guessedNum);
		}
		
		currentPoints = currentPoints + changeInPoints;
		//write info to the database
	
		return new Guess(counter.incrementAndGet(), message, currentPoints, changeInPoints, Integer.parseInt(userID));
	}
}

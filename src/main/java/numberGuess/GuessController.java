package numberGuess;

import java.util.concurrent.atomic.AtomicLong;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessController {
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	private PlayerRepository playerRepository;
	@CrossOrigin
	@RequestMapping("/guess")
	public GuessResponse makeGuess(@RequestParam(value="guessedNum", defaultValue="101") String guessedNum, @RequestParam(value="gameCount", defaultValue="-1") String gameCount, @RequestParam(value="userID", defaultValue="1211") long userID) {
		System.out.println(Arena.OFDOOM.getGame().getSecretNum()); //print number to terminal for debugging
		Player currentPlayer; //are they logged in?
		try {
			System.out.println("Looking for user id: " + userID);
			currentPlayer = playerRepository.findById(userID);
			System.out.println("Found user id: " + userID);
			if (currentPlayer == null) {
				System.out.println("Current user null");
				return new GuessResponse(0, "Invalid guess, user not logged in.", 0, 0, 0);
			}
		}
		catch (Exception e) {
			return new GuessResponse(0, "Invalid guess, user not logged in.", 0, 0, 0);
		}
		//set up some variables for the response
		int guess = Integer.parseInt(guessedNum);
		int secretNum = Arena.OFDOOM.getGame().getSecretNum();
		String message;
		int changeInPoints = -1;
		int currentPoints = currentPlayer.getPoints();
		//check guess matches the right game
		if (isNotCurrentGame(Integer.parseInt(gameCount)))
			message = "Someone guessed the number before you. A new game has started, try again!";
		//guess too low
		else if(guess < secretNum)
			message = "Higher than " + guessedNum + ", try again";
		//guess too high
		else if(guess > secretNum) 
			message = "Lower than " + guessedNum + ", try again";
		//guess correct
		else {
			Arena.OFDOOM.createNewGame();
			message = "Winner! " + guessedNum + " is the secret Number";
			changeInPoints = Integer.parseInt(guessedNum);
			try {
			SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
			api.call(new SlackMessage("\nUser: " + currentPlayer.getName() + "\n " + message + "\nChange in points: " + changeInPoints + "\nCurrent Points: " + currentPoints + "\n"));
			} 
			catch (Exception e){
				System.out.println("No Slack connection available.");
			}
		}
		currentPoints = currentPoints + changeInPoints;
		//write info to the database
		currentPlayer.setPoints(currentPoints);
		playerRepository.save(currentPlayer);
		
		
	
		return new GuessResponse(counter.incrementAndGet(), message, currentPoints, changeInPoints, userID);
	}
	
	public boolean isNotCurrentGame(int gameCount) {
		boolean isNotCurrentGame = true;
		if(gameCount == Arena.OFDOOM.getGameCount()) {
			isNotCurrentGame = false;
		}
		return isNotCurrentGame;
	}
	
	@CrossOrigin
	@GetMapping(path="/game/count")
	public @ResponseBody int getGameCount() {
		return Arena.OFDOOM.getGameCount();
	}
}

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
	public Guess makeGuess(@RequestParam(value="number", defaultValue="99") String number) {
		//put here all the logic to decide how 
		//the guess affects the game and what kind of response to send
		return new Guess(counter.incrementAndGet(), "the number guessed was:" + number, 32, -1, 78932);
	}
}

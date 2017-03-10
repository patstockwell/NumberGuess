package numberGuess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GameTest {
	
	Game currentGame; 
	
	@Before
	public void setUpGame() {
		currentGame = new Game();
	}
	
	@Test
	public void hasSecretNumberTest() {
		assertTrue(currentGame.getSecretNum() != -99);
	}

	@Test
	public void secretNumberInRangeTest() {
		assertTrue(currentGame.getSecretNum() < 101 && currentGame.getSecretNum() > 0);
	}
	
	@Test
	public void guessNumberTest() {
		assertEquals(currentGame.guessNumber(-100), -1);
		assertEquals(currentGame.guessNumber(1000), 1);
		assertEquals(currentGame.guessNumber(currentGame.getSecretNum()), 0);
		
	}

}

package numberGuess;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GuessControllerTest {
	
	GuessController controllerHere ;
	
	@Before
	public void setup() {
		controllerHere = new GuessController();
	}

	@Test
	public void isNotCurrentGameTest() {
		assertFalse(controllerHere.isNotCurrentGame(13));
	}
	
	@Test
	public void getGameCountTest() {
		JsonMessage json = controllerHere.getGameCount();
		assertTrue(Integer.parseInt(json.getMessage()) == Arena.OFDOOM.getGameCount());
	}
	
	@Test
	public void getByIdTest() {
		assertTrue(controllerHere.getById((long)43) == null);
	}

}

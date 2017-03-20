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

}

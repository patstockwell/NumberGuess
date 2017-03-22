package numberGuess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import numberGuess.Player;
import numberGuess.PlayerRepository;

@Controller
@RequestMapping(path="/player")
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping(path="/register")
	public @ResponseBody String registerPlayer (@RequestParam String name, @RequestParam String password) {
		
		Player n = new Player();
		n.setName(name);
		n.setPassword(password);
		if (playerRepository.exists(name)) {
			return "Username already taken, try another";
		}
		else
		playerRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

}

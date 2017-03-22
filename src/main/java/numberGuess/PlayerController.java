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
		if (playerRepository.exists(name)) {
			return "Username already taken, try another";
		}
		else {
			Player n = new Player();
			n.setName(name);
			n.setPassword(password);
			playerRepository.save(n);
			return "Saved";
		}
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}
	
	@GetMapping(path="/login")
	public @ResponseBody Player login(@RequestParam String name, @RequestParam String password) {
		try {
			Player player = playerRepository.findOne(name);
			if (player.getPassword().equals(password)) {
				return player;
			}
			else {
				System.out.println("Current password is: " + password + " \nPlayer password is: " + player.getPassword());
				Player wrongPassword = new Player();
				wrongPassword.setId(-1); //return -1 to show wrong password entered
				return wrongPassword;
			}
		}
		catch (Exception e) {
			Player noUsername = new Player();
			noUsername.setId(0); //return 0 to show no user-name found
			return noUsername;
		}
	}

}

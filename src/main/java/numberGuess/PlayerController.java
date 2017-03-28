package numberGuess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin
	@GetMapping(path="/register")
	public @ResponseBody JsonMessage registerPlayer (@RequestParam String name, @RequestParam String password) {
		if (playerRepository.exists(name)) {
			return new JsonMessage("Username already taken, try another");
		}
		else {
			Player n = new Player();
			n.setName(name);
			n.setPassword(password);
			playerRepository.save(n);
			return new JsonMessage("Saved");
		}
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}
	@CrossOrigin
	@GetMapping(path="/login")
	public @ResponseBody Player login(@RequestParam String name, @RequestParam String password) {
		try {
			Player player = playerRepository.findOne(name);
			if (player.getPassword().equals(password)) {
				//create new id token
				long id = System.currentTimeMillis();
				player.setId(id);
				playerRepository.save(player);
				return player;
			}
			else {
				System.out.println("Attempted password is: " + password + " \nPlayer password is: " + player.getPassword());
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

	@CrossOrigin
	@GetMapping(path="/findById")
	public @ResponseBody Player getById(@RequestParam long id) {
		return playerRepository.findById(id);
	}
	
	@CrossOrigin
	@GetMapping(path="/findByName")
	public @ResponseBody Player getByName(@RequestParam String name) {
		return playerRepository.findByName(name);
	}
	
	@CrossOrigin
	@GetMapping(path="/removeByName")
	public @ResponseBody String removeByName(@RequestParam String name) {
		Player player = playerRepository.findByName(name);
		playerRepository.delete(player);
		return player.getName() + " removed.";
	}

}

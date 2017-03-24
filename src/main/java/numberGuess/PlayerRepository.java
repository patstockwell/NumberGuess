package numberGuess;

import org.springframework.data.repository.CrudRepository;
import numberGuess.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {
	
	public Player findByName(String name);
	
	public Player findById(long id);

}

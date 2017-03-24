package numberGuess;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guess {
	@Id
	private Integer id;
    private String name;
    private int gameNum;
    private int guess;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGameNum() {
		return gameNum;
	}

	public void setGameNum(int gameNum) {
		this.gameNum = gameNum;
	}

	public int getGuess() {
		return guess;
	}

	public void setGuess(int guess) {
		this.guess = guess;
	}

}

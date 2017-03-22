package numberGuess;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guess {
	@Id
	private Integer id;

    private String name;

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

}

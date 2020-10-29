package creator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "solutionsDefinitions")
public class GameCoreData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number;				// The number of the square in squares variable
	private String direction;		// The direction of the word
	private int solutionLength;		// The length of the word written in squares
	private String definition;		// The explanation of the solutionLength field
	
	public String getSolution() {
		char[] chars = new char[solutionLength];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = direction.equals(Workbench.getLang().getHorizontal())
					? Workbench.getSquares()[number + i].getLetter()
					: Workbench.getSquares()[number + Workbench.getActualPreference().getWidth() * i].getLetter();
		}
		return String.valueOf(chars);
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getSolutionLength() {
		return solutionLength;
	}
	public void setSolutionLength(int solution) {
		this.solutionLength = solution;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}


	@Override
	public String toString() {
		return "GameCoreData [id=" + id + ", number=" + number + ", direction=" + direction + ", solutionLength="
				+ solutionLength + ", definition=" + definition + "]";
	}
	
}

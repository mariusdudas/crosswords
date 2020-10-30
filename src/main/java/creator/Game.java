package creator;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity(name = "games")
@Access(AccessType.FIELD)
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameId;

	private String name;
	private String theme;
	private String size;
	private String description;

	// We also save the image for the background of the game
	private Preferences preference;
	private File image;
	private Rating rating;
	private boolean complete;

	// Not showing these
	private char[] letters;
	private Timestamp accessTime;
	private String solutionsDefinitionsJson;
	@Transient
//	@Convert(converter = ListConverter.class)
	private List<GameCoreData> solutionsDefinitions = new ArrayList<GameCoreData>();
	
	public void serializeSolutionsDefinitions() throws JsonProcessingException {
	    this.solutionsDefinitionsJson = new ObjectMapper().writeValueAsString(solutionsDefinitions);
	}
	
	public void deserializeSolutionsDefinitions() throws JsonProcessingException {
		this.solutionsDefinitions = new ObjectMapper().readValue(solutionsDefinitionsJson, List.class);
	}

	public Long getId() {
		return gameId;
	}

	public Preferences getPreference() {
		return preference;
	}
	
	public void setPreferenceName(String name) {
		this.preference.setName(name);
	}

	public Rating getRating() {
		return rating;
	}

	public List<GameCoreData> getSolutionsDefinitions() {
		return solutionsDefinitions;
	}

	public String getSolution(int index) {
		char[] chars = new char[((GameCoreData) solutionsDefinitions.toArray()[index]).getSolutionLength()];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = ((GameCoreData)solutionsDefinitions.toArray()[index]).getDirection().equals(Workbench.getLang().getHorizontal())
					? Workbench.getSquares()[((GameCoreData)solutionsDefinitions.toArray()[i]).getNumber() + i].getLetter()
					: Workbench.getSquares()[((GameCoreData)solutionsDefinitions.toArray()[i]).getNumber() + preference.getWidth() * i]
							.getLetter();
		}
		return String.valueOf(chars);
	}

	public void initGame(int length) {
		letters = new char[length];
		preference = Workbench.getActualPreference();
		size = preference.getWidth() + "X" + preference.getHeight();
	}

	public void setLetter(int index, char letter) {
		letters[index] = letter;
	}

	public char getLetter(int index) {
		return letters[index];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public List<GameCoreData> getGameCoreData() {
		return solutionsDefinitions;
	}

	public char[] getCharValues() {
		return letters;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSize() {
		return size;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isComplete() {
		return complete;
	}
	
	public char[] getLetters() {
		return letters;
	}
	
	public String getSolutionsDefinitionsJSON() {
		return solutionsDefinitionsJson;
	}

	public Timestamp getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}
	
//	public JsonString getJSONsolutionsDefinitions() {
//		return JSONsolutionsDefinitions;
//	}
//
//	public void setJSONsolutionsDefinitions(JsonString jSONsolutionsDefinitions) {
//		JSONsolutionsDefinitions = jSONsolutionsDefinitions;
//	}
}

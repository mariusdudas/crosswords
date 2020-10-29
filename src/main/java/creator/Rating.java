package creator;

import javax.persistence.Entity;

public enum Rating {
	ONE (1), TWO (2), THREE (3), FOUR (4), FIVE (5);
	
	private int level;
	
	private Rating(int level) {
		this.level = level;
	}
	
	public int getRating() {
		return this.level;
	}
	
	public void setRating(int level) {
		this.level = level;
	}
}

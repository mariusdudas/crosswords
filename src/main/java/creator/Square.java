package creator;

public class Square {

	private int x, y;
	private boolean selected = false, blanc = false;
	private char letter = 0;

	public Square(int x, int y, boolean selected, char letter) {
		super();
		this.x = x;
		this.y = y;
		this.selected = selected;
		this.letter = letter;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean isBlanc() {
		return blanc;
	}

	public void setBlanc(boolean blanc) {
		this.blanc = blanc;
		if (blanc) {
			letter = ' ';
			selected = false;
		}
		else
			letter = 0;
	}

	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + ", selected=" + selected + ", blank=" + blanc + ", letter=" + letter
				+ "]";
	}
	
}

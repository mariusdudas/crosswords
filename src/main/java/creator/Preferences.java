package creator;

import java.awt.Color;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Entity;

public class Preferences implements Externalizable{
	private String name;
	private int width = 1, height = 1;
	private Color bckSquareColor = Color.white, outSquareColor = Color.black, 
			selColor = Color.magenta, textSquareColor = Color.black, 
			blancSquareColor = Color.black, 
			drawAreaColor = new Color(0, 204, 255, 255),
			numberingColor = Color.black;
	public Preferences() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getBckSquareColor() {
		return bckSquareColor;
	}
	public void setBckSquareColor(Color bckSquareColor) {
		this.bckSquareColor = bckSquareColor;
	}
	public Color getOutSquareColor() {
		return outSquareColor;
	}
	public void setOutSquareColor(Color outSquareColor) {
		this.outSquareColor = outSquareColor;
	}
	public Color getSelColor() {
		return selColor;
	}
	public void setSelColor(Color selColor) {
		this.selColor = selColor;
	}
	public Color getTextSquareColor() {
		return textSquareColor;
	}
	public void setTextSquareColor(Color textSquareColor) {
		this.textSquareColor = textSquareColor;
	}
	public Color getNumberingColor() {
		return numberingColor;
	}

	public void setNumberingColor(Color numberingColor) {
		this.numberingColor = numberingColor;
	}

	public Color getBlancSquareColor() {
		return blancSquareColor;
	}
	public void setBlancSquareColor(Color blancSquareColor) {
		this.blancSquareColor = blancSquareColor;
	}

	
	public Color getDrawAreaColor() {
		return drawAreaColor;
	}

	public void setDrawAreaColor(Color drawAreaColor) {
		this.drawAreaColor = drawAreaColor;
	}

	@Override
	public void readExternal(ObjectInput preference) throws IOException, ClassNotFoundException {
		// Loads preferences from file
		name = preference.readUTF();
		width = preference.readInt();
		height = preference.readInt();
		bckSquareColor = (Color)preference.readObject();
		outSquareColor = (Color)preference.readObject();
		selColor = (Color)preference.readObject();
		textSquareColor = (Color)preference.readObject();
		numberingColor = (Color)preference.readObject();
		blancSquareColor = (Color)preference.readObject();
		drawAreaColor = (Color)preference.readObject();
	}
	
	@Override
	public void writeExternal(ObjectOutput preference) throws IOException {
		// Saves preferences to file
		preference.writeUTF(name);
		preference.writeInt(width);
		preference.writeInt(height);
		preference.writeObject(bckSquareColor);
		preference.writeObject(outSquareColor);
		preference.writeObject(selColor);
		preference.writeObject(textSquareColor);
		preference.writeObject(numberingColor);
		preference.writeObject(blancSquareColor);
		preference.writeObject(drawAreaColor);
	}

	@Override
	public String toString() {
		return "Preferences [name=" + name + ", width=" + width + ", height=" + height + ", bckSquareColor="
				+ bckSquareColor + ", outSquareColor=" + outSquareColor + ", selColor=" + selColor
				+ ", textSquareColor=" + textSquareColor + ", blancSquareColor=" + blancSquareColor + ", drawAreaColor="
				+ drawAreaColor + ", numberingColor=" + numberingColor + "]";
	}

}

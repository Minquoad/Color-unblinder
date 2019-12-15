package model;

public class LabeledColor {

	private String name = null;
	private int red = 0;
	private int green = 0;
	private int blue = 0;

	public LabeledColor(String name, int red, int green, int blue) {
		this.name = name;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int toInteger() {
		return (red << 16) + (green << 8) + blue;
	}

	public String getName() {
		return name;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
}

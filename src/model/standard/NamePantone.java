package model.standard;

import java.util.Locale;

import model.LabeledColor;
import model.LabeledColorInventory;

public class NamePantone extends Standard {

	public NamePantone(Locale locale) {
		super(locale);
	}

	@Override
	public String getId() {
		return "namedPantone";
	}

	@Override
	public String getName() {
		return "Pantone (names)";
	}

	@Override
	protected void fillInventory(LabeledColorInventory inventory) {

		inventory.add(new LabeledColor("Yellow C", 254, 221, 0));
		inventory.add(new LabeledColor("Yellow 012 C", 255, 215, 0));
		inventory.add(new LabeledColor("Orange 021 C", 254, 80, 0));
		inventory.add(new LabeledColor("Warm Red C", 249, 66, 58));
		inventory.add(new LabeledColor("Red 032 C", 239, 51, 64));
		inventory.add(new LabeledColor("Rubine Red C", 206, 0, 88));
		inventory.add(new LabeledColor("Rhodamine Red C", 225, 0, 152));
		inventory.add(new LabeledColor("Purple C", 187, 41, 187));
		inventory.add(new LabeledColor("Violet C", 68, 0, 153));
		inventory.add(new LabeledColor("Blue 072 C", 16, 6, 159));
		inventory.add(new LabeledColor("Reflex Blue C", 0, 20, 137));
		inventory.add(new LabeledColor("Process Blue C", 0, 133, 202));
		inventory.add(new LabeledColor("Green C", 0, 171, 132));
		inventory.add(new LabeledColor("Black C", 45, 41, 38));
		inventory.add(new LabeledColor("Yellow 0131 C", 242, 240, 161));
		inventory.add(new LabeledColor("Red 0331 C", 252, 174, 187));
		inventory.add(new LabeledColor("Magenta 0521 C", 241, 178, 220));
		inventory.add(new LabeledColor("Violet 0631 C", 191, 155, 222));
		inventory.add(new LabeledColor("Blue 0821 C", 116, 209, 234));
		inventory.add(new LabeledColor("Green 0921 C", 157, 231, 215));
		inventory.add(new LabeledColor("Black 0961 C", 158, 151, 142));
		inventory.add(new LabeledColor("Medium Yellow C", 255, 217, 0));
		inventory.add(new LabeledColor("Bright Orange C", 255, 94, 0));
		inventory.add(new LabeledColor("Bright Red C", 249, 56, 34));
		inventory.add(new LabeledColor("Strong Red C", 206, 0, 86));
		inventory.add(new LabeledColor("Pink C", 214, 37, 152));
		inventory.add(new LabeledColor("Medium Purple C", 78, 0, 142));
		inventory.add(new LabeledColor("Dark Blue C", 0, 35, 156));
		inventory.add(new LabeledColor("Medium Blue C", 0, 132, 202));
		inventory.add(new LabeledColor("Bright Green C", 0, 176, 139));
		inventory.add(new LabeledColor("Neutral Black C", 34, 34, 35));
		inventory.add(new LabeledColor("Warm Gray 1 C", 215, 210, 203));
		inventory.add(new LabeledColor("Warm Gray 2 C", 203, 196, 188));
		inventory.add(new LabeledColor("Warm Gray 3 C", 191, 184, 175));
		inventory.add(new LabeledColor("Warm Gray 4 C", 182, 173, 165));
		inventory.add(new LabeledColor("Warm Gray 5 C", 172, 163, 154));
		inventory.add(new LabeledColor("Warm Gray 6 C", 165, 156, 148));
		inventory.add(new LabeledColor("Warm Gray 7 C", 150, 140, 131));
		inventory.add(new LabeledColor("Warm Gray 8 C", 140, 130, 121));
		inventory.add(new LabeledColor("Warm Gray 9 C", 131, 120, 111));
		inventory.add(new LabeledColor("Warm Gray 10 C", 121, 110, 101));
		inventory.add(new LabeledColor("Warm Gray 11 C", 110, 98, 89));
		inventory.add(new LabeledColor("Cool Gray 1 C", 217, 217, 214));
		inventory.add(new LabeledColor("Cool Gray 2 C", 208, 208, 206));
		inventory.add(new LabeledColor("Cool Gray 3 C", 200, 201, 199));
		inventory.add(new LabeledColor("Cool Gray 4 C", 187, 188, 188));
		inventory.add(new LabeledColor("Cool Gray 5 C", 177, 179, 179));
		inventory.add(new LabeledColor("Cool Gray 6 C", 167, 168, 170));
		inventory.add(new LabeledColor("Cool Gray 7 C", 151, 153, 155));
		inventory.add(new LabeledColor("Cool Gray 8 C", 136, 139, 141));
		inventory.add(new LabeledColor("Cool Gray 9 C", 117, 120, 123));
		inventory.add(new LabeledColor("Cool Gray 10 C", 99, 102, 106));
		inventory.add(new LabeledColor("Cool Gray 11 C", 83, 86, 90));
		inventory.add(new LabeledColor("Black 2 C", 51, 47, 33));
		inventory.add(new LabeledColor("Black 3 C", 33, 39, 33));
		inventory.add(new LabeledColor("Black 4 C", 49, 38, 29));
		inventory.add(new LabeledColor("Black 5 C", 62, 43, 46));
		inventory.add(new LabeledColor("Black 6 C", 16, 24, 32));
		inventory.add(new LabeledColor("Black 7 C", 61, 57, 53));

	}

}

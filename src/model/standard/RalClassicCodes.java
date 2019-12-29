package model.standard;

import java.util.Locale;

import model.LabeledColor;
import model.LabeledColorInventory;

public class RalClassicCodes extends RalClassic {

	public RalClassicCodes(Locale locale) {
		super(locale);
	}

	@Override
	public String getId() {
		return "ralClassicCodes";
	}

	@Override
	public String getName() {
		return "RAL Classic (Codes)";
	}

	@Override
	public void addInInventory(
			LabeledColorInventory inventory,
			String code,
			int red, int green, int blue,
			String germanName, String englishName, String frenchName, String spanishName, String italianName, String nederlandsName) {

		inventory.add(new LabeledColor(code, red, green, blue));
	}

}

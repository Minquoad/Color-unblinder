package model.standard;

import java.util.Locale;

import model.LabeledColor;
import model.LabeledColorInventory;

public class RalClassicNames extends RalClassic {

	public RalClassicNames(Locale locale) {
		super(locale);
	}

	@Override
	public String getId() {
		return "ralClassicNames";
	}

	@Override
	public String getName() {
		return "RAL Classic (Names)";
	}

	@Override
	public void addInInventory(
			LabeledColorInventory inventory,
			String code,
			int red, int green, int blue,
			String germanName, String englishName, String frenchName, String spanishName, String italianName, String nederlandsName) {

		String name = null;

		if (getLocale().equals(new Locale("nl")))
			name = nederlandsName;
		else if (getLocale().equals(Locale.ITALIAN))
			name = italianName;
		else if (getLocale().equals(new Locale("es")))
			name = spanishName;
		else if (getLocale().equals(Locale.FRENCH))
			name = frenchName;
		else if (getLocale().equals(Locale.GERMAN))
			name = germanName;
		else if (getLocale().equals(Locale.ENGLISH))
			name = englishName;

		if (name != null) {
			inventory.add(new LabeledColor(name, red, green, blue));
		}

	}

}

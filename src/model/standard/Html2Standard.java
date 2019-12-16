package model.standard;

import java.util.Locale;

import model.LabeledColor;
import model.LabeledColorInventory;

public class Html2Standard extends Standard {

	public Html2Standard(Locale locale) {
		super(locale);
	}

	@Override
	public String getId() {
		return "html2";
	}

	@Override
	public String getName() {
		return "HTML2";
	}

	@Override
	public void fillInventory(LabeledColorInventory inventory) {

		inventory.add(new LabeledColor("grey", 127, 127, 127));

	}

}

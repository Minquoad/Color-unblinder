package model.standard;

import java.util.Locale;

import model.LabeledColorInventory;

public abstract class Standard {

	private Locale locale;
	private LabeledColorInventory inventory;

	public Standard(Locale locale) {
		this.locale = locale;
		inventory = null;
	}

	protected abstract void fillInventory(LabeledColorInventory inventory);

	public abstract String getId();

	public abstract String getName();

	public LabeledColorInventory getInventory() {
		if (inventory == null) {
			inventory = new LabeledColorInventory();
			this.fillInventory(inventory);
		}
		return inventory;
	}

	public Locale getLocale() {
		return locale;
	}

	public static Standard[] getAll(Locale locale) {
		Standard[] standards = {
				new HtmlStandard(locale)
		};

		return standards;
	}

	@Override
	public boolean equals(Object object) {
		if (super.equals(object)) {
			return true;
		}
		if (object instanceof Standard) {
			Standard standard = (Standard) object;
			return standard.getId().equals(this.getId()) && standard.getLocale().equals(this.getLocale());
		}
		return false;
	}
	
}

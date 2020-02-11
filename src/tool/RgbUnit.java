package tool;

public enum RgbUnit {

	PERCENT(i -> {
		String str = Integer.toString((int) Math.round(100f * ((float) i) / 255f));
		while (str.length() < 3)
			str = " " + str;
		return str + "%";
	}),
	BITS(i -> {
		String str = Integer.toString(i);
		while (str.length() < 4)
			str = " " + str;
		return str;
	}),
	HEXADECIMAL(i -> {
		String str = Integer.toHexString(i).toUpperCase();
		while (str.length() < 2)
			str = "0" + str;
		return str;
	});

	private Formater formater;

	private RgbUnit(Formater formater) {
		this.formater = formater;
	}

	public interface Formater {
		public String format(int i);
	}

	public String format(int i) {
		return formater.format(i);
	}
}

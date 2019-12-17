package tool;

public enum Unit {

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
	});

	private Formater formater;

	private Unit(Formater formater) {
		this.formater = formater;
	}

	public interface Formater {
		public String format(int i);
	}

	public String format(int i) {
		return formater.format(i);
	}
}

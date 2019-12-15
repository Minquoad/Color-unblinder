package tool;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationTool {

	public static String getText(String key, String bundleBaseName, Locale locale, Object... args) {
		return MessageFormat.format(ResourceBundle.getBundle(bundleBaseName, locale).getString(key), args);
	}

	public static Locale[] getSupportedLocales() {
		Locale[] locales = {
				Locale.ENGLISH,
				Locale.FRENCH
		};
		return locales;
	}

}

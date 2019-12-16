package tool;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationTool {

	public static String getText(String key, String bundleBaseName, Object... args) {
		return MessageFormat.format(ResourceBundle.getBundle(bundleBaseName, Configuration.getLocale()).getString(key), args);
	}

	public static Locale[] getSupportedLocales() {
		Locale[] locales = {
				Locale.ENGLISH,
				Locale.FRENCH
		};
		return locales;
	}

}

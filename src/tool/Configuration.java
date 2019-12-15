package tool;

import java.io.File;
import java.util.Locale;

import javax.swing.filechooser.FileSystemView;

import model.standard.HtmlStandard;
import model.standard.Standard;

public class Configuration {

	private static final String DEFAULT_DIRECTORY_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().toString();
	
	public static final File preferencesFile = new File(DEFAULT_DIRECTORY_PATH + File.pathSeparator
			+ "Minquoad products" + File.pathSeparator + "Color-unblinder" + File.pathSeparator + "preferences.json");

	public static final String VERSION = "0.1.0";

	private Locale locale;
	private Standard standard;

	public Configuration() {
		locale = Locale.ENGLISH;
		standard = new HtmlStandard(locale);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;

		for (Standard standard : Standard.getAll(this.getLocale()))
			if (standard.getId().equals(this.standard.getId()))
				this.standard = standard;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

}

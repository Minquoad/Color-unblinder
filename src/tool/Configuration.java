package tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.swing.filechooser.FileSystemView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.standard.HtmlStandard;
import model.standard.Standard;

public abstract class Configuration {

	public static final String PREFERENCES_FILE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().toString()
			+ "/Color-unblinder"
			+ "/preferences.json";

	public static final String VERSION = "1.0.0";

	private static Locale locale;
	private static Standard standard;

	static {

		File file = new File(PREFERENCES_FILE_PATH);
		if (!file.exists()) {
			locale = Locale.ENGLISH;
			standard = new HtmlStandard(locale);

		} else {

			InputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				byte[] buffer = new byte[10_000];
				int redBytesSize;
				while ((redBytesSize = bis.read(buffer)) > 0) {
					bos.write(buffer, 0, redBytesSize);
				}

				JsonNode rootNode = new ObjectMapper().readTree(new String(bos.toByteArray()));

				String language = rootNode.findValue("language").asText();
				String standardId = rootNode.findValue("standardId").asText();

				locale = new Locale(language);

				for (Standard standard : Standard.getAll(locale))
					if (standard.getId().equals(standardId))
						Configuration.standard = standard;

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bis.close();
				} catch (Exception e) {
				}
			}

		}

	}

	public static Locale getLocale() {
		return locale;
	}

	public static void setLocale(Locale locale) {
		Configuration.locale = locale;

		for (Standard standard : Standard.getAll(locale))
			if (standard.getId().equals(Configuration.standard.getId()))
				Configuration.standard = standard;
	}

	public static Standard getStandard() {
		return standard;
	}

	public static void setStandard(Standard standard) {
		Configuration.standard = standard;
	}

	public static void save() {
		OutputStream bos = null;
		try {
			ObjectNode messageJsonObject = JsonNodeFactory.instance.objectNode();
			messageJsonObject.put("version", VERSION);
			messageJsonObject.put("language", locale.getLanguage());
			messageJsonObject.put("standardId", standard.getId());

			File file = new File(PREFERENCES_FILE_PATH);

			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(messageJsonObject.toString().getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (Exception e) {
			}
		}
	}

}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private ConfigReader() {
		throw new IllegalStateException("ConfigReader class");
	}

	private static Properties getProperties() {

		File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String getProperty(String key) {
		Properties properties = getProperties();
		return properties.getProperty(key);
	}
}

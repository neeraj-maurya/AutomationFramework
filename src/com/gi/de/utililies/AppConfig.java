package com.gi.de.utililies;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AppConfig {

	/**
	 * @author neerajm
	 * This code is used to read the property file
	 * @return property file object
	 */
	public static Properties propertyReader() {
		FileReader reader;
		Properties prop = new Properties();
		try {
			reader = new FileReader("application.properties");
			prop.load(reader);
		} catch (IOException e) {
			Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE,
					"The properties file could not be loaded", e);
		}
		return prop;
	}
}
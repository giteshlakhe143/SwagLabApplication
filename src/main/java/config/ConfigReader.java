package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	//Load config property file
	static {
	try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"/src/main/resources/config/config.properties");
		 prop = new Properties();
		 prop.load(fis);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	public static String getProperties(String key) {
		return prop.getProperty(key);
	}

}


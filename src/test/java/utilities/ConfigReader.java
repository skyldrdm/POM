package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	
	static {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		return value;
	}

}

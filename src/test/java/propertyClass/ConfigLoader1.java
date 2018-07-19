package propertyClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader1 {

	// String url = ConfigLoader.getMyValue("a key in here") ;
	// driver.get(url) ;
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

	public static String getMyValue(String key) {

		// System.out.println( prop.getProperty("fairfax") );
		String value = prop.getProperty(key);

		return value;

	}

}
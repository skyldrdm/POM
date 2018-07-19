package propertyClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	//String url = ConfigLoader.getPropertyValue("a key in here");
	//driver.get()

	public static void main(String[] args) {

		Properties prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("dosya.properties");
			prop.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("<"+ prop.getProperty("Osman")+ ">");
	}
}

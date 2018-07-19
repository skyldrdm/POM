package propertyClass;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

public class PropertyClass {

	public static void main(String[] args) {
	

		Properties prop = new Properties();
		prop.setProperty("Tyson", "20282");
		prop.setProperty("Fairfax", "22030");
		prop.setProperty("Centreville", "20120");

		prop.setProperty("Fairfax", "22030");
		prop.setProperty("Centreville", "20120");

		System.out.println(prop);
		System.out.println(prop.getProperty("Tyson"));

		Set<Entry<Object, Object>> entries = prop.entrySet();
		for (Entry<Object, Object> each : entries) {

			System.out.println(each.getValue());
			System.out.println(each.getKey());
		}

	}
}
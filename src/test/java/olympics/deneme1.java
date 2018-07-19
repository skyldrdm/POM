package olympics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class deneme1 {
	WebDriver driver;

	@Test
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
	}

	public void testJapan() {
		// ===========Giving the location of Japan======
		List<WebElement> countries = driver.findElements(
				By.xpath("//table[@class = 'wikitable sortable" + " plainrowheaders jquery-tablesorter']//tbody//th"));
		int row = 0;
		for (int i = 0; i < countries.size(); i++) {
			if (countries.get(i).getText().contains("Japan")) {
				row = i + 1;
			}
		}
		System.out.println("=====Japan Location====");
		System.out.println("row= " + row);
		List<WebElement> columns = driver.findElements(
				By.xpath("//table[@class = 'wikitable sortable" + " plainrowheaders jquery-tablesorter']//thead//th"));

		int column = 0;
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).getText().contains("NOC")) {
				column = i + 1;
			}
		}

		System.out.println("column= " + column);
		System.out.println("========================\n");
	}
	
	
}
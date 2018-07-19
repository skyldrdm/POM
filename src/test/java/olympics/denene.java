package olympics;

import java.util.Map.Entry;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class denene {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void testCase1() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

		// STEP 1-2
		// collect all first column cells
		List<WebElement> rankList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[1]"));
		// remove the last cell
		rankList.remove(rankList.size() - 1);
		// confirm that rankList is sorted
		Assert.assertTrue(isSorted(rankList));

		// STEP 3-4
		// click on NOC link
		driver.findElement(By.xpath("//caption[.='2016 Summer Olympics medal table']//..//th[.='NOC']")).click();

		// collect all country names
		List<WebElement> countryList = driver
				.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/th/a"));
		// confirm if countryList is sorted
		Assert.assertTrue(isSorted(countryList));

		// STEP 5
		// collect all first column cells
		rankList = driver.findElements(By.xpath("//caption[.='2016 Summer Olympics medal table']//../tbody/tr/td[1]"));
		// confirm that rankList is not ordered any more
		Assert.assertFalse(isSorted(rankList));
	}

	// Method to check if given list sorted
	public boolean isSorted(List<WebElement> lst) {

		List<Integer> lstInt = new ArrayList<>();
		List<String> lstStr = new ArrayList<>();

		// Check if content numeric such as Integer
		if (!lst.isEmpty() && Character.isDigit(lst.get(0).getText().charAt(0))) {
			for (WebElement each : lst) {
				lstInt.add(Integer.valueOf(each.getText()));
			}
			List<Integer> lstIntCopy = new ArrayList<>(lstInt);
			Collections.sort(lstInt);
			return lstInt.equals(lstIntCopy);
		} else { // content is not numeric, then check sorting by alphabetic order
			for (WebElement each : lst) {
				lstStr.add(each.getText());
			}
			List<String> lstStrCopy = new ArrayList<>(lstStr);
			Collections.sort(lstStr);
			return lstStr.equals(lstStrCopy);
		}
	}
}
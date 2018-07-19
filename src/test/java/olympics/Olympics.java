package olympics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Olympics {

	WebDriver driver;

	String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.manage().window().fullscreen();
	}

	@Test

	public void sort() throws InterruptedException {
		driver.get(url);

		List<WebElement> rankList = new ArrayList<>();
		rankList = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		rankList.remove(rankList.size() - 1);
		List<Integer> rankListText = new ArrayList<>();
		for (WebElement rank : rankList) {

			rankListText.add(Integer.parseInt(rank.getText()));
		}
		System.out.print(rankListText);

		SortedSet<Integer> expectedList = new TreeSet<>(rankListText);

		Assert.assertEquals(rankListText, expectedList);

		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[2]"))
				.click();

		Thread.sleep(1000);

		List<WebElement> countryList = new ArrayList<>();

		countryList = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));

		System.out.println(countryList);

		List<String> countryListText = new ArrayList<>();

		for (WebElement rank : countryList) {

			countryListText.add(rank.getText());
		}
		System.out.print(countryListText);

		SortedSet<String> expectedCountryList = new TreeSet<>(countryListText);

		Assert.assertEquals(countryListText, expectedCountryList);

		Assert.assertFalse((rankListText.equals(expectedList)));

	}

	public void sortInteger2(String xpath) {

		List<WebElement> rankList = new ArrayList<>();

		rankList = driver.findElements(

				By.xpath(xpath));

		rankList.remove(rankList.size() - 1);
		List<Integer> rankListText = new ArrayList<>();
		for (WebElement rank : rankList) {

			rankListText.add(Integer.parseInt(rank.getText()));
		}
		System.out.print(rankListText);

		SortedSet<Integer> expectedList = new TreeSet<>(rankListText);

		Assert.assertEquals(rankListText, expectedList);

	}

	public void sortString2(String xpath) {

		List<WebElement> countryList = new ArrayList<>();

		countryList = driver.findElements(By.xpath(xpath));

		System.out.println(countryList);

		List<String> countryListText = new ArrayList<>();

		for (WebElement rank : countryList) {

			countryListText.add(rank.getText());
		}
		System.out.print(countryListText);

		SortedSet<String> expectedCountryList = new TreeSet<>(countryListText);

		Assert.assertEquals(countryListText, expectedCountryList);

	}

	@Test

	public void most() throws InterruptedException {

		driver.get(url);

		driver.findElement(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead/tr/th[3]"))
				.click();

		List<WebElement> rows = driver.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr"));
		String tablePath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/";

		List<String[]> medalTable = new ArrayList<>();

		for (int i = 0; i < rows.size(); i++) {
			System.out.println(rows.get(i).getText());
			medalTable.add(new String[] { 
					driver.findElement(By.xpath(tablePath + "tr[" + (i + 1) + "]//a")).getText(),
					driver.findElement(By.xpath(tablePath + "tr[" + (i + 1) + "]/td[2]")).getText(),
					driver.findElement(By.xpath(tablePath + "tr[" + (i + 1) + "]/td[3]")).getText(),
					driver.findElement(By.xpath(tablePath + "tr[" + (i + 1) + "]/td[4]")).getText(),
					driver.findElement(By.xpath(tablePath + "tr[" + (i + 1) + "]/td[5]")).getText() });
		}

		for (String[] strings : medalTable) {
			System.out.println(Arrays.toString(strings));
		}

		// GOLD
		int mostMedal = 0;
		String mostMedalCountry = "";
		for (String[] row : medalTable) {
			if (Integer.parseInt(row[1]) > mostMedal) {
				mostMedal = Integer.parseInt(row[1]);
				mostMedalCountry = row[0];
			}
		}

		System.out.println(mostMedalCountry);
		Assert.assertEquals(mostMedalCountry, "United States"); // GOLD

		// SILVER
		mostMedal = 0;
		mostMedalCountry = "";
		for (String[] row : medalTable) {
			if (Integer.parseInt(row[2]) > mostMedal) {
				mostMedal = Integer.parseInt(row[2]);
				mostMedalCountry = row[0];
			}
		}

		System.out.println(mostMedalCountry);
		Assert.assertEquals(mostMedalCountry, "United States"); // SILVER

		// BRONZE
		mostMedal = 0;
		mostMedalCountry = "";
		for (String[] row : medalTable) {
			if (Integer.parseInt(row[3]) > mostMedal) {
				mostMedal = Integer.parseInt(row[3]);
				mostMedalCountry = row[0];
			}
		}

		System.out.println(mostMedalCountry);
		Assert.assertEquals(mostMedalCountry, "United States"); // BRONZE

		// TOTAL
		mostMedal = 0;
		mostMedalCountry = "";
		for (String[] row : medalTable) {
			if (Integer.parseInt(row[4]) > mostMedal) {
				mostMedal = Integer.parseInt(row[4]);
				mostMedalCountry = row[0];
			}
		}

		System.out.println(mostMedalCountry);
		Assert.assertEquals(mostMedalCountry, "United States"); // TOTAL 18 silver

		String country1 = "", country2 = "";
		OUTER: for (String[] rowOuter : medalTable) {
			for (String[] rowInner : medalTable) {
				if (Integer.parseInt(rowOuter[2]) + Integer.parseInt(rowInner[2]) == 18) {
					country1 = rowOuter[0];
					country2 = rowInner[0];
					break OUTER;
				}
			}
		}

		System.out.println(country1 + " & " + country2);
		
		// country row & col
		System.out.println(
				Arrays.toString(rowColNumber(medalTable, "Italy"))
				);
		

	}
	
	int[] rowColNumber(List<String[]> table, String countryName) {
		for (int i = 0; i < table.size(); i++) {
			if(table.get(i)[0].contains(countryName))
				return new int[] {i+1, 2};
			
		}
		return new int[] {0,0};		// return 0,0 if not found
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
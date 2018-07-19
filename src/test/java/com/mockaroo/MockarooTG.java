package com.mockaroo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooTG {

	WebDriver driver;

	@BeforeClass

	public void setUp() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("https://mockaroo.com/");

	}

	@Test(priority = 1)
	public void verifyTitle() {

		String expectedTitle = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";

		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 2)
	public void realisticGen() {

		assertTrue(driver.findElement(By.xpath("//div[@class='brand']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='tagline']")).isDisplayed());

	}

	@Test(priority = 3)

	public void removeX() throws InterruptedException {

		List<WebElement> list = driver.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));

		Iterator<WebElement> iter = list.iterator();

		while (iter.hasNext()) {
			Thread.sleep(1000);
			iter.next().click();

		}

	}

	@Test(priority = 4)
	public void fieldDisp() {

		assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).isDisplayed());

	}

	@Test(priority = 5)
	public void enabled() {

		assertTrue(driver.findElement(By.xpath("//a[.='Add another field']")).isEnabled());

	}

	@Test(priority = 6)
	public void defaultNo() {

		String actual = driver.findElement(By.xpath("//input[@value='1000']")).getAttribute("value");

		assertEquals(actual, "1000");

	}

	@Test(priority = 7)
	public void formatCsv() {

		String actual = driver.findElement(By.xpath("//option[.='CSV']")).getText();
		assertEquals(actual, "CSV");

	}

	@Test(priority = 8)
	public void lineEnding() {
		String actual = driver.findElement(By.xpath("//option[.='Unix (LF)']")).getText();
		assertEquals(actual, "Unix (LF)");

	}

	@Test(priority = 9)
	public void checkBox() {

		assertTrue(driver.findElement(By.xpath("//input[@id='schema_include_header']")).isSelected());
		assertFalse(driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected());
	}

	@Test(priority = 10)
	public void clickonAdd() {

		driver.findElement(By.xpath("//a[.='Add another field']")).click();
		driver.findElement(By
				.xpath("//div[@class='fields'][7]/div[@class='column']" + "/input[@class='column-name form-control']"))
				.sendKeys("City");

	}

	@Test(priority = 11)
	public void chooseType() throws InterruptedException {

		driver.findElement(By.xpath("//div[@class='fields'][7]//input[@class='btn btn-default']")).click();

		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//h3[.='Choose a Type']")).isDisplayed());

	}

	@Test(priority = 12)
	public void searchCity() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("city");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='type-name']")).click();

	}

	@Test(priority = 13)
	public void searchCountry() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).clear();
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).sendKeys("Country");
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='type_search_field']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("Country");
		driver.findElement(By.xpath("//div[.='Country']")).click();

	}

	@Test(priority = 14)
	public void download() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.='Download Data']")).click();

	}

	@Test(priority = 15)
	public void readlines() throws InterruptedException {
		Thread.sleep(2000);
		try (FileReader fr = new FileReader("/Users/tanergur/Downloads/MOCK_DATA (15).csv");
				BufferedReader br = new BufferedReader(fr);) {
			// System.out.println(br.readLine());
			assertEquals(br.readLine(), "City,Country");
			int count = 0;
			while (!(br.readLine() == null)) {

				count++;
			}
			assertEquals(String.valueOf(count), "1000");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 16)
	public void cityArrayList() {
		List<String> city = new ArrayList<>();
		List<String> country = new ArrayList<>();

		try (FileReader fr = new FileReader("/Users/tanergur/Downloads/MOCK_DATA (15).csv");
				BufferedReader br = new BufferedReader(fr);) {
			String str = "";

			while ((str = br.readLine()) != null) {

				city.add(str.substring(0, str.indexOf(",")));
				country.add(str.substring(str.indexOf(",") + 1));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		city.remove(0);
		country.remove(0);

		Collections.sort(city);
		Collections.sort(country);

		System.out.println(city);
		System.out.println(country);

		int longestCityNo = city.get(0).length();
		String longestCity = city.get(0);
		int shortestCityNo = city.get(0).length();
		String shortestCity = city.get(0);

		for (int i = 0; i < city.size(); i++) {
			if (city.get(i).length() > longestCityNo) {
				longestCity = city.get(i);
			} else if (city.get(i).length() < shortestCityNo) {
				shortestCity = city.get(i);
			}
		}
		System.out.println(longestCity);
		System.out.println(shortestCity);

		Set<String> eachCountry = new HashSet<>(country);

		for (String uniqueCountry : eachCountry) {

			int count = 0;
			for (int i = 0; i < country.size(); i++) {

				if (uniqueCountry.equals(country.get(i))) {
					count++;
				}
			}

			System.out.println(uniqueCountry + "-" + count);
		}
		Set<String> uniqueCity = new HashSet<>(city);

		int countCity = 1;
		for (int i = 0; i < city.size() - 1; i++) {
			if (!city.get(i + 1).equals(city.get(i))) {

				countCity++;
			}
		}
		assertEquals(String.valueOf(countCity), String.valueOf(uniqueCity.size()));

		Set<String> uniqueCountry2 = new HashSet<>(country);

		int countCountry = 1;

		for (int i = 0; i < country.size() - 1; i++) {

			if (!country.get(i + 1).equals(country.get(i))) {

				countCountry++;
			}
		}
		assertEquals(String.valueOf(countCountry), String.valueOf(uniqueCountry2.size()));
	}

}

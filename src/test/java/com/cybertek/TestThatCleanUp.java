	package com.cybertek;

	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

/*
Test Case 1
    Go to amazon
    search for something
Test Case 2
    Go to Google
    Search for something
*/
	public class TestThatCleanUp {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void searchGoogle() {
		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.findElement(By.id("lst-ib")).sendKeys("selenium cookbook" + Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("selenium cookbook"));
	}

	@Test
	public void searchAmazon() {
		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("fathers day gift" + Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("Just Me and My Dad (Little Critter)"));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}

package com.cybertek;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Notes {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@Test
	public void searchVerification() {

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("phone" + Keys.ENTER);
		List<WebElement> results = driver.findElements(By.xpath("//ul[@id='s-results-list-atf']/li//h2"));
		for (WebElement result : results) {
			System.out.println(result.getText());
			boolean check=result.getText().toLowerCase().contains("iphone");
			Assert.assertTrue(check);
		}
	}

}

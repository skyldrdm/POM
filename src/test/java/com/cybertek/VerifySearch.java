package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySearch {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://etsy.com");
		String searchTerm = "fathers day gifts";

		WebElement input = driver.findElement(By.id("search-query"));

		// driver.findElement(By.id("search-query"));
		input.sendKeys(searchTerm + Keys.ENTER);// page refreshed // directed to results page // locate the element again									

		input = driver.findElement(By.id("search-query"));

		String actual = input.getAttribute("value");

		System.out.println(actual);

		if (actual.equals(searchTerm)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
			System.out.println("expected:\t" + searchTerm);
			System.out.println("found:\t" + actual);
		}
		driver.close();
	}
}
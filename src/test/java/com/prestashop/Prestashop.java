package com.prestashop;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Prestashop {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@Test
	public void WrongCredentialsTest() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("kyldrdm@gmail.com" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("12345" + Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Authentication failed."));
		//System.out.println("Authentication failed.");
	}
	@Test
	public void InvalidEmailTest() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("skyldrdm@gmail" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("12345" + Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Invalid email address."));
		//System.out.println("Invalid email address.");
	}
	@Test
	public void BlankEmailTest() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("12345" + Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("An email address required"));
		//System.out.println("An email address required");
	}
	@Test
	public void BlankPasswordTest() {

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("skyldrdm@gmail.com" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("" + Keys.ENTER);
		driver.findElement(By.id("SubmitLogin")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("Password is required."));
		//System.out.println("Password is required.");
	}
	
	
	
	
	
	
	
	
	
	

}

package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearh {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();//set up chrome driver path
		WebDriver driver=new ChromeDriver();//invoke selenium webdriver
		driver.manage().window().fullscreen();//fullscreen
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);//set universal wait time in case web page is slow
		
		/*step 1. launch browser and navigate to https://dice.com
		  expected: dice home page should be displayed
		*/
		String url= "http://dice.com";
		driver.get(url);
		
		String actualTitle=driver.getTitle();
		String expectedTitle="Job Search for Technology Professionals | Dice.com";
				
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step Pass. Dice homepage succesfully loaded.");
		}else {
			System.out.println("Step Fail. Dice homepage did not load successfully loaded.");
			throw new RuntimeException("Step fail.Dice homepage did not load successfully loaded.");
		}
		
		String keyword="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		
		String location="22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count=driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		
		int countResult=Integer.parseInt(count.replace(",",""));
		
		if(countResult>0) {
			System.out.println("Step PASS: Keyword: "+keyword+ " search returned"+ count);
		}else {
			System.out.println("Step FAIL: Keyword: "+keyword+ " search returned"+ count);
		}
		
		driver.close();
		System.out.println("TEST COMPLETED -"+ LocalDateTime.now());	
}
}
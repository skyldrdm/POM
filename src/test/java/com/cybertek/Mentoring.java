package com.cybertek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mentoring {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("http:/orbitz.com");
		
		driver.findElement(By.tagName("input"));
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector(".icon.icon-packages-double")).click();		//class
		
		driver.findElement(By.cssSelector("#tab-package-tab-hp")).click();				// use # only id in css
		
		driver.findElement(By.cssSelector("button#tab-package-tab-hp")).click();
		
		driver.findElement(By.cssSelector("ul.tabs.cf.col:li(4)"));
		
		driver.findElement(By.xpath("//ul[@class=tabs cf col"));						// E[@A='t'] E-> tag A-> attribute t-> value //exp: //input[@type='checkbox'] //input[contains(@id, ‘username’)]
		
		
	}	
	
}

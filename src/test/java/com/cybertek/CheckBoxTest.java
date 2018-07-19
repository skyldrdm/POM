package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/*
TC 2: Verify checkbox functionality
1.Open browser
2.Go to http://the-internet.herokuapp.com/checkboxes 
3.Verify that the first checkbox is not selected 
4.Verify that the second checkbox is selected 
5.Click on the first checkbox
6.Verify that the first checkbox is selected 
7.Verify that the second checkbox is selected 
8.Click on the second checkbox
9.Verify that the first checkbox is selected
10. Verify that the second checkbox is not selected 
*/
public class CheckBoxTest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/checkboxes");
		
		//WebElement firstOne = driver.findElement(By.tagName("input"));
		WebElement firstOne = driver.findElement(By.xpath("//input[@type='checkbox']"));
		//WebElement firstOne = driver.findElement(By.cssSelector("input[type='checkbox']"));

		//WebElement secondOne = driver.findElement(null);
		WebElement secondOne = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));

		System.out.println("first");
		
		System.out.println(firstOne.isSelected());
		firstOne.click();
		System.out.println(firstOne.isSelected());

		
		System.out.println("second");
		
		System.out.println(secondOne.isSelected());
		secondOne.click();
		System.out.println(secondOne.isSelected());

		driver.close();
	}

}

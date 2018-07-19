package com.dice;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
HOMEWORK
1)Create arraylist of keywords.
add 20 different keywords
list.add("java");
pass each item to search box and print accordingly.
modify your arraylist 
java-1234
2) Store all keywords into a text file 
read the text file and  repeat above steps.
store keyword and results count into an arraylist.
----
after closing browser.
print contents of arraylist that was updated each time 
we looped.
https://github.com/CybertekSchool/selenium-maven-automation
Job_List.txt
*/

public class DiceJobSearch1 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		List<String> list = new ArrayList<>();
		list.add("Developer");
		list.add("Information Security Risk Analyst");
		list.add("Senior Developer");
		list.add("Javascript Developer");
		list.add("Product Owner");
		list.add("Sr Full Stack Developer");
		list.add("Senior Ruby on Rails Developer");
		list.add("Senior Data Architect");
		list.add("HRIS Workday Solutions Architect");
		list.add("Junior Data Analyst");
		list.add("IT NOC Analyst");
		list.add("Senior SCCM Desktop Engineer");
		list.add("Python developer");
		list.add("Senior Citrix Engineer");
		list.add("Senior Net Developer");
		list.add("Java RAD Developer");
		list.add("Sr. IT Project Manager");
		list.add("Java Developer");
		list.add("Tech Lead");
		list.add("Senior Business Data Analyst");
	

		for (int i = 0; i < list.size(); i++) {
			driver.get("http://dice.com");
			String actualTitle = driver.getTitle();
			String expectedTitle = "Job Search for Technology Professionals | Dice.com";
			if (actualTitle.equals(expectedTitle)) {
				System.out.println("Step PASS. Dice homepage successfully loaded");
			} else {
				System.out.println("Step FAIL. Dice homepage did not load successfully");
				throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
			}
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(list.get(i));
			String location = "11732";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			driver.findElement(By.id("findTechJobs")).click();
			String count = driver.findElement(By.id("posiCountId")).getText();
			int result = Integer.parseInt(count.replace(",", ""));
			if (result > 0) {
				System.out.println("Step PASS: Keyword : " + 
						list.get(i) + 
						" search returned \"" + 
						result + 
						"\" results in \"" + 
						location + "\"");
			} else {
				System.out.println("Step FAIL: Keyword : " + 
						list.get(i) + 
						" search returned \"" + 
						result + 
						"\" result in \"" + 
						location + "\"");
			}
			list.set(i, list.get(i) + "-" + count);
		}
		driver.close();
		System.out.println("TEST COMPLETED : " + LocalDateTime.now());
		System.out.println("New ArrayList:");
		System.out.println(list);
	}
}

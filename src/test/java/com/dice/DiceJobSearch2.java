package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
public class DiceJobSearch2 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		List<String> list = new ArrayList<>();

		try (FileReader fr = new FileReader("jobtitle.txt"); 
				BufferedReader br = new BufferedReader(fr);) {

			String str = "";

			while ((str = br.readLine()) != null) {

				list.add(str);

			}
		} catch (Exception e) {

			System.out.println("There is no text found");
			e.printStackTrace(); // it will give you standard output.
		}

		for (int i = 0; i < list.size(); i++) {

			String url = "https://dice.com";
			driver.get(url);

			String actualTitle = driver.getTitle();
			String expectedTitle = "Job Search for Technology Professionals | Dice.com";

			if (actualTitle.equals(expectedTitle)) {

				System.out.println("Step PASS. Dice Homepage Successfully loaded");

			} else {

				System.out.println("Step FAIL. Dice Homepage did not Successfully loaded");
				throw new RuntimeException("Step FAIL.  Dice Homepage did not Successfully loaded");
			}

			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(list.get(i));

			String location = "11732";

			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);

			driver.findElement(By.id("findTechJobs")).click();

			String jobCount = driver.findElement(By.id("posiCountId")).getText();

			int result = Integer.parseInt(jobCount.replace(",", ""));

			if (result > 0) {

				System.out.println("Step PASS: Keyword : " + list.get(i) + "search returned\n"

						+ result + "results in \n" + location);
			} else {

				System.out.println("Step FAIL: Keyword : " + list.get(i) + "search returned"

						+ result + "results in -" + location);

			}

			list.set(i, list.get(i) + "-" + result);

		}

		driver.close();
		System.out.println("TEST COMPLETED " + LocalDateTime.now());

		System.out.println("Modified ArrayList: " + list);

	}

}

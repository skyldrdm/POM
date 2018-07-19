package propertyClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * 	Create a maven project 

	Create a properties file to store 
	URL = http://automationpractice.com/index.php
	User = waya@IOreak.net
	Password = password

	inside your test use the utility class to access URL , USER , PASSWORD

	Create a test for login functionality 

	// NAVIGATE TO THE PAGE 
	// ENTER USER NAME AND PASSWORD
	// VERIFY YOU ARE LOGGED IN SUCCESSFULLY 
 */

public class ConfigLoader2 {
	
WebDriver driver;
	
	@BeforeClass 
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.get(ConfigLoader2.getMyValue("URL"));
		
	}

	@Test
	public void getAllProductsV1() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("email")).sendKeys(ConfigLoader2.getMyValue("User"));
    	driver.findElement(By.id("passwd")).sendKeys(ConfigLoader2.getMyValue("Password"));
    	driver.findElement(By.xpath("//button[@type='submit']")).click();
}
	
	private static Properties prop;

	static {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("SeleniumProject.properties");
			prop.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static String getMyValue(String key) {

		String value = prop.getProperty(key);

		return value;

	}
}
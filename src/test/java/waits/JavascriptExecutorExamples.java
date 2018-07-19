package waits;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavascriptExecutorExamples {

	@Test
	public void alerts() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// class which enables executing js commands
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// takes a js code as a string argument and executes on the browser

		js.executeScript("alert('WARNING: some bad things are happening')");

	}

	@Test
	public void scroll() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("http://amazon.com");

		WebElement element = driver.findElement(By.xpath("//*[.='Get to Know Us']"));

		js.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	@Test
	public void click() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("http://amazon.com");
		WebElement element = driver.findElement(By.partialLinkText("Manage Your"));

		js.executeScript("arguments[0].click()", element);

	}

}

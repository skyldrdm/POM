package waits;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AnotherWait {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}
	
	@Test
	public void one() {
		//driver = new ChromeDriver();
		driver.findElement(By.className("flkajfdk"));
	}
	
	@Test
	public void two() {
		driver.findElement(By.className("flkajfdk"));
	}
}
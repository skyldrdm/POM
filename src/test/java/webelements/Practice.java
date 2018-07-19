package webelements;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practice {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void WebElement() throws InterruptedException {
		//WebElement email = driver.findElement(By.name("Email"));
		List<WebElement> input = driver.findElements(By.xpath("//input[@type='text']"));
		assertEquals(input.size(), 2);
		//  Loop through each inputbox and enter random names
		for (WebElement webElement : input) {
			webElement.sendKeys(new Faker().name().firstName());
		}
		List<WebElement> dropDown = driver.findElements(By.tagName("select"));//-->dropDown
		assertEquals(dropDown.size(), 3);
	   //  Loop through each dropDown and randomly select by index
		for (WebElement webElement : dropDown) {
		 Select select= new Select(webElement);
		 select.selectByIndex(new Faker().number().numberBetween(1, select.getOptions().size()));
		}
		List<WebElement> checkBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		assertEquals(checkBox.size(), 9);
		//  Loop through each checkBoxes and select each one
		for (WebElement webElement : checkBox) {
			webElement.click();
		}
		List<WebElement> radioButton = driver.findElements(By.xpath("//input[@type='radio']"));
		assertEquals(radioButton.size(), 9);
		//  Loop through each radioButton and click one by one by waiting one second intervals 
		for (WebElement webElement : radioButton) {
			Thread.sleep(1000);
			webElement.click();
		}
		List<WebElement> button = driver.findElements(By.tagName("button"));
		assertEquals(button.size(), 1);
		//  click all buttons
		for (WebElement webElement : button) {
			webElement.click();
		}

	}
}
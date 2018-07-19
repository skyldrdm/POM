package webelements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		// how many links on github home page
		int numberOfLinksOnGithub = links.size();
		System.out.println("number of links:" + numberOfLinksOnGithub);
		// print text of each link
		for (WebElement link : links) {
			if (!link.getText().isEmpty()) {
				System.out.println(link.getText());
			}
		}
		// add each link text into a list of Strings
		List<String> linkNames = new ArrayList<>();

		for (WebElement link : links) {
			if (!link.getText().isEmpty()) {
				linkNames.add(link.getText());
			}
		}

		System.out.println(linkNames.toString());
	}

	/**
	 * navigate to
	 * https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg
	 * Find all input boxes and assign to List of webelements -> 2 Find all drop
	 * down boxes and assign to another List of webelements -> 3 Find all check
	 * boxes and assign to another List of webelements -> 9 Find all radio boxes and
	 * assign to another List of webelements -> 9 Find all buttons and assign to
	 * another List of webelements -> 1 assert each one's count
	 */
	@Test
	public void SeleniumWebElementsForm() {
		driver.navigate().to(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		List<WebElement> dropDowns = driver.findElements(By.tagName("select"));
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
		List<WebElement> buttons = driver.findElements(By.tagName("button"));

		assertEquals(inputBoxes.size(), 2);
		assertEquals(dropDowns.size(), 3);
		assertEquals(checkBoxes.size(), 9);
		assertEquals(radioButtons.size(), 9, "Message will show if it fails");
		assertEquals(buttons.size(), 1, "Message will show if it fails");

		/*
		 * Homework: Loop through each inputbox and enter random names Loop through each
		 * dropDown and randomly select by index Loop through each checkBoxes and select
		 * each one Loop through each radioButton and click one by one by waiting one
		 * second intervals click all buttons
		 */
	}

	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();

		for (WebElement flower : images) {
			srcs.add(flower.getAttribute("src"));
		}

		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}

	}

}
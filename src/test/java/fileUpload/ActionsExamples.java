package fileUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.beust.jcommander.JCommander.Builder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsExamples {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void doubleClickTest() {

		driver.get("https://www.primefaces.org/showcase/ui/misc/effect.xhtml");

		WebElement slides = driver.findElement(By.id("slide_header"));

		Actions action = new Actions(driver);
		// doubleClick --> double clicks on an element
		action.doubleClick(slides).perform();

	}

	@Test
	public void hoverTest1() throws InterruptedException {
		driver.get("http://amazon.com");
		WebElement hello = driver.findElement(By.xpath("//span[.='Hello. Sign in']"));

		Actions action = new Actions(driver);

		// moves the mouse on top of the target element
		action.moveToElement(hello).perform();

		Thread.sleep(1000);
		driver.findElement(By.linkText("Your Garage")).click();
	}

	@Test
	public void hoverTest2() throws InterruptedException {
		driver.get("http://amazon.com");

		Actions action = new Actions(driver);
		
		//span[.='Back to top']
		action.moveToElement(driver.findElement(By.xpath("//span[.='Back to top']"))).perform();

	}

	@Test
	public void scrollTest() throws InterruptedException {
		driver.get("http://amazon.com");
		Actions action = new Actions(driver);
		
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
	}

	@Test
	public void dragDropTest1() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		Actions action = new Actions(driver);
		// drag drop operation involves 2 elements. source and target
		// source --> element which we will move
		// target --> element where we drop the source
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(source, target).perform();
		
		// action.dragAndDropBy(source, 0, -200).perform();;
	}

	@Test
	public void dragDropTest2() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		Actions action = new Actions(driver);
		// drag drop operation involves 2 elements. source and target
		// source --> element which we will move
		// target --> element where we drop the source
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		// 0. move the mouse on top the source
		// 1. click and hold mouse
		// 2. move to target element
		// 3. release the mouse
		// this example chains multiple operations. when we chain multiple operations we
		// also use
		// build() to add all those operation
		action.moveToElement(source).clickAndHold().moveToElement(target).release().build().perform();

		// action.dragAndDropBy(source, 0, -200).pause(1000).
		// dragAndDropBy(source, 200, 0).pause(1000).
		// dragAndDropBy(source, 0, -200).build().perform();

	}

}

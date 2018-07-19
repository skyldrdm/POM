package fileUploadActions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionExamples {

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
    // //span[.='Back to top']

    action.moveToElement(driver.findElement(By.xpath("//span[.='Back to top']"))).perform();

    }
  
  
  @Test
  public void scrollTest() throws InterruptedException {
    driver.get("http://amazon.com");
    Actions action = new Actions(driver);
//
    action.sendKeys(Keys.PAGE_DOWN).perform();;
    action.sendKeys(Keys.ARROW_DOWN).perform();;

    action.sendKeys(Keys.ARROW_DOWN).perform();;

    }

}
	
	
	
	
	
	
	
	


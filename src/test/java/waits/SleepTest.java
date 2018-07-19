package waits;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * go to http://the-internet.herokuapp.com/dynamic_loading/1
 * click start
 * verify hello world is displayed
 */
public class SleepTest {
  @Test
  public void test() throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

    driver.findElement(By.tagName("button")).click();

    // wait
    Thread.sleep(5000);

    // locate element that is already in the html
    WebElement message = driver.findElement(By.xpath("//h4[.='Hello World!']"));

    // verify elemenet visible
    assertTrue(message.isDisplayed(), "Message not displayed");

  }

  public static void pause(long l) {
    try {
      Thread.sleep(l);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}


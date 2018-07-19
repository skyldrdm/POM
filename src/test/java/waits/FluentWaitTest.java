package waits;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitTest {
  @Test
  public void test() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    driver.findElement(By.tagName("button")).click();

    // fluentwait and webdirverwait both implement wait interface

    // creating wait object
    // we pass how it should wait --withTimeout
    // how often they should check --> withTimeout
    // what exception should be ignored --> ignoring(NoSuchElementException.class)

    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)

        .withTimeout(Duration.ofSeconds(5))

        .pollingEvery(Duration.ofSeconds(1))

        .ignoring(NoSuchElementException.class);

    // the condition which the wait object will wait for

    WebElement message = wait.until(dr -> dr.findElement(By.id("message")));

    assertTrue(message.isDisplayed());
    assertEquals(message.getText(), "It's gone!");
    // import com.google.common.base.Function;

  }

}
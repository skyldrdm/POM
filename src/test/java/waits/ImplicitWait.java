package waits;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImplicitWait {
  /*
   * how implicit wait should not be used
   */
  @Test
  public void notAGoodTest() throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

    driver.findElement(By.tagName("button")).click();
    WebElement message = driver.findElement(By.xpath("//h4[.='Hello World!']"));

    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    assertTrue(message.isDisplayed(), "Message not displayed");
    
  }
  
  @Test
  public void realExample() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    // will wait for specified duration while executing the findElement method
    // will only wait if the find element method does not find anything
    // findElement method will keep attempting to find the element during this time
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    
    
    // this setting will control how long driver should wait then the page is loading
    driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
    
    
    // will wait for background ajax processes to finish
    driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    
    
    driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    
    driver.findElement(By.id("btn")).click();
    
    WebElement message = driver.findElement(By.id("message"));
    
    assertTrue(message.isDisplayed(), "Message should be displayed");
    
    assertEquals(message.getText(), "It's gone!");
    
    driver.findElement(By.id("btn")).click();
    
    message = driver.findElement(By.id("message"));
    
    assertTrue(message.isDisplayed(), "Message should be displayed");
    
    assertEquals(message.getText(), "It's back!");
    
    
    
    
  }

}
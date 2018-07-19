package popUp;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class IframesTest extends TestBase {
  @Test 
  public void iframeTest1() {
    driver.get("http://the-internet.herokuapp.com/iframe");
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());
    
    // find the iframe as an element and switch to the element
    WebElement iframe = driver.findElement(By.tagName("iframe"));
    // switchTo() --> swtiches to the frame
    driver.switchTo().frame(iframe);
    driver.findElement(By.id("tinymce")).sendKeys(" more text");

    // Selects either the first frame on the page, 
    //or the main document when a page contains iframes.
    driver.switchTo().defaultContent();
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());

  }
  
  
  @Test 
  public void iframeTest2() {
    driver.get("http://the-internet.herokuapp.com/iframe");
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());
    
    // pass the id or name of the iframe to the switchTo method
    driver.switchTo().frame("mce_0_ifr");
    driver.findElement(By.id("tinymce")).sendKeys(" more text");

    // Selects either the first frame on the page, 
    //or the main document when a page contains iframes.

    driver.switchTo().defaultContent();
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());

  }
  
  
  @Test 
  public void iframeTest3() {
    driver.get("http://the-internet.herokuapp.com/iframe");
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());
    
//    // pass the index the iframe to the switchTo method
    driver.switchTo().frame(0);
    driver.findElement(By.id("tinymce")).sendKeys(" more text");
    
    // switchTo().parentFrame()   --> switches to the parent of the current frame
    driver.switchTo().parentFrame();
    assertTrue(driver.findElement(By.linkText("Elemental Selenium")).isDisplayed());

  }
  
  
}


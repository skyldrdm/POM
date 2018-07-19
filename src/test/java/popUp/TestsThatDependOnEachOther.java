package popUp;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestsThatDependOnEachOther extends TestBase {
  
  @Test
  (priority = 1)
  public void opensThePage() {
    driver.get("http://google.com");
  }

  @Test
  (priority = 2)
  public void types() {
    driver.findElement(By.name("q")).sendKeys("ASdfasfasd");
  }
}

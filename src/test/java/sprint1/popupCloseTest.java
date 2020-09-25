package sprint1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utilities.BaseTest;

public class popupCloseTest extends BaseTest{
  @Test
  public void popUpHandel_Test() throws InterruptedException {
	  
	  
	  openBrowser("chrome");
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		
		Thread.sleep(3000);
		
		driver.switchTo().frame(0);
		driver.findElement(By.id("closeBtn")).click();
		
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("TV")).click();
		
		
		
		  }
}

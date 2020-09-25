package sprint1;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utilities.BaseTest;
import Utilities.Utility;

public class Day3_Test extends BaseTest {
  @Test
  public void verifyCartErrorMessage() throws InterruptedException, IOException {
	  
	  openBrowser("chrome");
	  driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.linkText("MOBILE")).click();
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
		
		  
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();	    
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");
	    test.log(Status.INFO, "enterd 1000 quantity:",MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
	   	    
		String actErrorMsg = driver.findElement(By.xpath("//span[contains(text(),'Some of the products cannot be ordered in requeste')]")).getText();
		String expErrorMsg = "Some of the products cannot be ordered in requested quantity.";
		
		assertEquals(actErrorMsg, expErrorMsg);
		
		driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
		String actCartMsg = driver.findElement(By.xpath("//div[@class='cart-empty']//p[contains(text(),'You have no items in your shopping cart.')]")).getText();
		String expCartMsg = "You have no items in your shopping cart.";
	  
		try {
			assertEquals(actCartMsg, expCartMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
}

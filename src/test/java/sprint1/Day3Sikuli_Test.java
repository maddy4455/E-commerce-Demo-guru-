package sprint1;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import Utilities.BaseTest;

public class Day3Sikuli_Test extends BaseTest{
  @Test
  public void verifyWithSikuli() throws FindFailed {
	  
	  openBrowser("chrome");
	  driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.linkText("MOBILE")).click();
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
		
		Screen s=new Screen();
		Pattern qtyImg=new Pattern("C:\\Users\\yavyo\\Desktop\\sikuliimages\\Update_Cart.PNG");
		Pattern updatebtnImg=new Pattern("C:\\Users\\yavyo\\Desktop\\sikuliimages\\update_button.PNG");
		
		s.wait(qtyImg);
		s.type(qtyImg, "1000");
		s.click(updatebtnImg);
		
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

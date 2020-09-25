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

public class TC_005 extends BaseTest {
	@Test
	public void emailTest() throws InterruptedException, IOException {

		openBrowser("chrome");
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("firstname")).sendKeys("maddy");
		driver.findElement(By.id("middlename")).sendKeys("don");
		driver.findElement(By.id("lastname")).sendKeys("king");
		driver.findElement(By.id("email_address")).sendKeys("test123jh8@gmail.com");
		driver.findElement(By.name("password")).sendKeys("test123");
		driver.findElement(By.name("confirmation")).sendKeys("test123");
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@class='button']")).click();

		Thread.sleep(3000);

		String actregMsg = driver
				.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]"))
				.getText();
		String expregMsg = "Thank you for registering with Main Website Store.";

		assertEquals(actregMsg, expregMsg);
		test.log(Status.INFO, "registration success and screenshot captured: ",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
Thread.sleep(3000);

driver.findElement(By.linkText("TV")).click();
driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();

Thread.sleep(3000);

		driver.findElement(By.name("save_and_share")).click();
		
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("madhavrr5@gmail.com");
		driver.findElement(By.id("message")).clear();
		driver.findElement(By.id("message")).sendKeys("Hi honey this tv fit for your requirement");
		driver.findElement(By.xpath("//div[@class='buttons-set form-buttons']//button[@class='button']")).click();
		
		Thread.sleep(3000);
		String actwishlitmsg = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();
		String expWishListmsg = "Your Wishlist has been shared.";
		
		try {
			assertEquals(actwishlitmsg, expWishListmsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}

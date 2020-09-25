package sprint1;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utilities.BaseTest;
import Utilities.Utility;

public class TC_001 extends BaseTest {
	@Test
	public void pageTitleTest() throws IOException, InterruptedException {

		openBrowser("chrome");

		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String homePageTitle = driver.getTitle();
		String expHomepageTILTE = "Home page";
		test.log(Status.INFO, "Homepage Title is:- " + homePageTitle);
		test.log(Status.INFO, "Homepage screenshot captured: ",
				 MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
		assertEquals(homePageTitle, expHomepageTILTE);
		
driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
String mobilePageTitle = driver.getTitle();
String expMpageTitle = "Mobile";
test.log(Status.INFO, "mobilePage Title is:- " + mobilePageTitle);
test.log(Status.INFO, "Mobile page screenshot captured: ",
		 MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
assertEquals(mobilePageTitle, expMpageTitle);
//driver.findElement(By.xpath("//select[@title='Sort By']")).sendKeys("Name");

new Select(driver.findElement(By.xpath("//select[@title='Sort By']"))).selectByVisibleText("Name");
test.log(Status.INFO, "Mobile sort by name page screenshot captured: ",
		 MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
	
	}
	
}

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

public class day2_Test extends BaseTest {
	@Test
	public void verifyThePrice() throws IOException, InterruptedException {

		openBrowser("chrome");
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("MOBILE")).click();
		test.log(Status.INFO, "Image of mobile list price: ",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());

		String xperiaPrice = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]")).getText();
		driver.findElement(By.id("product-collection-image-1")).click();
		String detailPrice = driver.findElement(By.xpath("//span[@class='price']")).getText();

		assertEquals(xperiaPrice, detailPrice);

	}
}

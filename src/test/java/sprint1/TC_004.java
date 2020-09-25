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

public class TC_004 extends BaseTest {
	@Test
	public void popupWindowVerify() throws InterruptedException, IOException {

		openBrowser("chrome");
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("MOBILE")).click();

		String mobile1 = driver.findElement(By.xpath("//h2[@class='product-name']//a[contains(text(),'Sony Xperia')]"))
				.getText();
		driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		System.out.println("mobile1= " + mobile1);
		Thread.sleep(1000);

		String mobile2 = driver.findElement(By.xpath("//h2[@class='product-name']//a[contains(text(),'IPhone')]"))
				.getText();
		System.out.println("mobile2= " + mobile2);
		driver.findElement(
				By.cssSelector("body.catalog-category-view.categorypath-mobile-html.category-mobile:nth-child(2) div.wrapper:nth-child(1) div.page:nth-child(2) div.main-container.col3-layout div.main div.col-wrapper div.col-main div.category-products ul.products-grid.products-grid--max-4-col.first.last.odd:nth-child(2) li.item.last:nth-child(2) div.product-info div.actions ul.add-to-links li:nth-child(2) > a.link-compare")).click();	
		Thread.sleep(1000);

//click on compare
		driver.findElement(By.xpath("//button[@class='button']//span//span[contains(text(),'Compare')]")).click();

//switch to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			test.log(Status.INFO, "popup window screenshot: ",
					MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
		}
//verify the popup window and check the porducts in it and heading "compare products"
		String actPopupHeding = driver.findElement(By.xpath("//h1[contains(text(),'Compare Products')]")).getText();
		String expPopupHeadng = "COMPARE PRODUCTS";
		System.out.println("popup page headline is: " + actPopupHeding);
		String popupmobil1 = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]")).getText();
		String popupMobile2 = driver.findElement(By.xpath("//a[contains(text(),'IPhone')]")).getText();
//verify the head line in pop up window
		try {
			assertEquals(actPopupHeding, expPopupHeadng);
		} catch (Exception e) {
			e.printStackTrace();
		}
// check sonyxperia  in popup window
		try {
			assertEquals(mobile1, popupmobil1);
		} catch (Exception e) {
			e.printStackTrace();
		}

//check iphone in popup window
		try {
			assertEquals(mobile2, popupMobile2);
		} catch (Exception e) {
			e.printStackTrace();
		}
//close popup window
		driver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();

//swith to main window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);

			Thread.sleep(2000);

		}
	}
}

package screenshotDeom;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utilities.BaseTest;
import Utilities.Utility;

public class getText extends BaseTest {
	
	

	@Test
	public void gettextdemo() throws IOException, InterruptedException {
		
		openBrowser("chrome");
		driver.get("https://www.google.co.in/");
		test.log(Status.INFO, "launching google.co.in: ",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("rajnikanth");
		test.log(Status.INFO, "enterd text in searbox: ",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
		
		String textData = driver.findElement(By.xpath("//input[@name='q']")).getAttribute("value");
		System.out.println(textData);

		driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']")).sendKeys(Keys.ENTER);
		String actpageTitle = driver.getTitle();
		System.out.println("page Title is: " + actpageTitle);
		String expPageTitle = "rajnikanth - Google Search";
		
		assertEquals(actpageTitle, expPageTitle);
		test.log(Status.PASS, "search box test successfull: "+actpageTitle);
		

	}

}

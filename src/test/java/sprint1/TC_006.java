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

public class TC_006 extends BaseTest{
	public String firstName="maddy";
	public String lasName="king";
	public String email="test123jh8@gmail.com";
	public String pw="test123";
	
  @Test
  public void purchaseProduct() throws IOException, InterruptedException {
	  
		openBrowser("chrome");
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(pw);
		driver.findElement(By.id("send2")).click();
		test.log(Status.INFO, "successfully entered into my accnt with valid crdntls: ",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());
		
		Thread.sleep(3000);
		
		//clicking on wishlist
		driver.findElement(By.xpath("//div[@class='block-content']//a[contains(text(),'My Wishlist')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
		
		//enter shipping details and get shipping estimation
		driver.findElement(By.id("country")).sendKeys("Canada");
		driver.findElement(By.id("region_id")).sendKeys("Alberta");
		driver.findElement(By.id("postcode")).sendKeys("100184");
		driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
		
		//verify the estmated shipping price
		String actEstimeteRate = driver.findElement(By.xpath("//dt[contains(text(),'Flat Rate')]")).getText();
		String expShippingRate = "Flat Rate";
		
		try {
			System.out.println("Flat Rate= "+actEstimeteRate );
			assertEquals(actEstimeteRate, expShippingRate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String actShippingPrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
		String expShippingPrice = "Fixed - $5.00";
		
		try {
			System.out.println("Fixed - $5.00= "+actEstimeteRate);
			assertEquals(actShippingPrice, expShippingPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//add shipping and update total
		driver.findElement(By.id("s_method_flatrate_flatrate")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();
		
		//verify the shipping price added to cart
		String actAddedshippingcost = driver.findElement(By.xpath("//tr[2]//td[2]")).getText();
		String expShippingtoCart = "$5.00";
		
		System.out.println("shipping Included: "+actAddedshippingcost);
		assertEquals(actAddedshippingcost, expShippingtoCart);
		
		//click to proceed checkout
		driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
		
		Thread.sleep(3000);
		
		//billing address
 // Check if Select element is present. If not present, it is the first time 
		//a customer has logged back in, to process what is in his/her wishlist
	    
	    try {
	    	Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
	    	int bAddrSize = bAddr.getOptions().size();
	    	bAddr.selectByIndex(bAddrSize-1); 
		    } catch (Exception e) {
		    	//e.printStackTrace();
		    	System.out.println("No dropdown element present");
		    }
	    
		driver.findElement(By.id("billing:firstname")).clear();
		driver.findElement(By.id("billing:firstname")).sendKeys("maddy");
		driver.findElement(By.id("billing:middlename")).clear();
		driver.findElement(By.id("billing:middlename")).sendKeys("don");
		driver.findElement(By.id("billing:lastname")).clear();
		driver.findElement(By.id("billing:lastname")).sendKeys("king");
		driver.findElement(By.id("billing:street1")).clear();
		driver.findElement(By.id("billing:street1")).sendKeys("148 Crown Street");
		driver.findElement(By.id("billing:city")).clear();
		driver.findElement(By.id("billing:city")).sendKeys("New York");
		new Select(driver.findElement(By.id("billing:region_id"))).selectByIndex(43);
		driver.findElement(By.id("billing:postcode")).sendKeys("100165");
		new Select(driver.findElement(By.id("billing:country_id"))).selectByValue("US");
		driver.findElement(By.id("billing:telephone")).clear();
		driver.findElement(By.id("billing:telephone")).sendKeys("8850567890");
		
		//ship to different address
		driver.findElement(By.id("billing:use_for_shipping_no")).click();
		//closing the popup add
		driver.switchTo().frame(0);
		driver.findElement(By.id("closeBtn")).click();
		
		//back to main window
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@class='button']//span//span[contains(text(),'Continue')]")).click();
		
		
		
  }
}

package dataDriven;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.BaseTest;

public class DataproviderTest_002 extends BaseTest {
	@Test(dataProvider = "newcusData")
	public void newCusRegister(String username, String password, String CustomerName, String DOB, String Address,
			String city, String state, String pin, String telephone, String emailId, String cusPWD)
			throws InterruptedException {

		openBrowser("chrome");
		driver.get("http://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		driver.findElement(By.xpath("//input[@name='name']")).clear();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(CustomerName);

		driver.findElement(By.name("rad1")).click();
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(DOB);

		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(Address);

		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(city);

		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(state);

		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(pin);

		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(telephone);

		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(emailId);

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(cusPWD);
		driver.findElement(By.name("sub")).click();
		
		try {
			Alert alt = driver.switchTo().alert();
			String actualBoxMsg = alt.getText();
			alt.accept();
			String expBoxMsg = "please fill all fields";
			assertEquals(actualBoxMsg, expBoxMsg);
			
			
		} catch (NoAlertPresentException e) {
			
			String actcusPage = driver.getTitle();
			System.out.println("registerd cus page title is: "+actcusPage);
			assertTrue(actcusPage.contains("customer"));
 
		}
		

	}

	@DataProvider(name = "newcusData")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] {

		{ "mngr276570", "AtYdagE", "maddy", "05/09/1987", "summerside", "delaware", "newyork", "t6x3m4",
						"1234567890", "tes45t@gmail.com", "Data1233" },
		{ "mngr276570", "AtYdagE", "maddyr", "05/09/1989", "summerslide", "chicago", "washington", "t6x3m4",
							"12345678999", "test154@gmail.com", "Data0623" },
		{ "mngr276570", "AtYdagE", "maddgh", "05/09/1983", "summerville", "newyork", "newyork", "100184",
								"1234567890", "testrr12@gmail.com", "Data1223" }
				

		};

	}
}

package dataDriven;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class DataProviderTest_01 extends Utilities.BaseTest {
  @Test(dataProvider = "LoginData")
  public void Login(String userNmae, String password) {
	  
	  openBrowser("chrome");
	  
	  driver.get("http://demo.guru99.com/test/newtours/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.name("userName")).clear();
		driver.findElement(By.name("userName")).sendKeys(userNmae);
		
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.name("submit")).click();
		
		try {
			
			String actErrorMsg = driver.findElement(By.xpath("//span[contains(text(),'Enter your userName and password correct')]")).getText();
			String expErrorMsg = "Enter your userName and password correct";
			assertEquals(actErrorMsg, expErrorMsg);
			test.log(Status.PASS, "error message thrown as expected: "+actErrorMsg);
			
			
		} catch (Exception e) {
		
			String actMsg = driver.findElement(By.xpath("//h3[contains(text(),'Login Successfully')]")).getText();
			String expMsg = "Login Successfully";
			
			assertTrue(actMsg.contains(expMsg), "Logi succesfull");
			test.log(Status.PASS, "login success message displayed: "+actMsg);
		}
 
  }
  
  @DataProvider(name="LoginData")
  public Object[][] getDataFromDataprovider(){
	return new Object[][]
			{
		
		{"admin2", "admin78"},
		{"admin245", "admin5678"},
		{"admin2", "admin78"},
		{"admin123", "admin"}
		
			};
			
	  
  }
  
}

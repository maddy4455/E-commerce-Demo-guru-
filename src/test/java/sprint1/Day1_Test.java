package sprint1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Utilities.BaseTest;

public class Day1_Test extends BaseTest{
	
	 
  @Test
  public void f() throws IOException {
	  
	  openBrowser("chrome");

		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String demoSite  = driver.findElement(By.cssSelector("h2")).getText();
		//String demoSite = driver.getTitle();
		    System.out.println(demoSite);
		
		    // Step 3. Click on ‘MOBILE’ menu
		    driver.findElement(By.linkText("MOBILE")).click();	
	        // Step 5. In the list of all mobile , select ‘SORT BY’ dropdown as ‘name’		
		    new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
		   
		
  }
}

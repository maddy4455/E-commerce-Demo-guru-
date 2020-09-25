package demoPack;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Utilities.BaseTest;

public class TC_001 extends BaseTest{
  @Test
  public void f() {
	  openBrowser("chrome");
	  
		driver.get("https://www.atb.com/personal/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		assertEquals(title, "Personal Banking | AT");
	  
  }
}

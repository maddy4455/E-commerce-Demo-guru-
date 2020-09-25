package fullscreenshot;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Utilities.getFullPageScreenshotUtil;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class captureFullPage {

	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;
	public static String filePath;

	@BeforeTest
	public void startReport() {

		Date dt = new Date();
		filePath = dt.toString().replace(':', '_').replace(' ', '_') + ".html";

		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/extentRep.html" + filePath);
		report = new ExtentReports();
		report.attachReporter(reporter);

	}

	@Test
	public void captureScreenshot() {

		test = report.createTest("captureScreenshot");
		System.setProperty("webdriver.chrome.driver",
				"\\C:\\Users\\yavyo\\Desktop\\seleniumproject\\chromedriver.exe\\");
		driver = new ChromeDriver();
		driver.get("https://www.atb.com/personal/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		assertEquals("atb home", title);

	/*}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshotPath = getFullPageScreenshotUtil.captureFullPage(driver, "full page");
			test.fail(MarkupHelper.createLabel(result.getName() + "test case failed", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Screnshot Below: " + test.addScreenCaptureFromPath(screenshotPath));

		}*/

	}

	@AfterTest
	public void tearDown() {

		report.flush();

	}

}

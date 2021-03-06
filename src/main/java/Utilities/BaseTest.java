package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest {

	public static WebDriver driver;
	public String testName = null;
	public static String filePath;
	public static FileInputStream fis;
	public static Properties p;
	public static String projectpath = System.getProperty("user.dir");
	public static ExtentTest test;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;

	@BeforeTest
	public void init() throws Exception {

		Date dt = new Date();
		filePath = dt.toString().replace(':', '_').replace(' ', '_') + ".html";

		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/extentRep.html" + filePath);
		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("User Name", "Maddy");
		//report.setSystemInfo("User Name", "Maddy");

		fis = new FileInputStream(projectpath + "//log4jconfig.properties");
		PropertyConfigurator.configure(fis);

		fis = new FileInputStream(projectpath + "//data.properties");
		p = new Properties();
		p.load(fis);

	}

	public static void openBrowser(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"\\C:\\Users\\yavyo\\Desktop\\seleniumproject\\chromedriver.exe\\");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "false");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("user-data-dir=C:\\Users\\yavyo\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
			option.addArguments("--disable-notifications");
			driver = new ChromeDriver(option);
	

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\yavyo\\Desktop\\seleniumproject\\geckodriver.exe");
			ProfilesIni p = new ProfilesIni();
			FirefoxProfile profile = p.getProfile("Marchsel");
			profile.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions option = new FirefoxOptions();
			option.setProfile(profile);
			driver = new FirefoxDriver(option);
			
		}else if (browser.equals("ie")) {
			System.setProperty("WebDriver.ie.driver", "C:\\Users\\yavyo\\Desktop\\seleniumproject\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();

		}else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\yavyo\\Desktop\\seleniumproject\\msedgedriver.exe");
			 driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void register(Method method) {

		System.out.println("iam Befor Method.....");
		String testName = method.getName();
		test = report.createTest(testName);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException, IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			//test.log(Status.FAIL, "The test method Named as : " + result.getName() + " is Failed");
			// test.log(Status.FAIL, "Test failure : " + result.getThrowable());
			test.fail(MarkupHelper.createLabel(result.getName() + " :-test case failed", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "The Test Method Named as : " + result.getName() + " is Passed");
			test.log(Status.PASS, "Test success : " + result.getInstance());
			test.pass("Test is Passed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getScreenshot(driver)).build());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "The Test Method Named as : " + result.getName() + " is Skipped");
		}
		driver.quit();

	}

	// to generate random numbers
	public int randomNum() {
		Random r = new Random();
		int rnum = r.nextInt(999999);
		return rnum;

	}

	@AfterTest
	public void cleanUp() {
		report.flush();
	}
}

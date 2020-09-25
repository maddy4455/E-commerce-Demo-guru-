package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static String getScreenshot(WebDriver driver) throws InterruptedException, IOException {
		Date dt = new Date();
		String filePath = dt.toString().replace(':', '_').replace(' ', '_') + ".html";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File screenshotLocation = new File("./screenshots/" + filePath + "png");
		// FileUtils.copyFile(screenshot, screenshotLocation);

		try {
			FileUtils.copyFile(screenshot, screenshotLocation);
			System.out.println("Screenshot Captured");
		} catch (IOException e) {
			System.out.println("Capture Failed" + e.getMessage());
		}

		InputStream is = new FileInputStream(screenshotLocation);
		byte[] imageBytes = IOUtils.toByteArray(is);
		String base64 = Base64.getEncoder().encodeToString(imageBytes);
		return base64;
	}

}

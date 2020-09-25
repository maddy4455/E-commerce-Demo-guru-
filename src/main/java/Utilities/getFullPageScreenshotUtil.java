package Utilities;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class getFullPageScreenshotUtil {
	
	public static String captureFullPage(WebDriver driver, String screenshotPath) throws IOException {
		
	Screenshot	screenshot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		String dest= System.getProperty("user.dir")+"/Screenshots/"+screenshotPath+".png";
		ImageIO.write(screenshot.getImage(), "PNG", new File(dest));
		return dest;
		
	}

}

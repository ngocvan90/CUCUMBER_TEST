package commons;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends WebDriverManager {
	private GeckoDriverService  ffService;

	@Override
	protected void startService() {
		if (ffService == null) {
			try {
				ffService = new GeckoDriverService.Builder().usingAnyFreePort().build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void stopService() {
		if (ffService != null && ffService.isRunning()) {
			ffService.stop();
		}

	}

	@Override
	protected void createService() {
		driver = new FirefoxDriver();
	}

}

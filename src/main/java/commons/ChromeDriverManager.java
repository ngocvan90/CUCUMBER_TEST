package commons;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends WebDriverManager {

	private ChromeDriverService chService;

	@Override
	protected void startService() {
		if (chService == null) {
			try {
				chService = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File(".\\driver\\chromedriver.exe")).usingAnyFreePort().build();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	protected void stopService() {
		if (chService != null && chService.isRunning()) {
			chService.stop();
		}

	}

	@Override
	protected void createService() {
		DesiredCapabilities capabilites = new DesiredCapabilities().chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("start-maximized");
		capabilites.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(chService, capabilites);
	}

}

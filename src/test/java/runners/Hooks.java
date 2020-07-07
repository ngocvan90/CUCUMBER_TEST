package runners;

import org.openqa.selenium.WebDriver;

import commons.Constants;
import commons.DriverManagerFactory;
import commons.WebDriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	private static WebDriverManager driverManager;
	private static WebDriver driver;

	@Before
	public synchronized static WebDriver openBrowser() {
		String browser = System.getProperty("BROWSER");
	
		if (browser == null && driver == null) {
			browser = System.getenv("BROWSER");
			if (browser == null) {
				browser = "chrome";
			}
			driverManager = DriverManagerFactory.getManager(browser);
			driver = driverManager.getDriver();
			driver.get(Constants.TINY_URL);
		}
		return driver;
	}

	@After
	public static void closeBroser() {
		try {
			if (driver != null) {
				driverManager.quitBrowser();
				System.gc();
				if (driver.toString().toLowerCase().contains("chrome")) {
					String cmd = "taskkill /IM chromedriver.exe /F";
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				}
				if (driver.toString().toLowerCase().contains("internetexplorerdriver")) {
					String cmd = "taskkill /IM IEDriverServer.exe /F";
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

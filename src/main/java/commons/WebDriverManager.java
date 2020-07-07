package commons;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverManager {
	protected WebDriver driver;

	protected abstract void startService();

	protected abstract void stopService();

	protected abstract void createService();

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public WebDriver getDriver() {
		if (driver == null) {
			startService();
			createService();

		}
		return driver;
	}

}

package commons;

public class DriverManagerFactory {

	public static WebDriverManager getManager(String browser) {
		WebDriverManager driverManager;
		switch (browser) {
		case "chrome":
			driverManager = new ChromeDriverManager();
			break;
		case "firefox":
			driverManager = new FirefoxDriverManager();
			break;
		default:
			driverManager = new ChromeDriverManager();
			break;
		}
		return driverManager;
	}
}

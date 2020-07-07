package commons;

import org.openqa.selenium.WebDriver;

import pageObject.AddPeoplePage;
import pageObject.DashboardPage;
import pageObject.LoginPage;

public class PageFactoryManager {
	private static DashboardPage dashboardPage;
	private static AddPeoplePage addPeoplePage;
	private static LoginPage loginPage;
	
	public static LoginPage getLoginPage(WebDriver driver) {
		if (loginPage == null) {
			return new LoginPage(driver);
		}
		return loginPage;
	}
//	
	public static DashboardPage getDashboardPage(WebDriver driver) {
		if (dashboardPage == null) {
			return new DashboardPage(driver);
		}
		return dashboardPage;
	}
	
	public static AddPeoplePage getAddPeoplePage(WebDriver driver) {
		if (addPeoplePage == null) {
			return new AddPeoplePage(driver);
		}
		return addPeoplePage;
	}
	
//	public static AbstractPage getAbstractPage(WebDriver driver) {
//		if (abstractPage == null) {
//			return new AbstractPage(driver);
//		}
//		return abstractPage;
//	}
//
//	public static AbstractPageObject getAbstractPage(WebDriver driver) {
//		if (abstractPageObject == null) {
//			return new AbstractPageObject(driver);
//		}
//		return abstractPageObject;
//	}
	
}

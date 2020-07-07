package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageFactoryManager;
import cucumber.api.java.en.Given;
import pageObject.DashboardPage;
import runners.Hooks;

public class DashboardSteps {
	WebDriver driver;
	private DashboardPage dashboardPage;

	public DashboardSteps() {
		driver = Hooks.openBrowser();
		dashboardPage = PageFactoryManager.getDashboardPage(driver);
	}
	
	@Given("^User clicks on User & Settings icon in top right corner$")
	public void userClicksOnUserSettingsIconInTopRightCorner() {
		dashboardPage.clickSettingIcon();
	}
}

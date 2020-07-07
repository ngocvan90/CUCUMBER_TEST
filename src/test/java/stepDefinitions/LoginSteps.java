package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageFactoryManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.LoginPage;
import runners.Hooks;

public class LoginSteps {
	WebDriver driver;
	private LoginPage loginPage;

	public LoginSteps() {
		driver = Hooks.openBrowser();
		loginPage = PageFactoryManager.getLoginPage(driver);
	}

	@Given("^User is on Login page$")
	public void userIsOnLoginPage() {
		loginPage.clickLoginLink();
	}

	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void userEntersAnd(String userName, String pwd) {
		loginPage.inputUsername(userName);
		loginPage.inputPassword(pwd);
	}

	@When("^User click on Sign In button$")
	public void userClickOnSignInButton() {
		loginPage.clickLoginButton();
	}
	
	@Then("^User closes browser$")
    public void userCloseBrowser(){
		Hooks.closeBroser();
    }
}

package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;
import commons.Constants;

public class LoginPage extends AbstractPage {
	@FindBy(xpath = "//div[@class='login']/a")
	private WebElement eltLoginLink;

	@FindBy(css = "input#session_email")
	private WebElement _eltInputTxt;

	@FindBy(css = "input#session_password")
	private WebElement _eltPasswordTxt;

	@FindBy(xpath = "//button[text()='Sign In']")
	private WebElement eltSignInBtn;

	@FindBy(xpath = "//h4[@class='signin-title']")
	private WebElement eltTitle;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickLoginLink() {
		clickToElement(eltLoginLink);
		waitForPageLoaded(Constants.LONG_WAIT);
		waitForElementVisible(eltTitle);
	}

	public void inputUsername(String username) {
		waitForElementClickable(_eltInputTxt);
		sendkeyToElement(_eltInputTxt, username);
	}

	public void inputPassword(String password) {
		sendkeyToElement(_eltPasswordTxt, password);
	}

	public void clickLoginButton() {
		clickToElement(eltSignInBtn);
		waitForPageLoaded(Constants.LONG_WAIT);
	}
}

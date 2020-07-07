package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;
import commons.Constants;

public class AddPeoplePage extends AbstractPage {

	private static final String TITLE_TEXT = "Preparing for Launch";
	private static final String LASTNAME_LOCATOR = "//input[@value='%s']/ancestor::td//input[@field='lastName']";
	private static final String EMAIL_LOCATOR = "//input[@value='%s']/ancestor::tr//input[@field='email']";
	private static final String MANAGER_LOCATOR = "//input[@value='%s']/ancestor::tr/td[@data-cucumber='manager']//i[contains(@class,'DropdownIndicator')]";

	@FindBy(xpath = "//span[contains(@class,'js-cucumber-manage-people')]")
	private WebElement _eltManagePeopleTab;

	@FindBy(xpath = "//div[contains(@class,'onboarding-invite-banner')]")
	private WebElement _eltInviteBanner;

	@FindBy(xpath = "//a[@aria-label='Users and Settings']")
	private WebElement _eltUserSettingIcon;

	@FindBy(xpath = "//a[@href='/invite']")
	private WebElement _eltAddPeopleMenu;

	@FindBy(xpath = "//div[contains(@class,'cucumber-send-invite-button')]")
	private WebElement _eltAddPeopleButton;

	@FindBy(xpath = "//input[@field='firstName'][string-length(@value)=0]")
	private WebElement _eltFirstNameTxt;

	@FindBy(xpath = "//div[contains(@class,'items-center green')]")
	private WebElement _eltCongratulation;

	public AddPeoplePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickOnAddPeopleMenu() {
		waitForElementClickable(_eltAddPeopleMenu);
		clickToElement(_eltAddPeopleMenu);
		waitForElementVisible(_eltInviteBanner);
	}

	public void clickOnAddPeopleButton() {
		clickToElement(_eltAddPeopleButton);
		waitForPageLoaded(Constants.LONG_WAIT);
	}

	public void inputFirstName(String firstName) {
		sendkeyToElement(_eltFirstNameTxt, firstName);
	}

	public void inputLastNameForAPerson(String firstName, String lastName) {
		String locLastName = String.format(LASTNAME_LOCATOR, firstName);
		sendkeyToElementBy(locLastName, lastName);
	}

	public void inputEmailForAPerson(String firstName, String email) {
		String locLastName = String.format(EMAIL_LOCATOR, firstName);
		sendkeyToElementBy(locLastName, email);
	}

	public String getSuccessMessage() {
		waitForElementVisible(_eltCongratulation);
		return getTextElement(_eltCongratulation);
	}

}

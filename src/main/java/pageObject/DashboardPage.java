package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;
import commons.Constants;

public class DashboardPage extends AbstractPage {

	@FindBy(xpath = "//div[contains(@class,'prelaunch-overview__prepareForLaunchCard')]//div[contains(@class,'titleText')]")
	private WebElement _eltTitle;

	@FindBy(xpath = "//i[@class='icon-people-setting']")
	private WebElement _eltSettingIcon;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickSettingIcon(){
		waitForElementClickable(_eltSettingIcon);
		clickToElement(_eltSettingIcon);
		waitForPageLoaded(Constants.LONG_WAIT);
	}
}

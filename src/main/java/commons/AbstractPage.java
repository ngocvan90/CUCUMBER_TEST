package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	public final Log log;
	private WebDriver driver;

	public AbstractPage(WebDriver _driver) {
		this.driver = _driver;
		log = LogFactory.getLog(getClass());
	}

	public void waitForPageLoaded(int timeOut) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(pageLoadCondition);
	}

	// Web Element
	public void clickToElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebElement elt) {
//		try {
			elt.click();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void clickToElement(String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendkeyToElementBy(String locator, String text) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled() && !(getAttributeValue(locator, "type").equalsIgnoreCase("date"))) {
			element.clear();
			element.sendKeys(text);
		} else
			element.sendKeys(text);
	}

	public void sendkeyToElement(WebElement elt, String value) {
		elt.clear();
		elt.sendKeys(value);
	}

	public void sendkeyToElement(String locator, String value, String text) {
		locator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled() && !(getAttributeValue(locator, "type").equalsIgnoreCase("date"))) {
			element.clear();
			element.sendKeys(text);
		} else
			element.sendKeys(text);
	}

	public void selectItemInDropdown(String locator, String item) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(item);
	}

	public String getFirstItemSelect(String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public String getAttributeValue(String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getAttributeValue(String locator, String value, String attributeName) {
		String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebElement elt) {
		return elt.getText().trim();
	}

	public String getTextElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText().trim();
	}

	public int getSize(WebDriver driver, String locator) {
		List<WebElement> list = driver.findElements(By.xpath(locator));
		return list.size();
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplay(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplay(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelect(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendkeyToAlert(WebDriver driver, String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public void switchToWindowByID(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void doubleClick(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void hoverMouse(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClick(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).release().perform();
	}

	public void keyUp(WebDriver driver, String locator, Keys modifier_key) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyUp(element, modifier_key).perform();
	}

	public void dynamicKeyPress(WebDriver driver, String locator, Keys modifier_key) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, modifier_key).perform();
	}

	public void uploadBySenkey(WebDriver driver, String locator, String filePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath);
	}

	public Object executeForBrowserElement(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeForWebElementByClick(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebDriver driver, WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToElement(WebDriver driver, String locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath(locator));
			return js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementVisible(WebElement elt) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.visibilityOf(elt));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementNotVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForElementClickableBy(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForElementClickable(WebElement elt) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(elt));
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MEDIUM_WAIT);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean WaitForObject(WebElement elt) {
		boolean returnValue = true;
		WebDriverWait waitSmall = new WebDriverWait(driver, Constants.MEDIUM_WAIT);

		try {
			waitSmall.until(ExpectedConditions.visibilityOf(elt));
		} catch (Exception e) {
			returnValue = false;
			e.printStackTrace();
		}

		try {
			waitSmall.until(ExpectedConditions.elementToBeClickable(elt));
		} catch (Exception e) {
			returnValue = false;
			e.printStackTrace();
		}
		return returnValue;
	}


	public int randomMailNumber() {
		Random rad = new Random();
		int number = rad.nextInt(5000) + 1;
		return number;
	}

}

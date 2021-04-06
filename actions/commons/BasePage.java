package commons;
import java.util.List;
import java.util.Set;
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

import pageUIs.DashboardPageUI;

public class BasePage {
	public static BasePage getBasePage() {
		return new BasePage();
	}
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void getTextAlert(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void senKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
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

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public By ByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public String getDynamicLocator(String locator, String... values) {
		locator =  String.format(locator, (Object[])values);
		return locator;			
	}
	
	public WebElement findWebElement(WebDriver driver, String locator) {
		return driver.findElement(ByXpath(locator));
	}
	
	public List<WebElement> findListWebElement(WebDriver driver, String locator) {
		return driver.findElements(ByXpath(locator));
	}
	
	public List<WebElement> findListWebElement(WebDriver driver, String locator, String...values) {
		return driver.findElements(ByXpath(getDynamicLocator(locator,values)));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		findWebElement(driver,locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String...values) {
		findWebElement(driver,getDynamicLocator(locator,values)).click();
	}
	
	public void clickToElement(WebElement element) {
		element.click();
	}
	
	public void senKeyToElement(WebDriver driver, String locator, String value) {
		findWebElement(driver,locator).sendKeys(value);
	}
	
	public void senKeyToElement(WebDriver driver, String locator, String value, String... values) {
		findWebElement(driver,getDynamicLocator(locator,values)).sendKeys(value);
	}
	
	public void selectItemInDropDown(WebDriver driver, String locator, String value) {
		Select element = new Select(findWebElement(driver,locator));
		element.selectByVisibleText(value);
	}
	
	public void getSelectedItemInDropDown(WebDriver driver, String locator) {
		Select element = new Select(findWebElement(driver,locator));
		element.getFirstSelectedOption().getText();
	}
	
	public boolean isDropDownMultiple(WebDriver driver, String locator) {
		Select element = new Select(findWebElement(driver,locator));
		return element.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		clickToElement(driver,parentLocator);
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, 20);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ByXpath(childItemLocator)));
		List<WebElement> allItems = findListWebElement(driver, childItemLocator);
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				clickToElement(item);
				sleepInSecond(1);
				break;
			}
		}
	}
  
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getAttributeElement(WebDriver driver, String locator, String attribute) {
		return findWebElement(driver, locator).getAttribute(attribute);
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		return findWebElement(driver, locator).getText();
	}
	
	public String getTextElement(WebDriver driver, String locator, String...values) {
		return findWebElement(driver, getDynamicLocator(locator,values)).getText().trim();
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return findListWebElement(driver, locator).size();
	}
	
	public void checkTheRadioOrCheckbox(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		if(!element.isSelected()) {
			element.click();
		};
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		if(element.isSelected()) {
			element.click();
		};
	}
	
	public boolean isElementDisplay(WebDriver driver, String locator) {
		return findWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return findWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findWebElement(driver, locator).isEnabled();
	}
	
	public void switchToIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick().perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(findWebElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(findWebElement(driver, locator)).perform();
	}
	
	public void dragDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		Actions action = new Actions(driver);
		action.dragAndDrop(findWebElement(driver, locatorSource), findWebElement(driver, locatorTarget)).perform();
	}
	
	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(findWebElement(driver, locator), key).perform();
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = findWebElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		WebElement element = findWebElement(driver, locator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete&&typeof arguments[0].naturalWidth!=\"undefined\"&& arguments[0].naturalWidth>0", element);
		if(status) {
			return true;
		}else {
			return false;
		}
		
	} 
	
	public void waitForElementVisible(WebDriver driver, String locator) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ByXpath(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForListElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByXpath(locator)));
	}
	
	public void waitForListElementVisible(WebDriver driver, String locator, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(ByXpath(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ByXpath(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator,String...values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(ByXpath(getDynamicLocator(locator,values))));
	}
	
	public void sleepInSecond(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openLinkByPageName(WebDriver driver,String pageName) {
		waitForElementVisible(driver, DashboardPageUI.MENU_LNK,pageName);
		clickToElement(driver,DashboardPageUI.MENU_LNK,pageName);
	}
	
	private long longTimeout = 30;
}

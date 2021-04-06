package pageObjects;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LogInPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToEmailTextBox(String value) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LogInPageUI.TXT_USERNAMEOREMAIL);
		senKeyToElement(driver, LogInPageUI.TXT_USERNAMEOREMAIL, value);
	}

	public void inputToPasswordTextBox(String value) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LogInPageUI.TXT_PASSWORD);
		senKeyToElement(driver, LogInPageUI.TXT_PASSWORD, value);
	}
	
	public void clickToContinueButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LogInPageUI.BTN_CONTINUE);
		clickToElement(driver, LogInPageUI.BTN_CONTINUE);
	}

	public String getAttributeErrorMessage(String attribute) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LogInPageUI.LBL_ERROR_MESSAGE);
		return getAttributeElement(driver, LogInPageUI.LBL_ERROR_MESSAGE, attribute);
	}


	public void clickToLogInButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LogInPageUI.BTN_LOGIN);
		clickToElement(driver, LogInPageUI.BTN_LOGIN);
	}
	
	public DashboardPageObject logInToSystem(String userName, String password) {
		inputToEmailTextBox(userName);
		clickToContinueButton();
		inputToPasswordTextBox(password);
		clickToLogInButton();
		return PageGeneratorManager.getDashboardPage(driver);
	}

}

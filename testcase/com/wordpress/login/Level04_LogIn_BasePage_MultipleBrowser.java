package com.wordpress.login;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.DashboardPageObject;
import pageObjects.LoginPageObject;

public class Level04_LogIn_BasePage_MultipleBrowser extends BaseTest{
	WebDriver driver;
	WebDriverWait explicitwait;
	LoginPageObject logInPage;
	DashboardPageObject dashboardPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
	driver = getBrowserName(browserName);
	}

	@BeforeMethod
	public void beforeMethod() {
	driver.get("https://automationfc.wordpress.com/wp-admin");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
	explicitwait = new WebDriverWait(driver,30);
	logInPage = new LoginPageObject(driver);
	}
	
	@Test
	public void TC01_emptyEmail(){
	logInPage.clickToContinueButton();
	Assert.assertEquals("Please enter a username or email address.", logInPage.getAttributeErrorMessage("innerText"));
	}

	@Test
	public void TC02_invalidEmail(){
	logInPage.inputToEmailTextBox("atb@!@#$");
	logInPage.clickToContinueButton();
	Assert.assertEquals("User does not exist. Would you like to create a new account?", logInPage.getAttributeErrorMessage( "innerText"));
	}
	
	@Test
	public void TC03_emptyPassword(){
	logInPage.inputToEmailTextBox("automationeditor");
	logInPage.clickToContinueButton();
	logInPage.sleepInSecond(2);
	logInPage.clickToContinueButton();
	Assert.assertEquals("Don't forget to enter your password.", logInPage.getAttributeErrorMessage( "innerText"));
	}
	
	@Test
	public void TC04_invalidPassword(){
	logInPage.inputToEmailTextBox("automationeditor");
	logInPage.clickToContinueButton();
	logInPage.inputToPasswordTextBox("automationeditor");
	logInPage.clickToContinueButton();
	Assert.assertEquals("Oops, that's not the right password. Please try again!", logInPage.getAttributeErrorMessage( "innerText"));
	}
	
	@Test
	public void TC05_validEmailPassword(){
	logInPage.inputToEmailTextBox("automationeditor");
	logInPage.clickToContinueButton();
	logInPage.inputToPasswordTextBox("automationfc");
	logInPage.clickToLogInButton();
	dashboardPage = new DashboardPageObject(driver);
	Assert.assertEquals("Dashboard", dashboardPage.getAttributeDashboard("innerText"));
	}
	
	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}

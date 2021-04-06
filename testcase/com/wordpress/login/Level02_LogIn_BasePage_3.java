package com.wordpress.login;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level02_LogIn_BasePage_3 extends BasePage {
	WebDriver driver;
	WebDriverWait explicitwait;
	String projectFolder = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
	openUrl(driver, "https://automationfc.wordpress.com/wp-admin");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
	explicitwait = new WebDriverWait(driver,30);
	}
	
	@Test
	public void TC01_emptyEmail(){
	clickToElement(driver, "//*[@type='submit']");
	waitForElementVisible(driver,"//*[@class='form-input-validation is-error']");
	Assert.assertEquals("Please enter a username or email address.", getAttributeElement(driver, "//*[@class='form-input-validation is-error']", "innerText"));
	}

	@Test
	public void TC02_invalidEmail(){
	senKeyToElement(driver, "//*[@id='usernameOrEmail']", "atb@!@#$");
	clickToElement(driver,"//*[@type='submit']");
	waitForElementVisible(driver,"//*[@class='form-input-validation is-error']");
	Assert.assertEquals("User does not exist. Would you like to create a new account?", getAttributeElement(driver, "//*[@class='form-input-validation is-error']", "innerText"));
	}
	
	@Test
	public void TC03_emptyPassword(){
	senKeyToElement(driver, "//*[@id='usernameOrEmail']", "automationeditor");
	clickToElement(driver, "//*[@type='submit']");
	sleepInSecond(2);
	clickToElement(driver, "//*[@class='login__form-action']/*");
	waitForElementVisible(driver,"//*[@class='form-input-validation is-error']");
	Assert.assertEquals("Don't forget to enter your password.", getAttributeElement(driver, "//*[@class='form-input-validation is-error']", "innerText"));
	}
	
	@Test
	public void TC04_invalidPassword(){
	senKeyToElement(driver, "//*[@id='usernameOrEmail']", "automationeditor");
	clickToElement(driver, "//*[@type='submit']");
	waitForElementVisible(driver,"//*[@id='password']");
	senKeyToElement(driver, "//*[@id='password']", "automationeditor");
	waitForElementVisible(driver,"//*[@class='login__form-action']/*");
	clickToElement(driver, "//*[@class='login__form-action']/*");
	waitForElementVisible(driver,"//*[@class='form-input-validation is-error']");
	Assert.assertEquals("Oops, that's not the right password. Please try again!", getAttributeElement(driver, "//*[@class='form-input-validation is-error']", "innerText"));
	}
	
	@Test
	public void TC05_validEmailPassword(){
	senKeyToElement(driver, "//*[@id='usernameOrEmail']", "automationeditor");
	clickToElement(driver, "//*[@type='submit']");
	waitForElementVisible(driver,"//*[@id='password']");
	senKeyToElement(driver, "//*[@id='password']", "automationfc");
	clickToElement(driver, "//*[@class='login__form-action']/*");
	waitForElementVisible(driver,"//h1[text()='Dashboard']");
	Assert.assertEquals("Dashboard", getAttributeElement(driver, "//h1[text()='Dashboard']", "innerText"));
	}
	
	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

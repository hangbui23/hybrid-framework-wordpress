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
import pageObjects.MyBlogsPageObject;
import pageObjects.PageGeneratorManager;

public class Level07_LogIn_BasePage_WebDataTable extends BaseTest{
	WebDriver driver;
	WebDriverWait explicitwait;
	LoginPageObject logInPage;
	DashboardPageObject dashboardPage;
	MyBlogsPageObject myBlogsPage;
	
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
	logInPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	
	@Test
	public void TC01_CheckInfoDisplays(){
	dashboardPage =	logInPage.logInToSystem("automationeditor","automationfc");
	Assert.assertEquals("Dashboard", dashboardPage.getAttributeDashboard("innerText"));
	dashboardPage.openLinkByPageName(driver, "My Blogs");
	myBlogsPage = PageGeneratorManager.getMyBlogsPage(driver);
	Assert.assertEquals("My Blogs",myBlogsPage.getAttributeMyBlogs("innerText"));
	Assert.assertTrue(myBlogsPage.isInfoDisplays("Role", "2", "Administrator"));
	Assert.assertTrue(myBlogsPage.isInfoDisplays("Address", "1", "automationfc.wordpress.com"));
	}
	
	
	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}

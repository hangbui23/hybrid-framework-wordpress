package com.wordpress.login;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Level01_LogIn_Repeat_Your_Self {
	WebDriver driver;
	WebDriverWait explicitwait;
	String projectFolder = System.getProperty("user.dir");
	By emailAddress = By.id("usernameOrEmail");
	By continueButton = By.xpath("//*[@type='submit']");
	By errorMessage = By.xpath("//*[@class='form-input-validation is-error']/*");
	By logInButton = By.xpath("//*[@class='login__form-action']/*");
	By password = By.id("password");
	By dashBoard = By.xpath("//h1[text()='Dashboard']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
	driver.get("https://automationfc.wordpress.com/wp-admin");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
	explicitwait = new WebDriverWait(driver,30);
	}
	
	@Test
	public void TC01_emptyEmail(){
	driver.findElement(continueButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	Assert.assertEquals("Please enter a username or email address.", driver.findElement(errorMessage).getText());
	}

	@Test
	public void TC02_invalidEmail(){
	driver.findElement(emailAddress).sendKeys("atb@!@#$");
	driver.findElement(continueButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	Assert.assertEquals("User does not exist. Would you like to create a new account?", driver.findElement(errorMessage).getAttribute("innerText"));
	}
	
	@Test
	public void TC03_emptyPassword(){
	driver.findElement(emailAddress).sendKeys("automationeditor");
	driver.findElement(continueButton).click();
	sleepInSecond(2);
	driver.findElement(logInButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	Assert.assertEquals("Don't forget to enter your password.", driver.findElement(errorMessage).getAttribute("innerText"));
	}
	
	@Test
	public void TC04_invalidPassword(){
	driver.findElement(emailAddress).sendKeys("automationeditor");
	driver.findElement(continueButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(password));
	driver.findElement(password).sendKeys("automationeditor");
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(logInButton));
	driver.findElement(logInButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	Assert.assertEquals("Oops, that's not the right password. Please try again!", driver.findElement(errorMessage).getAttribute("innerText"));
	}
	
	@Test
	public void TC05_validEmailPassword(){
	driver.findElement(emailAddress).sendKeys("automationeditor");
	driver.findElement(continueButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(password));
	driver.findElement(password).sendKeys("automationfc");
	driver.findElement(logInButton).click();
	explicitwait.until(ExpectedConditions.visibilityOfElementLocated(dashBoard));
	Assert.assertEquals("Dashboard", driver.findElement(dashBoard).getAttribute("innerText"));
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

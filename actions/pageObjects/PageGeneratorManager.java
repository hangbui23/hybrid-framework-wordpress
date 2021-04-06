package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static MyBlogsPageObject getMyBlogsPage(WebDriver driver) {
		return new MyBlogsPageObject(driver);
	}
}

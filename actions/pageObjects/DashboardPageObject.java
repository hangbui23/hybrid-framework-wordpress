package pageObjects;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends BasePage {
	WebDriver driver;
	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getAttributeDashboard(String attribute) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DashboardPageUI.LBL_DASHBOARD_HEADER);
		return getAttributeElement(driver, DashboardPageUI.LBL_DASHBOARD_HEADER, attribute);
	}

}

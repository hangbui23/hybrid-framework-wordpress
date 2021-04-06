package pageObjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import commons.BasePage;
import pageUIs.MyBlogsPageUI;

public class MyBlogsPageObject extends BasePage {
	WebDriver driver;
	public MyBlogsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getAttributeMyBlogs(String attribute) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, MyBlogsPageUI.BLOG_HEADER);
		return getAttributeElement(driver, MyBlogsPageUI.BLOG_HEADER, attribute);
	}
	
	public boolean isInfoDisplays (String columnName, String rowIndex,String expectedValue) {
		List<WebElement> ele = findListWebElement(driver, MyBlogsPageUI.INFO_POSITION,columnName);
		int colIndex = ele.size()+1;
		waitForElementVisible(driver, MyBlogsPageUI.INFO_DETAIL, rowIndex, String.valueOf(colIndex));
		String infoValue = getTextElement(driver, MyBlogsPageUI.INFO_DETAIL, rowIndex, String.valueOf(colIndex));
		return infoValue.equals(expectedValue);
	}
}

package commons;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectFolder = System.getProperty("user.dir");
	private String osName = System.getProperty("os.name");
	
	protected WebDriver getBrowserName(String browser) {
		Browser browserName = Browser.valueOf(browser.toUpperCase());
		if(browserName==Browser.CHROME_UI) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		}else if(browserName==Browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		else if(browserName==Browser.FIREFOX_UI){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName==Browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("headless");
			driver = new FirefoxDriver(options);
		}
		else if(browserName==Browser.EDGE){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
	private String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	private boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	private boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	private boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") > 0);
	}

	private boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
	
	private void setLocationPropertyDriver() {
		if(isWindows()) {
			System.setProperty("webdriver.chrome.driver", projectFolder + getDirectorySlash("browserDriver") + "chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectFolder + getDirectorySlash("browserDriver") + "geckodriver.exe");
			System.setProperty("webdriver.edge.driver", projectFolder + getDirectorySlash("browserDriver") + "msedgedriver.exe");
		}else if(isMac()) {
			System.setProperty("webdriver.chrome.driver", projectFolder + getDirectorySlash("browserDriver") + "chromedriver_mac");
			System.setProperty("webdriver.gecko.driver", projectFolder + getDirectorySlash("browserDriver") + "geckodriver_mac");
			System.setProperty("webdriver.edge.driver", projectFolder + getDirectorySlash("browserDriver") + "msedgedriver_mac");
		}
		else {
			System.setProperty("webdriver.chrome.driver", projectFolder + getDirectorySlash("browserDriver") + "chromedriver_linux");
			System.setProperty("webdriver.gecko.driver", projectFolder + getDirectorySlash("browserDriver") + "geckodriver_linux");

		}
	}
}

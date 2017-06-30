package general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {
	public static WebDriver getDriver(String browser){
		WebDriver driver;
		switch (browser.toLowerCase()) {
        	case "firefox":
        		driver = new FirefoxDriver();
        		break;
        	case "ie":
        		driver = new InternetExplorerDriver();
        		break;
        	case "safari":
        		driver = new SafariDriver();
        		break;
        	case "chrome":
        		driver = new ChromeDriver();
        		break;
        	default:
        		driver = new FirefoxDriver();
		}

		return driver;
	}
}

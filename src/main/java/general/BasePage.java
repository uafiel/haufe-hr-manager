package general;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {
	public static WebDriverResource webDriver = WebDriverResource.getInstance();


	static String parentWindowHandle;
	static String localWindowHandle;


	public static <C extends BasePage> C getInstance(Class<C> clazz){
		C page = PageFactory.initElements(webDriver.driver, clazz);
		webDriver.waitDriver.until(page.ready());
		return page;
	}


	public static void quit(){
		webDriver.driver.quit();
	}
	
	public ExpectedCondition<?> ready(){
		parentWindowHandle = webDriver.driver.getWindowHandle();
		List<String> handlesList = new ArrayList<>(webDriver.driver.getWindowHandles());
		localWindowHandle = handlesList.get(handlesList.size() - 1);
		webDriver.driver.switchTo().window(localWindowHandle);

		return null;
	}
}

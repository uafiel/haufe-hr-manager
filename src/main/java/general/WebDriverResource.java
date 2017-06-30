package general;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;

public class WebDriverResource {

	private static WebDriverResource instance = null;
	public WebDriver driver;
	public WebDriverWait waitDriver;

	private WebDriverResource() {
		String browserName= "";
        String baseURL = "";
        int waitTimeOut = 0;
		try
		{
			JSONParser parser = new JSONParser();
			System.out.println();
			JSONObject jsonObject = (JSONObject)parser.parse(new FileReader("src/resources/config.json"));
            JSONObject pageInfo = (JSONObject)jsonObject.get("pageInfo");
            browserName = pageInfo.get("browserName").toString();
            baseURL = pageInfo.get("baseURL").toString();
            waitTimeOut = Integer.parseInt(pageInfo.get("waitTimeOut").toString());
		}
		catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

		driver = BrowserFactory.getDriver(browserName);
		waitDriver = new WebDriverWait(driver, waitTimeOut);
		driver.get(baseURL);
	}


	public static WebDriverResource getInstance() {
		if(instance == null){
			System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver.exe");
			instance = new WebDriverResource();
		}
		return instance;
	}

}

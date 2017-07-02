package general;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoadingPage;
import pages.LoginPage;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public Logger log = LogManager.getLogger(BaseTest.class.getName());
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public LoadingPage loadDirectLink(String URL) {
        driver.get(URL);
        this.log.info(URL + "loaded.");
        return new LoadingPage(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(Method method) {
        String testName = method.getName();
        log.info("Start: " + testName);
    }

    @BeforeClass
    public static void setUp() {
        String browserName = "";
        String baseURL = "";
        int waitTimeOut = 0;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/config.json"));
            JSONObject pageInfo = (JSONObject) jsonObject.get("pageInfo");
            browserName = pageInfo.get("browserName").toString();
            baseURL = pageInfo.get("baseURL").toString();
            waitTimeOut = Integer.parseInt(pageInfo.get("waitTimeOut").toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        driver = BrowserFactory.getDriver(browserName);
        wait = new WebDriverWait(driver, waitTimeOut);
        driver.get(baseURL);

    }

    @AfterClass
    public void quitDriver(){
        driver.quit();
    }

}



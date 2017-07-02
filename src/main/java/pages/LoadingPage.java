package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class LoadingPage extends BasePage {

    @FindBy(xpath = "//a[text()='here']")
    @CacheLookup
    private WebElement hereLink;


    public LoadingPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickHere() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        hereLink.click();
        this.log.info("Here link clicked.");
        return new LoginPage(getDriver());
    }

    public LoginPage waitLoadingToPass () {
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new LoginPage(getDriver());
    }
}

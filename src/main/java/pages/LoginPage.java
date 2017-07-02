package pages;

import general.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage{

	@FindBy (id = "input_UserName")
	@CacheLookup
	private WebElement usernameInput;

	@FindBy (id = "input_password")
	@CacheLookup
	private WebElement passwordInput;

    @FindBy (id = "input_passwor2d")
    @CacheLookup
    private WebElement wrong;
	@FindBy (xpath = "//button[contains(@name,'Login')]")
	@CacheLookup
	private WebElement loginBtn;

	public LoginPage (WebDriver driver) {
	   super(driver);
    }



	public LoginPage enterUsername(String username) throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		usernameInput.clear();
		usernameInput.sendKeys(username);
		this.log.info("Entered username: " + username);
		return this;
	}

	public LoginPage enterPassword(String password) throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		this.log.info("Entered password: " + password);
		return this;
	}

	public ApplicationPage clickLoginBtnCorrectCredentials() {
		loginBtn.click();
		this.log.info("Login button clicked.");
        return new ApplicationPage(getDriver());
	}


}

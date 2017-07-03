import general.BaseTest;
import general.EvaluationDTO;
import general.ExcelUtils;
import general.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.LoadingPage;
import pages.LoginPage;

import java.io.IOException;


public class AddNewEvaluation extends BaseTest {

    @Test(dataProvider="TestData")
    public void start(String testUrl, String username, String password, String applicant, String starsNumber, String nextStep, String text ) throws InterruptedException, IOException {

        LoadingPage load = loadDirectLink(testUrl);
        LoginPage login = load.waitLoadingToPass();
        login.enterUsername(username);
        login.enterPassword(password);
        ApplicationPage applicationPage = login.clickLoginBtnCorrectCredentials();
        Assert.assertEquals(applicationPage.getApplicantName(),applicant);
        applicationPage.setRate(starsNumber);
        applicationPage.chooseNextStep(nextStep);
        applicationPage.addComment(text);
        applicationPage.clickSubmitBtn();
        EvaluationDTO ev = applicationPage.createEvaluationInstance();
        Assert.assertEquals(ev.getNextStep(),nextStep);
        Assert.assertEquals(ev.getRating(),starsNumber);
        Assert.assertEquals(ev.getComment(),text);

    }

    @DataProvider
    public Object[][] TestData() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray("src/main/resources/TestData.xlsx","Sheet1");
        return (testObjArray);

    }
}

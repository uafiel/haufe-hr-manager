package pages;

import general.BasePage;
import general.EvaluationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class ApplicationPage extends BasePage {

    @FindBy(id = "evaluationStatement")
    @CacheLookup
    private WebElement commentTextBox;

    @FindBy(xpath = "//*[@title=\"Top candidate\"]")
    @CacheLookup
    private WebElement topApplicant;

    @FindBy(xpath = "//*[@title=\"A applicant\"]")
    @CacheLookup
    private WebElement aApplicant;

    @FindBy(xpath = "//*[@title=\"B applicant\"]")
    @CacheLookup
    private WebElement bApplicant;

    @FindBy(xpath = "//*[@title=\"C applicant\"]")
    @CacheLookup
    private WebElement cApplicant;

    @FindBy(xpath = "//*[@title=\"E applicant\"]")
    @CacheLookup
    private WebElement eApplicant;

    @FindBy(xpath = "//h3[contains(@class,'u-inline ng-binding')]")
    @CacheLookup
    private WebElement applicantNameText;

    @FindBy(id = "evaluationStatement")
    @CacheLookup
    private WebElement evaluationStatement;

    @FindBy(xpath = "//a[@class=\"dropdown-toggle c-evaluation__drop-down__btn\"]")
    @CacheLookup
    private WebElement nextStepDropDown;

    @FindBy(xpath = "//a[@class=\"c-evaluation__drop-down__list-item ng-binding\"]")
    @CacheLookup
    private List<WebElement> nextSteps;

    @FindBy(xpath = "//button[@ng-click=\"$ctrl.createApplicationEvaluation($ctrl.newEvaluation)\"]")
    @CacheLookup
    private WebElement submitBtn;

    @FindBy(xpath = "(//span[@class=\"tooltip-center\"])[1]")
    @CacheLookup
    private WebElement lastEvaluationRating;

    @FindBy(xpath = "(//div[@class=\"ng-binding\"])[1]")
    @CacheLookup
    private WebElement lastEvaluationComment;


    @FindBy(xpath = "(//div[@class=\"u-text-darker ng-binding\"])[1]")
    @CacheLookup
    private WebElement lastEvaluationNextStep;

    @FindBy(xpath = "(//div[@class=\"c-chat-message__meta c-chat-message__meta--date ng-binding\"])[1]")
    @CacheLookup
    private WebElement lastEvaluationDate;


    public ApplicationPage(WebDriver driver) {
        super(driver);
    }

    public String getApplicantName() throws InterruptedException {
        Thread.sleep(2000);
        String applicantName = applicantNameText.getText();
        this.log.info("Current applicant names are: " + applicantName);
        return applicantName;
    }

    public ApplicationPage setRate(String stars) {
        int i = Integer.parseInt(stars);
        switch (i) {
            case 1:
                eApplicant.click();
                break;
            case 2:
                cApplicant.click();
                break;
            case 3:
                bApplicant.click();
                break;
            case 4:
                aApplicant.click();
            case 5:
                topApplicant.click();
        }
        this.log.info("Rated with: " + stars + " stars.");
        return new ApplicationPage(getDriver());
    }

    public ApplicationPage addComment(String text) {
        evaluationStatement.sendKeys(text);
        this.log.info("Comment added.");
        return new ApplicationPage(getDriver());
    }

    public ApplicationPage chooseNextStep(String nextStep) throws IOException {
        nextStepDropDown.click();
        Boolean nextStepExist = false;
        for (int i = 0; i < nextSteps.size(); i++) {
            if (nextStep.equals(nextSteps.get(i).getText())) {
                this.log.info(nextSteps.get(i).getText() + "will be selected.");
                nextSteps.get(i).click();
                nextStepExist = true;
                break;
            }
        }
        if (!nextStepExist) {
            IOException e = new IOException();
            this.log.info("Expected next step was not found!");
            throw e;
        }
        return new ApplicationPage(getDriver());
    }

    public void clickSubmitBtn() {
        submitBtn.click();
        this.log.info("Submit button clicked.");
    }

    public String getLastEvaluationRating() {
        String rating = lastEvaluationRating.getAttribute("uib-tooltip");
        String stars = "";
        switch (rating) {
            case "E applicant":
                stars = "1";
                break;
            case "C applicant":
                stars = "2";
                break;
            case "B applicant":
                stars = "3";
                break;
            case "A applicant":
                stars = "4";
                break;
            case "Top applicant":
                stars = "5";
                break;
        }
        this.log.info("User is rated with " + stars + " stars.");
        return stars;
    }

    public String getLastEvaluationComment() {
        this.log.info("Last comment is: " + lastEvaluationComment.getText());
        return lastEvaluationComment.getText();

    }

    public String getLastEvaluationNextStep() {
        this.log.info("Last next step is: " + lastEvaluationNextStep.getText());
        return lastEvaluationNextStep.getText();
    }

    public String getLastEvaluationDate() {
        return lastEvaluationDate.getText();
    }

    public EvaluationDTO createEvaluationInstance() {
        EvaluationDTO ev = new EvaluationDTO(getLastEvaluationComment(), getLastEvaluationDate());
        ev.setNextStep(getLastEvaluationNextStep());
        ev.setRating(getLastEvaluationRating());
        return ev;
    }
}

package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.yaml.snakeyaml.scanner.Constant;
import util.ConfigReader;
import util.ElementHelper;
import util.LoginHelper;

import java.time.Duration;

public class PasswordRetrievalPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(id="tci-number-fgt")
    WebElement tcIDInput;
    @FindBy(id="dd")
    WebElement birthdateInput;
    @FindBy(id="emailOrPhone-forgot")
    WebElement emailInput;
    @FindBy(id="frg-button")
    WebElement continueButton;
    @FindBy(id="opt")
    WebElement otpInput;
    @FindBy(id="opt-button")
    WebElement submitOtpButton;

    public PasswordRetrievalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    private void enterTcId(String tcID) {
        elementHelper.checkVisible(tcIDInput);
        tcIDInput.sendKeys(LoginHelper.getUserName(tcID));
    }
    private void enterBirthdate(String birthdate) {
        elementHelper.checkVisible(birthdateInput);
        birthdateInput.sendKeys(birthdate);
        birthdateInput.sendKeys(Keys.TAB);
    }
    private void enterEmail(String email) {
        elementHelper.checkVisible(emailInput);
        emailInput.sendKeys(LoginHelper.getUserName(email));
    }
    private void enterOtp() {
        elementHelper.checkClickable(otpInput);
        otpInput.sendKeys( ConfigReader.get("otp.number"));
    }

    private void clickContinueButton() {
        elementHelper.checkClickable(continueButton);
        elementHelper.click(continueButton);
    }

    private void submitOtp() {
        elementHelper.click(submitOtpButton);
    }

    public void verifyPasswordRetrievalFormNotSubmittable(String tcID, String birthdate, String email) {
        enterTcId(tcID);
        enterBirthdate(birthdate);
        enterEmail(email);
        Assert.assertFalse(continueButton.isEnabled(), "Continue button should be disabled when required fields are missing.");
    }

    public void fillPasswordRetrievalForm(String tcId, String birthDate, String phoneNumber) {
        enterTcId(tcId);
        enterBirthdate(birthDate);
        enterEmail(phoneNumber);
        clickContinueButton();
    }

    public void enterAndSubmitOtp() {
     enterOtp();
     submitOtp();
    }
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Constants;
import util.DataProcess;
import util.ElementHelper;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class RegisterPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    // Locator'lar
    @FindBy(xpath = "//button[@class='btn-default btn-bg-secondary signupcta loginboxsignup']")
    WebElement signUpButton;
    @FindBy(xpath = "//input[@id='name']")
    WebElement firstNameField;
    @FindBy(xpath = "//input[@id='surname']")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@id='birthDate']")
    WebElement birthDateField;
    @FindBy(id = "emailRgn")
    WebElement emailField;
    @FindBy(id = "rgn-mobileNumber")
    WebElement phoneField;
    @FindBy(id = "tcidnumber")
    WebElement tcNumberField;
    @FindBy(id = "rgn-password")
    WebElement passwordField;
    @FindBy(id = "bonusCode")
    WebElement promoCodeField;
    @FindBy(id = "firstCheckboxYesLabel")
    WebElement commercialMessagesYesRadio;
    @FindBy(id = "gdprYesLabel")
    By conditionsAcceptanceYesRadio;
    @FindBy(xpath = "//button[@class='account-register']")
    WebElement submitButton;
    @FindBy(id ="register-otp-input")
    WebElement otpNumberField;
    @FindBy(id ="otp-button")
    WebElement otpNumber;
    @FindBy(id ="confirmbutton")
    WebElement playNowButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }
    public void clickRegisterButton() {
        elementHelper.click(signUpButton);
    }
    public void fillRegistrationForm() {
        String firstName = DataProcess.generateFirstName(5);
        String lastName = DataProcess.generateLastName(7);
        String birthDate = DataProcess.generateRandomBirthDate(18,100);
        String email = DataProcess.generateEmail(firstName, lastName);
        String phone = DataProcess.generatePhoneNumber();
        String tcNo = DataProcess.generateTurkishIdentityNumber();
        String password = DataProcess.generatePassword(firstName, lastName, birthDate);

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        birthDateField.sendKeys(birthDate);
        emailField.sendKeys(email);
        phoneField.sendKeys(phone);
        tcNumberField.sendKeys(tcNo);
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        elementHelper.click(submitButton);
    }

    private String getOtpNumber(String otpNumber){
        if(otpNumber.equals("correctOtpNumber")){
            return Constants.OTP_NUMBER;
        }else{
           throw new IllegalArgumentException(otpNumber);
        }
    }
    public void enterOtpNumber(String otpNumber) {
        otpNumberField.sendKeys(getOtpNumber(otpNumber));
    }
    public void clickOtpConfirmButton() {
        elementHelper.click(otpNumber);
    }
    public void checkSuccessful() {
        elementHelper.checkVisible(playNowButton);
    }

}

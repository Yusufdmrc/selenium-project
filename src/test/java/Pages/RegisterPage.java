package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        signUpButton.click();
    }
    public void enterField(String value, String fieldName) {
        switch (fieldName) {
            case "Ad Name":
                firstNameField.sendKeys(value);
                break;
            case "Soyadı":
                lastNameField.sendKeys(value);
                break;
            case "Doğum Tarihi":
                birthDateField.sendKeys(value);
                break;
            case "E-posta":
                emailField.sendKeys(value);
                break;
            case "Cep telefonu":
                phoneField.sendKeys(value);
                break;
            case "TC Numarası":
                tcNumberField.sendKeys(value);
                break;
            case "Şifre":
                passwordField.sendKeys(value);
                break;
            case "OTP":
                otpNumberField.sendKeys(value);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }
    public void clickOtpConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        otpNumber.click();
    }
    public void checkSuccessful() {
        elementHelper.checkVisible(playNowButton);
    }
}

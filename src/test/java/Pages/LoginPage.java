package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Constants;
import util.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class LoginPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@class='btn-default btn-bg-primary loginboxcta']")
    WebElement memberLoginButton;

    @FindBy(id = "loginBtna")
    WebElement loginButton;

    @FindBy(xpath = "//span[@id='errorLoginModal']")
    WebElement errorMessage;

    @FindBy(id = "username")
    WebElement usernameBox;

    @FindBy(id = "password")
    WebElement passwordBox;

    @FindBy(css = "span[class='userDetailsBalance']")
    WebElement balance;

    //@FindBy(xpath ="//span[@class='value d-lg-none']/span[@class='userDetailsBalance']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    public void userAtHomePage() {
        String expectedUrl = Constants.TEST_URL;
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    public void clickMemberLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(memberLoginButton));
        memberLoginButton.click();
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    private String getUserName(String username) {
        switch (username) {
            case "correctTcID":
                return Constants.CORRECT_TC_ID;
            case "correctAccountNo":
                return Constants.CORRECT_ACCOUNT_NO;
            case "correctEmail":
                return Constants.CORRECT_EMAIL;
            case "empty":
                return " ";
            default:
                return username;
        }
    }

    private String getPassword(String password) {
        switch (password) {
            case "correctPassword":
                return Constants.CORRECT_PASSWORD;
            case "empty":
                return "";
            default:
                return password;
        }
    }

    public void writeUsernameForUsernameField(String username) {
        usernameBox.sendKeys(getUserName(username));
    }

    public void writePasswordForPasswordField(String password) {
        passwordBox.sendKeys(getPassword(password));
    }

    public void dontCredentialValidErrorMessage(String message) {
        elementHelper.checkVisible(errorMessage);
        Assert.assertEquals(errorMessage.getText(),message,"Expected to see" + message + "but we see" + errorMessage.getText());
    }

    public void checkSuccessful() {
        elementHelper.checkVisible(balance);
    }

    public void checkUnsuccessful() {
        elementHelper.checkNotVisible(balance);
    }
}
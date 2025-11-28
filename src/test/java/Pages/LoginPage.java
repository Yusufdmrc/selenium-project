package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.ConfigReader;
import util.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.LoginHelper;


import java.time.Duration;

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

    @FindBy(css ="a.loginbox-forgetpsw")
    WebElement forgotPasswordButton;

    //@FindBy(xpath ="//span[@class='value d-lg-none']/span[@class='userDetailsBalance']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    public void userAtHomePage() {
        String expectedUrl = ConfigReader.get("test.url");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    public void clickMemberLoginButton() {
        elementHelper.click(memberLoginButton);
    }

    public void clickLogin() {
        elementHelper.click(loginButton);
    }

    public void writeUsernameForUsernameField(String username) {
        usernameBox.sendKeys(LoginHelper.getUserName(username));
    }

    public void writePasswordForPasswordField(String password) {
        passwordBox.sendKeys(LoginHelper.getPassword(password));
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

    public void login(String username,String password){
        clickMemberLoginButton();
        writeUsernameForUsernameField(username);
        writePasswordForPasswordField(password);
        clickLogin();
        checkSuccessful();
    }

    public void navigateToPasswordRetrievalPage() {
        elementHelper.click(forgotPasswordButton);
    }
}

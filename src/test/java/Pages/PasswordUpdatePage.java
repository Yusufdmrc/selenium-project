package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;

public class PasswordUpdatePage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(id="new-password")
    WebElement newPasswordInput;
    @FindBy(id="repeat-password")
    WebElement repeatPasswordInput;
    @FindBy(id="reset-button")
    WebElement savePasswordButton;
    @FindBy(id="lgn-button")
    WebElement navigateLoginButton;
    @FindBy(xpath = "//input[@class='inputText'][@id='username']")
    WebElement usernameInput;
    @FindBy(xpath = "//input[@class='inputText'][@id='password']")
    WebElement passwordInput;
    @FindBy(id="loginBtn")
    WebElement loginButton;

    public PasswordUpdatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    private void reEnterNewPassword(String repeatNewPassword) {
        elementHelper.checkVisible(newPasswordInput);
        newPasswordInput.sendKeys(repeatNewPassword);
        elementHelper.checkVisible(repeatPasswordInput);
        repeatPasswordInput.sendKeys(repeatNewPassword);
    }

    private void submitNewPassword() {
        elementHelper.click(savePasswordButton);
    }

    private void enterUsername(String tcId) {
        elementHelper.checkVisible(usernameInput);
        usernameInput.sendKeys(tcId);
    }

    private void enterPassword(String password) {
        elementHelper.checkVisible(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void changeThePasswordAndSubmit(String password) {
        elementHelper.checkVisible(newPasswordInput);
        reEnterNewPassword(password);
        submitNewPassword();
    }

    public void navigateToLoginPage() {
        elementHelper.click(navigateLoginButton);
    }

    public void loginToApp(String tcId, String password) {
        enterUsername(tcId);
        enterPassword(password);
        elementHelper.click(loginButton);

    }
}

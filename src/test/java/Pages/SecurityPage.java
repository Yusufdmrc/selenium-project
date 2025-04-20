package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.Constants;
import util.ElementHelper;

import static util.Constants.EXPLICIT_WAIT;


import java.time.Duration;

public class SecurityPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;
    @FindBy(xpath = "(//span[contains(text(),'Güvenlik')])")
    WebElement securityButton;
    @FindBy(xpath = "//input[@aria-label='decorative checkbox']")
    WebElement selectAll;
    @FindBy(xpath = "//button[normalize-space()='Sil']")
    WebElement deleteButton;
    @FindBy(xpath = "//button[normalize-space()='TÜMÜNÜ SİL']")
    WebElement deleteAllButton;
    @FindBy(xpath = "//button[normalize-space()='GİRİŞ YAP']")
    WebElement loginButton;
    @FindBy(css = ".css-jfo1ar-noResultsText")
    WebElement securityText;

    public SecurityPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper=new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    public void navigateToSecurityPage() {
        elementHelper.click(accountButton);
        elementHelper.click(securityButton);
    }

    public void clickSelectAllButton() {
        elementHelper.click(selectAll);
    }

    public void clickDeleteButton() {
        elementHelper.click(deleteButton);
        elementHelper.click(deleteAllButton);
    }
    public void verifyDeletion() {
        elementHelper.click(loginButton);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.CORRECT_TC_ID,Constants.CORRECT_PASSWORD);

        navigateToSecurityPage();
        elementHelper.checkVisible(securityText);
        String actualText=securityText.getText().trim();
        Assert.assertEquals(actualText,"Henüz veri bulunmuyor.");
    }
}

package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;

public class WithdrawalPage{
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@class='btn-default btn-bg-primary loginboxcta']")
    WebElement memberLoginButton;
    @FindBy(id = "loginBtna")
    WebElement loginButton;
    @FindBy(id = "username")
    WebElement usernameBox;
    @FindBy(id = "password")
    WebElement passwordBox;
    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;
    @FindBy(xpath = "//span[normalize-space()='Para Çekme']")
    WebElement withdrawalButton;
    @FindBy(xpath = "//button[@data-testid='drawMoney.main.addNewBank.button']")
    WebElement  newAddButton;
    @FindBy(id = "iban")
    WebElement ibanField;
    @FindBy(id = "alias")
    WebElement shortNameField;
    @FindBy(xpath = "//button[contains(text(),'Doğrula')]")
    WebElement verifyButton;
    @FindBy(xpath = "//button[normalize-space()='Kaydet']")
    WebElement saveButton;
    @FindBy(xpath = "//button[@data-testid='drawMoney.main.bank_0_draw.button']")
    WebElement drawMoneyButton;
    @FindBy(id = "amount")
    WebElement amountField;
    @FindBy(xpath = " //button[@data-testid='drawMoney.makeWithdrawal.confirm.button']")
    WebElement confirmButton;
    @FindBy(xpath = "//p[@data-testid='general.modal.desc']")
    WebElement verifyText;

    public WithdrawalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToWithDrawalPage() {
        elementHelper.click(accountButton);
        elementHelper.click(withdrawalButton);
    }

    public void addNewIban(String ibanNo, String shortName) {
        elementHelper.click(newAddButton);
        ibanField.sendKeys(getIbanNo(ibanNo));
        elementHelper.click(verifyButton);
        shortNameField.sendKeys(getShortName(shortName));
        elementHelper.click(saveButton);
    }
    public void withdrawAmount(String price) {
        elementHelper.click(drawMoneyButton);
        amountField.sendKeys(getPrice(price));
        elementHelper.click(confirmButton);
    }
    public void verifySuccessfulWithdrawal() {
        elementHelper.checkVisible(verifyText);
    }

    public static String getShortName(String shortName) {
        switch (shortName) {
            case "shortName1":
                return ConfigReader.get("short.name1");
            case "empty":
                return " ";
            default:
                return shortName;
        }
    }
    public static String getPrice(String price) {
        switch (price) {
            case "price1":
                return ConfigReader.get("price1");
            case "empty":
                return " ";
            default:
                return price;
        }
    }

    public static String getIbanNo(String ibanNo) {
        switch (ibanNo) {
            case "ibanNo":
                return ConfigReader.get("iban.no");
            case "empty":
                return " ";
            default:
                return ibanNo;
        }
    }
}

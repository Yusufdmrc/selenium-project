package Pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;
    @FindBy(xpath = "//span[contains(text(),'Sayısal Oyunlar')]")
    WebElement LotteryGamesTitle;
    @FindBy(xpath = "//a[contains(@href,'milli-piyango')][@class='gtm-headernavigation']")
    WebElement MlpTitle;
    @FindBy(xpath = "//iframe[@id='#milliPiyangoCardFrame']")
    WebElement MlpIframeId;
    @FindBy(xpath = "//a[contains(@href,'sayisal-loto')][@class='gtm-headernavigation']")
    WebElement SylGameMenu;
    @FindBy(xpath = "//iframe[@id='#sayisalLotoCardFrame']")
    WebElement SylIframeId;
    @FindBy(xpath = "//a[contains(@href,'super-loto')][@class='gtm-headernavigation']")
    WebElement SplGameMenu;
    @FindBy(xpath = "//iframe[@id='#superLotoCardFrame']")
    WebElement SplIframeId;
    @FindBy(xpath = "//a[contains(@href,'sans-topu')][@class='gtm-headernavigation']")
    WebElement SnsGameMenu;
    @FindBy(xpath = "//iframe[@id='#sansTopuCardFrame']")
    WebElement SnsIframeId;
    @FindBy(xpath = "//a[contains(@href,'on-numara')][@class='gtm-headernavigation']")
    WebElement NmrGameMenu;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToAreaPrivatePage() {
        elementHelper.click(accountButton);
    }

    public void navigateToLotteryGame(String gameName, Scenario scenario) {
        switch (gameName){
            case "Sayisal loto":
                elementHelper.click(LotteryGamesTitle);
                elementHelper.click(SylGameMenu);
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SylIframeId));
                break;
            case "Super loto":
                elementHelper.click(LotteryGamesTitle);
                elementHelper.click(SplGameMenu);
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SplIframeId));
                break;
            case "Sans topu":
                elementHelper.click(LotteryGamesTitle);
                elementHelper.click(SnsGameMenu);
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SnsIframeId));
                break;
            case "On numara":
                elementHelper.click(LotteryGamesTitle);
                elementHelper.click(NmrGameMenu);
                break;
            case "Milli piyango":
                elementHelper.click(MlpTitle);
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(MlpIframeId));
                break;
            default:
                scenario.log("The game name provided does not match any known lottery games.");
                break;
        }
    }
}

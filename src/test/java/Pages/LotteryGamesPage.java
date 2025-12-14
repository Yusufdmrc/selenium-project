package Pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;

public class LotteryGamesPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//button[.=Sen Seç]")
    WebElement SenSec;
    @FindBy(xpath = "//button[.=Sistem Oyunu]")
    WebElement SistemOyunu;
    @FindBy(xpath = "//button[contains(@class,'flashNumberBtn']")
    WebElement randomButton;


    public LotteryGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);
    }

    public void navigateToGameType(String gameType, Scenario scenario) {
        WebElement elementToClick=getGameTypeElement(gameType);
        try{
            elementHelper.click(elementToClick);
        }catch (Exception e){
            scenario.log("Failed to navigate to the " + gameType + " tab: " + e.getMessage());
            throw e;
        }
    }

    private WebElement getGameTypeElement(String gameType){
        switch (gameType){
            case "multiple":
            case "SistemOyunu":
               return SistemOyunu;
            case "simple":
            case "SenSec":
                return SenSec;
            default:
                throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
    }

    public void enterRowByRandomButton(int clickCount, Scenario scenario) {
        if(elementHelper.checkNotVisible(randomButton)) {
            scenario.log("Random button is not visible on the page.");
            throw new IllegalStateException("Random button is not visible on the page.");
        }
        for (int i=1; i<=clickCount;i++){
            elementHelper.pause(1);
            elementHelper.click(randomButton);
        }
    }
}

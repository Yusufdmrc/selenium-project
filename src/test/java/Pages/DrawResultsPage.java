package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigReader;
import utils.ElementHelper;

import java.time.Duration;
import java.util.List;

public class DrawResultsPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@name='filter'][@value='all']/..")
    WebElement allGamesFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='sayisaloto']/..")
    WebElement sayisalLotoFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='superloto']/..")
    WebElement superLotoFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='millipiyango']/..")
    WebElement milliPiyangoFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='sanstopu']/..")
    WebElement sansTopuFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='onnumara']/..")
    WebElement onNumaraFilter;

    @FindBy(xpath = "//input[@name='filter'][@value='hizlionnumara']/..")
    WebElement hizliOnNumaraFilter;

    @FindBy(xpath = "//button[contains(@class,'draws-submit')]")
    WebElement filterButton;

    @FindBy(xpath = "//div[@id='draws']//div[contains(@class, 'draw-item')]")
    List<WebElement> drawResults;

    @FindBy(xpath = "//div[@id='draws']//div[@loto]")
    List<WebElement> resultLotoDivs;

    public DrawResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToDrawResults() {
        driver.get(ConfigReader.get("test.url") + "cekilis-sonuclari");
    }

    public void filterByGame(String game) {
        WebElement filterElement;
        switch (game.toLowerCase()) {
            case "sayısal loto":
                filterElement = sayisalLotoFilter;
                break;
            case "süper loto":
                filterElement = superLotoFilter;
                break;
            case "milli piyango":
                filterElement = milliPiyangoFilter;
                break;
            case "şans topu":
                filterElement = sansTopuFilter;
                break;
            case "on numara":
                filterElement = onNumaraFilter;
                break;
            case "hızlı on numara":
                filterElement = hizliOnNumaraFilter;
                break;
            case "tüm oyunlar":
                filterElement = allGamesFilter;
                break;
            default:
                throw new IllegalArgumentException("Invalid game: " + game);
        }
        elementHelper.click(filterElement);
        elementHelper.click(filterButton);
    }

    public void verifyResultsBelongToGame(String game) {
        String expectedLotoAttr = getExpectedLotoAttr(game);
        elementHelper.pause(2); // Wait for results to update
        if (resultLotoDivs.isEmpty()) {
            return;
        }
        for (WebElement div : resultLotoDivs) {
            String lotoAttr = div.getAttribute("loto");
            if (lotoAttr != null && !lotoAttr.contains("{{lotteryName}}")) {
                Assert.assertEquals(lotoAttr, expectedLotoAttr, 
                    "Expected loto attribute to be '" + expectedLotoAttr + "' but found '" + lotoAttr + "'");
            }
        }
    }

    private String getExpectedLotoAttr(String game) {
        switch (game.toLowerCase()) {
            case "sayısal loto": return "SAYISAL";
            case "süper loto": return "SUPERLOTO";
            case "milli piyango": return "MILLIPIYANGO";
            case "şans topu": return "SANSTOPU";
            case "on numara": return "ONNUMARA";
            case "hızlı on numara": return "HIZLI-ON-NUMARA";
            default: return "";
        }
    }
}

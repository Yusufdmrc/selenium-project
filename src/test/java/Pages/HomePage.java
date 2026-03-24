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

import java.text.Normalizer;
import java.time.Duration;
import java.util.Locale;

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

    public void navigateToLotteryGame(String gameNameRaw) {
        String key = canonical(gameNameRaw);

        // --- Sayısal Loto ---
        if (matchesAny(key, "sayisalloto", "sayisal", "syl", "cilginsayisalloto", "sayısalloto")) {
            elementHelper.click(LotteryGamesTitle);
            elementHelper.click(SylGameMenu);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SylIframeId));
            return;
        }

        // --- Süper Loto ---
        if (matchesAny(key, "superloto", "super", "spl", "süperloto")) {
            elementHelper.click(LotteryGamesTitle);
            elementHelper.click(SplGameMenu);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SplIframeId));
            return;
        }

        // --- Şans Topu ---
        if (matchesAny(key, "sanstopu", "sans", "sns", "şanstopu")) {
            elementHelper.click(LotteryGamesTitle);
            elementHelper.click(SnsGameMenu);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(SnsIframeId));
            return;
        }

        // --- On Numara ---
        if (matchesAny(key, "onnumara", "onnum")) {
            elementHelper.click(LotteryGamesTitle);
            elementHelper.click(NmrGameMenu);
            return;
        }

        // --- Milli Piyango ---
        if (matchesAny(key, "millipiyango", "mp", "milli")) {
            elementHelper.click(MlpTitle);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(MlpIframeId));
            return;
        }

        throw new IllegalArgumentException("Invalid game name: " + gameNameRaw + " (canonical: " + key + ")");
    }

    // "Şans  Topu", "sans-topu", "SANS_TOPU", "ŞANS-TOPU" -> "sanstopu"
    private String canonical(String s) {
        if (s == null) return "";
        String lower = s.toLowerCase(new Locale("tr","TR")).trim();
        String noAccents = Normalizer.normalize(lower, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String collapsed = noAccents.replaceAll("[^a-z0-9]+", ""); // boşluk, tire, alt çizgi, noktalama hepsi kalkar
        return collapsed;
    }

    private boolean matchesAny(String key, String... candidates) {
        for (String c : candidates) {
            if (key.equals(c)) return true;
        }
        return false;
    }
}

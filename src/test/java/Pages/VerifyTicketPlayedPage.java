package Pages;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.Constants.EXPLICIT_WAIT;

public class VerifyTicketPlayedPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;
    List<String> backendNumbers = new ArrayList<>();

    @FindBy(css = "button.betBtn.validBtn")
    WebElement buyButton;

    @FindBy(xpath = "//div[@class='stato-label']")
    WebElement ticketText;

    @FindBy(xpath = "//span[contains(text(),'Sayısal Oyunlar')]")
    WebElement sayisalOyunlarButton;

    @FindBy(css = "div.combinazioni-body ul li div.numero")
    List<WebElement> playedNumbers;

    @FindBy(xpath = "//a[normalize-space()='Sayisal Loto']")
    private WebElement sayisalLotoPage;

    @FindBy(xpath = "//a[normalize-space()='Super Loto']")
    private WebElement superLotoPage;

    @FindBy(xpath = "//a[normalize-space()='On Numara']")
    private WebElement onNumaraPage;

    @FindBy(xpath = "//a[normalize-space()='Sans Topu']")
    private WebElement sansTopuPage;

    @FindBy(id="sayisalLotoCardFrame")
    private WebElement sayisalLotoIFrameId;

    @FindBy(id="superLotoCardFrame")
    private WebElement superLotoIFrameId;

    @FindBy(id="onnumaraCardFrame")
    private WebElement onNumaraIFrameId;

    @FindBy(id="sansTopuCardFrame")
    private WebElement sansTopuIFrameId;

    @FindBy(css = "div.flashIcon")
    WebElement onNumaraRandomButton;

    @FindBy(css = "div.flash span.textIconBtn")
    WebElement sansTopuRandomButton;

    @FindBy(xpath = "//div[@class='flash']")
    WebElement superLotoRandomButton;

    @FindBy(css = "div[class='flash'] span[class='textIconBtn']")
    WebElement sayisalLotoRandomButton;

    public VerifyTicketPlayedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void startNetworkMonitoring() {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        backendNumbers.clear();

        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (StringUtils.endsWithIgnoreCase(url, "sell")) {
                System.out.println("#### SELL REQUEST DETECTED ####");
                System.out.println("SEND URL: " + url);
                request.getPostData().ifPresent(postData -> {
                    System.out.println("Sell Request Data: " + postData);
                    extractNumbersFromBackend(postData);
                });
            }


        });
    }

    private void extractNumbersFromBackend(String postData) {
        try {
            JsonObject jsonObject = JsonParser.parseString(postData).getAsJsonObject();
            JsonArray marcatureArray = jsonObject.getAsJsonObject("biglietto")
                    .getAsJsonArray("pannelli")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonArray("marcature");

            backendNumbers.clear();
            marcatureArray.forEach(element -> backendNumbers.add(element.getAsString()));

            System.out.println("Backend Numbers: " + backendNumbers);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    public List<String> getPlayedNumbers() {
        List<String> numbers = new ArrayList<>();
        for (WebElement number : playedNumbers) {
            numbers.add(number.getText().trim());
        }
        return numbers;
    }

    public void navigateToOnNumaraPage(String game) {
        WebElement gamePageOption = getGamePage(game);
        elementHelper.click(sayisalOyunlarButton);
        elementHelper.click(gamePageOption);
        WebElement gameFrameOption = getGameFrame(game);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gameFrameOption));
    }

    public void buyTicket(String game) {
        WebElement randomButton = getGameRandomButton(game);
        elementHelper.click(randomButton);
        elementHelper.click(buyButton);
        elementHelper.pause(5);
    }

    public void checkTicket() {
        elementHelper.checkVisible(ticketText);
        Assert.assertEquals(ticketText.getText(), "Çekiliş için bekleniyor");
    }

    public void checkNumbersPlayed() {
        List<String> uiNumbers = getPlayedNumbers();
        System.out.println("UI Numbers: " + uiNumbers);
        System.out.println("Backend Numbers: " + backendNumbers);
        Assert.assertEquals(uiNumbers, backendNumbers);
    }

    private WebElement getGamePage(String game) {
        switch (game) {
            case "Sayisal Loto": return sayisalLotoPage;
            case "Super Loto": return superLotoPage;
            case "On Numara": return onNumaraPage;
            case "Sans Topu": return sansTopuPage;
            default: throw new IllegalArgumentException("Invalid game page: " + game);
        }
    }

    private WebElement getGameFrame(String game) {
        switch (game) {
            case "Sayisal Loto": return sayisalLotoIFrameId;
            case "Super Loto": return superLotoIFrameId;
            case "On Numara": return onNumaraIFrameId;
            case "Sans Topu": return sansTopuIFrameId;
            default: throw new IllegalArgumentException("Invalid frame: " + game);
        }
    }

    private WebElement getGameRandomButton(String game) {
        switch (game) {
            case "Sayisal Loto": return sayisalLotoRandomButton;
            case "Super Loto": return superLotoRandomButton;
            case "On Numara": return onNumaraRandomButton;
            case "Sans Topu": return sansTopuRandomButton;
            default: throw new IllegalArgumentException("Invalid random button: " + game);
        }
    }
}

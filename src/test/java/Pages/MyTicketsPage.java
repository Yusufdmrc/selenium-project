package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;
import java.util.List;

import static util.Constants.EXPLICIT_WAIT;

public class MyTicketsPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;

    @FindBy(xpath = "(//span[contains(text(),'Biletlerim')])[1]")
    WebElement biletlerimButton;

    @FindBy(css = "div.css-1h9fo2t-footer_details span.css-4qwqxz-footer_details_content")
    List<WebElement> detailButtons;

    @FindBy(xpath = "//div[2]//div[1]//button[1]")
    private WebElement milliPiyangoButton;

    @FindBy(xpath = "//div[3]//div[1]//button[1]")
    private WebElement sayisalLotoButton;

    @FindBy(xpath = "//div[4]//div[1]//button[1]")
    private WebElement superLotoButton;

    @FindBy(xpath = "//div[5]//div[1]//button[1]")
    private WebElement sansTopuButton;

    @FindBy(xpath = "//div[6]//div[1]//button[1]")
    private WebElement onNumaraButton;

    @FindBy(xpath = "//option[@value=1 and contains(text(),'Çekiliş Bekleyen')]")
    private WebElement lotteryPendingOption;

    @FindBy(xpath = "//option[@value=3 and contains(text(),'Kaybeden')]")
    private WebElement lotteryLoserOption;

    @FindBy(xpath = "//option[@value=4 and contains(text(),'Kazanan')]")
    private WebElement lotteryWinnerOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[1]")
    private WebElement threeDayOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[2]")
    private WebElement sevenDayOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[3]")
    private WebElement fifteenDayOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[4]")
    private WebElement oneMonthOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[5]")
    private WebElement twoMonthOption;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[6]")
    private WebElement threeMonthOption;

    @FindBy(xpath = "//div[@class='msg-head show']")
    private WebElement ticketWinText;

    @FindBy(xpath = "//div[@class='stato-label show']")
    private WebElement ticketText;

    @FindBy(xpath = "(//img[contains(@src, 'sayisal-loto')])[2]")
    private WebElement sayisalLotoLogo;

    @FindBy(xpath = "(//img[contains(@src, 'super-loto')])[2]")
    private WebElement superLotoLogo;

    @FindBy(xpath = "(//img[contains(@src, 'on-numara')])[1]")
    private WebElement onNumaraLogo;

    @FindBy(xpath = "(//img[contains(@src, '-millipiyango')])[1]")
    private WebElement milliPiyangoLogo;

    @FindBy(xpath = "(//img[contains(@src, 'sans-topu')])[2]")
    private WebElement sansTopuLogo;

    public MyTicketsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToBiletlerimPage() {
        elementHelper.click(accountButton);
        elementHelper.click(biletlerimButton);
    }

    public void filterBySelecting(String game, String status, String dateRange) {
        WebElement gameOption = getGameOption(game);
        WebElement statusOption = getStatusOption(status);
        WebElement dateOption = getDateOption(dateRange);

        elementHelper.click(gameOption);
        elementHelper.click(statusOption);
        elementHelper.click(dateOption);
    }

    public void clickDetailButton() {
        if (detailButtons.size() >=4){
            elementHelper.click(detailButtons.get(3));
        }else{
            elementHelper.click(detailButtons.get(1));
        }
    }

    public void validateTicketDetail(String expectedMessage, String status) {
        WebElement validationElement = getValidationElement(status);
        elementHelper.checkVisible(validationElement);
        Assert.assertEquals(validationElement.getText(), expectedMessage);
    }

    public void selectGameFilter(String game) {
        WebElement gameOption = getGameOption(game);
        elementHelper.click(gameOption);
    }

    public void confirmFilter(String drawName) {
        WebElement logoElement = getLogoElement(drawName);
        String actualSrc = logoElement.getAttribute("src");
        Assert.assertTrue(actualSrc.contains(drawName));
    }

    private WebElement getGameOption(String game) {
        switch (game) {
            case "On Numara": return onNumaraButton;
            case "Şans Topu": return sansTopuButton;
            case "Sayisal Loto": return sayisalLotoButton;
            case "Milli Piyango": return milliPiyangoButton;
            case "Süper Loto": return superLotoButton;
            default: throw new IllegalArgumentException("Geçersiz oyun: " + game);
        }
    }

    private WebElement getStatusOption(String status) {
        switch (status) {
            case "Çekiliş Bekleyen": return lotteryPendingOption;
            case "Kazanan": return lotteryWinnerOption;
            case "Kaybeden": return lotteryLoserOption;
            default: throw new IllegalArgumentException("Geçersiz durum: " + status);
        }
    }

    private WebElement getDateOption(String dateRange) {
        switch (dateRange) {
            case "3 gün": return threeDayOption;
            case "7 gün": return sevenDayOption;
            case "1 ay": return oneMonthOption;
            case "2 ay": return twoMonthOption;
            case "3 ay": return threeMonthOption;
            default: throw new IllegalArgumentException("Geçersiz tarih aralığı: " + dateRange);
        }
    }

    private WebElement getValidationElement(String status) {
        switch (status) {
            case "Kazanan": return ticketWinText;
            case "Çekiliş Bekleyen":
            case "Kazanamadın": return ticketText;
            default: throw new IllegalArgumentException("Geçersiz durum: " + status);
        }
    }

    private WebElement getLogoElement(String drawName) {
        switch (drawName) {
            case "sayisal-loto": return sayisalLotoLogo;
            case "super-loto": return superLotoLogo;
            case "on-numara": return onNumaraLogo;
            case "millipiyango": return milliPiyangoLogo;
            case "sans-topu": return sansTopuLogo;
            default: throw new IllegalArgumentException("Geçersiz çekiliş adı: " + drawName);
        }
    }
}

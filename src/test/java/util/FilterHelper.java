package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterHelper {

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

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[2]")
    private WebElement lotteryPendingOption;

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[4]")
    private WebElement lotteryLoserOption;

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[5]")
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

    public FilterHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getGameOption(WebDriver driver, String game) {
        switch (game) {
            case "On Numara": return onNumaraButton;
            case "Şans Topu": return sansTopuButton;
            case "Sayisal Loto": return sayisalLotoButton;
            case "Milli Piyango": return milliPiyangoButton;
            case "Süper Loto": return superLotoButton;
            default: throw new IllegalArgumentException("Invalid game: " + game);
        }
    }

    public WebElement getStatusOption(WebDriver driver, String status) {
        switch (status) {
            case "Çekiliş Bekleyen": return lotteryPendingOption;
            case "Kazanan": return lotteryWinnerOption;
            case "Kaybeden": return lotteryLoserOption;
            default: throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    public WebElement getDateOption(WebDriver driver, String dateRange) {
        switch (dateRange) {
            case "3 gün": return threeDayOption;
            case "7 gün": return sevenDayOption;
            case "1 ay": return oneMonthOption;
            case "2 ay": return twoMonthOption;
            case "3 ay": return threeMonthOption;
            default: throw new IllegalArgumentException("Invalid date range: " + dateRange);
        }
    }

    public WebElement getValidationElement(WebDriver driver, String status) {
        switch (status) {
            case "Kazanan":
                return ticketWinText;
            case "Çekiliş Bekleyen":
            case "Kazanamadın":
                return ticketText;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    public WebElement getLogoElement(WebDriver driver, String drawName) {
        switch (drawName) {
            case "sayisal-loto":
                return sayisalLotoLogo;
            case "super-loto":
                return superLotoLogo;
            case "on-numara":
                return onNumaraLogo;
            case "millipiyango":
                return milliPiyangoLogo;
            case "sans-topu":
                return sansTopuLogo;
            default:
                throw new IllegalArgumentException("Invalid draw name: " + drawName);
        }
    }
}

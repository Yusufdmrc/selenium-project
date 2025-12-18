package Pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static reporting.Logging.writeConsoleLog;
import static util.DataProcess.getCurrentTime;

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
    @FindBy(xpath = "//button[contains(@class,'betBtn') and text()='SATIN AL']")
    WebElement buyTicketButton;
    @FindBy(xpath = "//p[contains(text(),'5.000 TL ve üzeri bir bilet']")
    WebElement overFiveThousandPopup;
    @FindBy(xpath = "//button[.='Yeniden Oyna']")
    WebElement playAgainButton;
    @FindBy(xpath = "//button[.='ONAYLA']")
    WebElement confirmButton;
    @FindBy(css = "div.date")
    WebElement receiptDateText;
    @FindBy(css = "div.price")
    WebElement receiptPriceText;


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
        if(elementHelper.isNotVisible(randomButton)) {
            scenario.log("Random button is not visible on the page.");
            throw new IllegalStateException("Random button is not visible on the page.");
        }
        for (int i=1; i<=clickCount;i++){
            elementHelper.pause(1);
            elementHelper.click(randomButton);
        }
    }

    public void buyTicket() {
        boolean ticketBoughtWithSuccess = false;
        int i= 0;
        while (i<5){
            writeConsoleLog("the try count is : " + i);
            if (elementHelper.isVisible(buyTicketButton)){
                clickBuyButton();
            }
            if (elementHelper.isVisible(overFiveThousandPopup)){
                clickConfirmButton();
            }
            if (elementHelper.isVisible(playAgainButton)){
                ticketBoughtWithSuccess = true;
                break;
            }
            if (elementHelper.isVisible(confirmButton)){
                clickConfirmButton();
            }
            i++;
        }
        Assert.assertTrue(ticketBoughtWithSuccess,"TICKET WAS BOUGHT WITH SUCCESS AFTER"+ i + "TRIES");
    }

    private void clickBuyButton() {
        elementHelper.click(buyTicketButton);
    }
    private void clickConfirmButton() {
        elementHelper.click(overFiveThousandPopup);
    }

    public void verifyReceiptElements(int drawNumber, int columnNumber, int standartNumber, int superstarNumber, Scenario scenario) {
        verifyReceiptTitleBox(drawNumber,columnNumber,scenario);
        verifyReceiptInfoBox();
        verifyReceiptBodyBox(drawNumber,standartNumber,superstarNumber,scenario);
        verifyReceiptDetailBox(columnNumber,scenario);
    }

    private void verifyReceiptTitleBox(int drawNumber, int columnNumber, Scenario scenario) {
        elementHelper.checkVisible(receiptDateText);
        Assert.assertTrue(receiptDateText.getText().contains(getDate()));
        verifyTimePart(receiptDateText.getText());
        Assert.assertTrue(receiptPriceText.getText(),getTicketPrice(drawNumber,columnNumber,scenario) + " TL"," receipt price text displays correct");
        elementHelper.checkVisible(receiptTicketDetailTitle);
    }

    private void verifyTimePart(String elementText) {
        // Extract the time part (e.g., "12:04") from the element text
        String timePart = elementText.split("-")[1].trim();

        // Parse the extracted time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime extractedTime = LocalTime.parse(timePart, timeFormatter);

        // Get the current time in the system's default time zone
        ZoneId userTimeZone = ZoneId.systemDefault();
        LocalTime currentTime = LocalTime.now(userTimeZone);

        // Calculate the acceptable range (±1 minute)
        LocalTime lowerBound = currentTime.minusMinutes(1).withSecond(0).withNano(0);
        LocalTime upperBound = currentTime.plusMinutes(1).withSecond(59).withNano(999_999_999);

        // Adjust extracted time to include the full range of seconds and nanoseconds
        LocalTime extractedLowerBound = extractedTime.withSecond(0).withNano(0);
        LocalTime extractedUpperBound = extractedTime.withSecond(59).withNano(999_999_999);

        // Verify that the extracted time range overlaps with the acceptable range
        if (extractedUpperBound.isBefore(lowerBound) || extractedLowerBound.isAfter(upperBound)) {
            throw new AssertionError("Time verification failed. Extracted time: " + extractedTime +
                    " is not within the acceptable range: " + lowerBound + " - " + upperBound);
        }
    }

    public String getDate() {
        String month=getCurrentTime("MM");
        String day=Integer.toString(Integer.parseInt(getCurrentTime("dd")));
        String year=getCurrentTime("yyyy");
        writeConsoleLog("month is:" + month);
        writeConsoleLog("day is:" + day);
        writeConsoleLog("year is:" + year);
        switch (month){
            case "01":
                month="Ocak";
                break;
            case "02":
                month="Şubat";
                break;
            case "03":
                month="Mart";
                break;
            case "04":
                month="Nisan";
                break;
            case "05":
                month="Mayıs";
                break;
            case "06":
                month="Haziran";
                break;
            case "07":
                month="Temmuz";
                break;
            case "08":
                month="Ağustos";
                break;
            case "09":
                month="Eylül";
                break;
            case "10":
                month="Ekim";
                break;
            case "11":
                month="Kasım";
                break;
            case "12":
                month="Aralık";
                break;
            default:
                writeConsoleLog("Please check the month value");
        }
        String date= day + " " + month + " " + year;
        writeConsoleLog("date is :" + date);
        return date;
    }

    public void verifyDetailsOfTheTicket(List<ConcurrentMap<String, String>> listOfTickets, String gameName) {
    }
}

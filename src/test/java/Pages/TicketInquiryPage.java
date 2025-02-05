package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class TicketInquiryPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//span[normalize-space()='Sonuçlar']")
    WebElement resultsButton;

    @FindBy(css = "//a[@class='gtm-headernavigation'][contains(text(),'Çekilişler')]")
    WebElement lotteriesButton;

    @FindBy(css = "#txt_checkwinresults")
    WebElement ticketInput;

    @FindBy(css = ".wins-submit.gtm-checkwinresult")
    WebElement queryButton;


    @FindBy(xpath = "//div[@class='slick-slide slick-current slick-active']//p[contains(text(),'Kazandın!')]")
    WebElement ticketStatusText;

    public TicketInquiryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLotteryResults() {
     elementHelper.click(resultsButton);
     elementHelper.click(lotteriesButton);
    }

    public void inquireTicket(String serialNumber) {
        elementHelper.checkClickable(ticketInput);
        ticketInput.sendKeys(serialNumber);
        elementHelper.click(queryButton);
    }

    public void checkTicketDetail(String statusText) {
        elementHelper.checkVisible(ticketStatusText);
        Assert.assertEquals(ticketStatusText.getText(),statusText);
    }
}

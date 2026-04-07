package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ConfigReader;
import util.ElementHelper;

import java.time.Duration;

public class TicketInquiryPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//span[normalize-space()='Sonuçlar']")
    WebElement resultsButton;

    @FindBy(xpath= "//a[@class='gtm-headernavigation'][contains(text(),'Çekilişler')]")
    WebElement lotteriesButton;

    @FindBy(css = "#txt_checkwinresults")
    WebElement ticketInput;

    @FindBy(css = ".wins-submit.gtm-checkwinresult")
    WebElement queryButton;

    @FindBy(xpath = "//div[@class='slick-slide slick-current slick-active']//p[contains(text(),'KAZANILAN')]")
    WebElement winningTicketText;

    @FindBy(xpath = "//div[@class='slick-slide slick-current slick-active']//p[contains(text(),'Bu bilete ikramiye isabet etmedi')]")
    WebElement losingTicketText;

    @FindBy(xpath = "//div[@class='modal-body-content']//p[contains(text(),'Bu makbuz kodu için veri bulunmuyor')]")
    WebElement invalidTicketText;

    public TicketInquiryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
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
        if (statusText.equals("Kazandın!")) {
            elementHelper.checkVisible(winningTicketText);
            Assert.assertTrue(winningTicketText.isDisplayed(), "The details of the winning ticket were not displayed");
        } else if (statusText.equals("Kazanamadın!")) {
            elementHelper.checkVisible(losingTicketText);
            Assert.assertTrue(losingTicketText.isDisplayed(), "Details of the losing ticket were not displayed");
        } else if (statusText.equals("Geçersiz bilet!")) {
            elementHelper.checkVisible(invalidTicketText);
            Assert.assertTrue(invalidTicketText.isDisplayed(), "Details of the invalid ticket were not displayed");
        }
    }
}

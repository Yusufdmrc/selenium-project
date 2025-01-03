package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class CheckTicketDetailPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(id = "statusFilter")
    WebElement statusFilter;

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[2]")
    WebElement lotteryPendingOption;

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[4]")
    WebElement lotteryLoserOption;

    @FindBy(xpath = "//*[@id=\"statusFilter\"]/option[5]")
    WebElement lotteryWinnerOption;

    @FindBy(css = "div.MuiGrid-root.MuiGrid-container.css-1d3bbye > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-6.css-1e6jl34-card_grid_container:last-of-type span.css-4qwqxz-footer_details_content")
    WebElement detailButton;

    @FindBy(xpath = "//div[@class='msg-head show']")
    WebElement ticketWinText;

    @FindBy(xpath = "//div[@class='stato-label show']")
    WebElement ticketText;

    @FindBy(xpath = "//*[@id=\"periodFilter\"]/option[6]")
    WebElement dayOption;


    public CheckTicketDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectStatusFilter(String status) {
        WebElement statusOption;
        switch (status) {
            case "Çekiliş Bekleyen":
                statusOption = lotteryPendingOption;
                break;
            case "Kazanan":
                statusOption = lotteryWinnerOption;
                break;
            case "Kaybeden":
                statusOption = lotteryLoserOption;
                break;
            default:
                throw new IllegalArgumentException("status: " + status);
        }
        elementHelper.click(statusOption);
        elementHelper.click(dayOption);
    }

    public void clickDetailButton() {
        elementHelper.click(detailButton);
    }

    public void validateTicketDetail(String expectedMessage) {
        elementHelper.checkVisible(ticketText);
        Assert.assertEquals(ticketText.getText(),expectedMessage);
    }


}

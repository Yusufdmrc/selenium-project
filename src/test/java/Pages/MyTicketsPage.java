package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;
import util.FilterHelper;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class MyTicketsPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;
    FilterHelper filterHelper;

    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;

    @FindBy(xpath = "//span[normalize-space()='Biletlerim']")
    WebElement biletlerimButton;

    @FindBy(css = "div.MuiGrid-root.MuiGrid-container.css-1d3bbye > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-6.css-1e6jl34-card_grid_container:last-of-type span.css-4qwqxz-footer_details_content")
    WebElement detailButton;


    public MyTicketsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        this.filterHelper = new FilterHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToBiletlerimPage() {
        elementHelper.click(accountButton);
        elementHelper.click(biletlerimButton);
    }

    public void filterBySelecting(String game, String status, String dateRange) {
        WebElement gameOption = filterHelper.getGameOption(driver, game);
        WebElement statusOption = filterHelper.getStatusOption(driver, status);
        WebElement dateOption = filterHelper.getDateOption(driver, dateRange);

        elementHelper.click(gameOption);
        elementHelper.click(statusOption);
        elementHelper.click(dateOption);
    }

    public void clickDetailButton() {
        elementHelper.click(detailButton);
    }

    public void validateTicketDetail(String expectedMessage,String status) {
        WebElement validationElement = filterHelper.getValidationElement(driver, status);
        elementHelper.checkVisible(validationElement);
        Assert.assertEquals(validationElement.getText(), expectedMessage);
    }

    public void selectGameFilter(String game) {
        WebElement gameOption = filterHelper.getGameOption(driver, game);
        elementHelper.click(gameOption);
    }

    public void confirmFilter(String drawName) {
        WebElement logoElement = filterHelper.getLogoElement(driver,drawName);
        String actualSrc = logoElement.getAttribute("src");
        Assert.assertTrue(actualSrc.contains(drawName));
    }
}

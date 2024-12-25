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

public class TicketFilteringPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Hesabım')]")
    WebElement accountButton;

    @FindBy(xpath = "//span[normalize-space()='Biletlerim']")
    WebElement biletlerimButton;

    @FindBy(css = "img[alt='Next arrow icon']")
    WebElement arrowButton;

    @FindBy(xpath = "//div[6]//div[1]//button[1]")
    WebElement onNumaraButton;

    @FindBy(xpath = "(//img[@alt='logo'])[1]")
    WebElement onNumaraLogo;


    public TicketFilteringPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToBiletlerimPage() {
        elementHelper.click(accountButton);
        elementHelper.click(biletlerimButton);
    }

    public void selectOnNumaraGameFilter() {
        elementHelper.click(arrowButton);
        elementHelper.click(onNumaraButton);
    }

    public void validateGameFilter() {
        elementHelper.checkVisible(onNumaraLogo);
    }
}

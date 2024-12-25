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

public class VerifyTicketPlayedPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(),'Sayısal Oyunlar')]")
    WebElement numericalGamesButton;

    @FindBy(xpath = "//a[normalize-space()='On Numara']")
    WebElement onNumaraButton;

    @FindBy(xpath = "//span[normalize-space()='Rastgele']")
    WebElement randomButton;

    @FindBy(xpath = "//button[normalize-space()='SATIN AL']")
    WebElement buyButton;

    @FindBy(xpath = "//div[@class='stato-label']")
    WebElement ticketText;

    public VerifyTicketPlayedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToOnNumaraPage() {
        elementHelper.click(numericalGamesButton);
        elementHelper.click(onNumaraButton);
    }

    public void buyTicket() {
//        wait.until(driver -> {
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
//            return randomButton.isDisplayed();
//        });
        elementHelper.click(randomButton);
        elementHelper.click(buyButton);
    }

    public void checkTicket() {
        elementHelper.checkVisible(ticketText);
        Assert.assertEquals(ticketText.getText(),"Çekiliş için bekleniyor");
    }
}

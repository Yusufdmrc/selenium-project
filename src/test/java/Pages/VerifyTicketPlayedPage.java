package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;
import util.GamePageHelper;

import java.time.Duration;

import static util.Constants.EXPLICIT_WAIT;

public class VerifyTicketPlayedPage {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;



    @FindBy(css = "button.betBtn.validBtn")
    WebElement buyButton;

    @FindBy(xpath = "//div[@class='stato-label']")
    WebElement ticketText;

    @FindBy(xpath = "//span[contains(text(),'Sayısal Oyunlar')]")
    WebElement sayisalOyunlarButton;

    public VerifyTicketPlayedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToOnNumaraPage(String game) {
        GamePageHelper gamePageHelper = new GamePageHelper(driver);
        WebElement gamePageOption = gamePageHelper.getGamePage(driver, game);
        elementHelper.click(sayisalOyunlarButton);
        elementHelper.click(gamePageOption);
        WebElement gameFrameOption=gamePageHelper.getGameFrame(driver,game);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gameFrameOption));
    }

    public void buyTicket() {
        elementHelper.click(randomButton);
        elementHelper.click(buyButton);
        elementHelper.pause(5);
    }

    public void checkTicket() {
        elementHelper.checkVisible(ticketText);
        Assert.assertEquals(ticketText.getText(),"Çekiliş için bekleniyor");
    }
}

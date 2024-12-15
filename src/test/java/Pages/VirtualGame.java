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

public class VirtualGame {
    WebDriver driver;
    util.ElementHelper elementHelper;
    WebDriverWait wait;
    String originalWindow;


    @FindBy(xpath = "//span[normalize-space()='Sanal Oyunlar']")
    WebElement virtualGameButton;

    @FindBy(xpath = "//a[@data-disciplineid='41']")
    WebElement playButton;


    @FindBy(xpath = "//span[@class='h-content-text']")
    WebElement errorMessage;

    public VirtualGame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver, this);
        this.originalWindow=driver.getWindowHandle();
    }

    public void navigateToVirtualGamePage() {
         elementHelper.click(virtualGameButton);
    }

    public void clickOnTheGameButton() {
        elementHelper.click(playButton);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void checkUnsuccessfulPlay(String message) {
        elementHelper.checkVisible(errorMessage);
        Assert.assertEquals(errorMessage.getText(),message,"Expected to see" + message + "we see" + errorMessage.getText());
    }
}

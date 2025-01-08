package util;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GamePageHelper {

    @FindBy(xpath = "//span[normalize-space()='Milli Piyango']")
    private WebElement milliPiyangoPage;

    @FindBy(xpath = "//a[normalize-space()='Sayisal Loto']")
    private WebElement sayisalLotoPage;

    @FindBy(xpath = "//a[normalize-space()='Super Loto']")
    private WebElement superLotoPage;

    @FindBy(xpath = "//a[normalize-space()='On Numara']")
    private WebElement onNumaraPage;

    @FindBy(xpath = "//a[normalize-space()='Sans Topu']")
    private WebElement sansTopuPage;


    public GamePageHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public WebElement getGamePage(WebDriver driver, String game){
        switch (game){
            case "Milli Piyango": return milliPiyangoPage;
            case "Sayisal Loto" : return sayisalLotoPage;
            case "Super Loto"   : return superLotoPage;
            case "On Numara"    : return onNumaraPage;
            case "Sans Topu"    : return sansTopuPage;
            default: throw new IllegalArgumentException("Invalid game page: " + game);
        }
    }
}

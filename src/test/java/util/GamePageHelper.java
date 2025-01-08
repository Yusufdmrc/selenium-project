package util;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GamePageHelper {

    @FindBy(xpath = "//a[normalize-space()='Sayisal Loto']")
    private WebElement sayisalLotoPage;

    @FindBy(xpath = "//a[normalize-space()='Super Loto']")
    private WebElement superLotoPage;

    @FindBy(xpath = "//a[normalize-space()='On Numara']")
    private WebElement onNumaraPage;

    @FindBy(xpath = "//a[normalize-space()='Sans Topu']")
    private WebElement sansTopuPage;

    @FindBy(id="sayisalLotoCardFrame")
    private WebElement sayisalLotoIFrameId;

    @FindBy(id="superLotoCardFrame")
    private WebElement superLotoIFrameId;

    @FindBy(id="onnumaraCardFrame")
    private WebElement onNumaraIFrameId;

    @FindBy(id="sansTopuCardFrame")
    private WebElement sansTopuIFrameId;

    @FindBy(css = "div.flashIcon")
    WebElement onNumaraRandomButton;

    @FindBy(css = "div.flash span.textIconBtn")
    WebElement sansTopuRandomButton;

    @FindBy(xpath = "//div[@class='flash']")
    WebElement superLotoRandomButton;

    @FindBy(css = "div[class='flash'] span[class='textIconBtn']")
    WebElement sayisalLotoRandomButton;


    public GamePageHelper(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public WebElement getGamePage(WebDriver driver, String game){
        switch (game){
            case "Sayisal Loto" : return sayisalLotoPage;
            case "Super Loto"   : return superLotoPage;
            case "On Numara"    : return onNumaraPage;
            case "Sans Topu"    : return sansTopuPage;
            default: throw new IllegalArgumentException("Invalid game page: " + game);
        }
    }

    public WebElement getGameFrame(WebDriver driver,String game){
        switch (game){
            case "Sayisal Loto"  : return sayisalLotoIFrameId;
            case "Super Loto"    : return superLotoIFrameId;
            case "On Numara"     : return onNumaraIFrameId;
            case "Sans Topu"     : return sansTopuIFrameId;
            default:throw new IllegalArgumentException("Invalid frame:" + game);
        }
    }

    public WebElement getGameRandomButton(WebDriver driver,String game){
        switch (game){
            case "Sayisal Loto" : return sayisalLotoRandomButton;
            case "Super Loto"   : return superLotoRandomButton;
            case "On Numara"    : return onNumaraRandomButton;
            case "Sans Topu"    : return sansTopuRandomButton;
            default:throw new IllegalArgumentException("Invalid random button:" +game);
        }
    }
}

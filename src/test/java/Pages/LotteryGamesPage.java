package Pages;

import io.cucumber.java.Scenario;
import io.cucumber.java.it.Ma;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import util.ConfigReader;
import util.ElementHelper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

import static reporting.Logging.writeConsoleLog;
import static util.DataProcess.getCurrentTime;

public class LotteryGamesPage {
    WebDriver driver;
    ElementHelper elementHelper;
    WebDriverWait wait;
    Scenario scenario;



    @FindBy(xpath = "//button[.='Sen Seç']")
    WebElement senSec;
    @FindBy(xpath = "//button[.='Sistem Oyunu']")
    WebElement sistemOyunu;
    @FindBy(xpath = "//button[text()='SüperStar']")
    WebElement superStarTab;
    @FindBy(xpath = "//button[text()='1. Bölüm']")
    WebElement snsStandardKeyPad;
    @FindBy(xpath = "//button[text()='2. Bölüm']")
    WebElement snsJokerKeyPad;
    @FindBy(xpath = "//button[text()='Çılgın Sayısal Loto']")
    WebElement standardNumberTab;
    @FindBy(xpath = "//input[@class='grc2-mark grc2-input-0 grc2-setByUser']")
    WebElement firstInputStandardNumber;
    @FindBy(xpath = "//button[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') ='tamam']")
    WebElement tamamBtn;
    @FindBy(xpath = "//div[@class='icon-close']")
    WebElement closeBtn;
    @FindBy(xpath = "//div[@class='buttonTooltip']//button[.='Kaydet']")
    WebElement saveBtn;
    @FindBy(xpath = "//button[contains(@class,'flashNumberBtn')]")
    WebElement randomButton;
    @FindBy(xpath="(//input[contains(@class,'grc2-input-0')])[1]")
    WebElement firstLine;
    @FindBy(xpath = "//div[@class='prizeValue']")
    WebElement ticketAmountBeforeBuy;
    @FindBy(xpath = "//button[.='ONAYLA']")
    WebElement confirmButton;
    @FindBy(xpath = "//button[contains(@class,'betBtn') and text()='SATIN AL']")
    WebElement buyBtn;
    @FindBy(xpath = "//button[.='Yeniden Oyna]")
    WebElement playAgainBtn;
    @FindBy(xpath = "(//button[@class='grc2-aggiungi-input-vuoto'])[2]")
    WebElement superstarPlusIcon;
    @FindBy(css = "div[aria-label='Next slide']")
    WebElement receiptNextButton;
    @FindBy(xpath = "//span[@class='none']")
    WebElement drawNumberBeforeSell;
    @FindBy(xpath = "//button[contains(@id,'btn_checkwinbox')]")
    WebElement checkBtn;
    @FindBy(xpath = "//a[contains(@href,'sonuclar')][contains(@class,'gtm-lotonavigator')]")
    WebElement resultsButton;
    @FindBy(id = "draw-year")
    WebElement drawResultOpenYearListArrow;
    @FindBy(id = "draw-month")
    WebElement drawResultOpenMonthListArrow;
    @FindBy(xpath = "(//select[@id=\"draw-month\"]/option)")
    List<WebElement> drawResultMonthList;
    @FindBy(xpath = "(//select[@id=\"draw-year\"]/option)")
    List<WebElement> drawResultYearList;
    @FindBy(xpath = "//input[contains(@id,'txt_checkwin')]")
    WebElement inputFieldCheckWinBox;
    @FindBy(xpath = "//div[@class='check-wins-modal' and not(@style='display: none;')]//div[contains(@class,'check-modal-content')]")
    WebElement modalCheckWinBox;
    @FindBy(xpath = "//div[@class='check-wins-modal' and not(@style='display: none;')]//div[contains(@class,'row categories')]")
    WebElement rowCategoryNo;
    @FindBy(xpath = "//div[@class='check-wins-modal' and not(@style='display: none;')]//div[contains(@class,'modal-body-content win')]/p")
    WebElement statusGame;
    @FindBy(css = "input[name='search']")
    WebElement filterSearchInput;
    @FindBy(xpath = "//div[@class=\"dr-nr\"]")
    WebElement lastPlayedCompetition;
    @FindBy(xpath = "//button[contains(@class,'draws-submit')]")
    WebElement filterBtn;
    @FindBy(xpath = "//div[contains(@class,'noresultdraws')]")
    WebElement noResultsMessage;
    @FindBy(xpath = "//div[@loto=\"ONNUMARA\"]")
    WebElement onNumaraLogo;
    @FindBy(xpath = "//div[@class=\"date_info\"]")
    WebElement dateAndTime;
    @FindBy(xpath = "//div[@class=\"number-onnumara\"]")
    List<WebElement> displayedCombinationNo;
    @FindBy(xpath = "//div[@class='stato-label' and text()='Çekiliş için bekleniyor']")
    WebElement receiptWaitingForDrawTitle;
    @FindBy(xpath = "//span[@class='drawInfoBox' and text()='Çekiliş no.']")
    WebElement receiptDrawNoTitle;
    @FindBy(xpath = "//div[@class='label' and text()='SAAT']")
    WebElement receiptHourTitle;
    @FindBy(xpath = "//div[@class='label' and text()='DAKİKA']")
    WebElement receiptMinuteTitle;
    @FindBy(xpath = "//span[@class='timeInfoBox']")
    WebElement receiptDateTitle;
    @FindBy(xpath = "//*[text()='Numaraların']")
    WebElement receiptYourNumbersTitle;
    @FindBy(xpath = "//*[text()='Seçtiğin Numaralar']")
    WebElement receiptChoosedNumbersTitle;
    @FindBy(xpath = "//div[@class='header-title' and text()='Bilet Detayları']")
    WebElement receiptTicketDetailTitle;
    @FindBy(xpath = "//button[@class='button-blue' and text()='Yeniden Oyna']")
    WebElement receiptReplayButton;
    @FindBy(xpath = "//*[text()='Detaylar']")
    WebElement receiptDetailDetailsTitle;
    @FindBy(xpath = "//p[contains(text(),'Şu anda işlemini')]")
    WebElement sellTicketErrorPopup;
    @FindBy(xpath = "//div[@loto='SAYISAL']/img")
    WebElement filterResultSayisalLogo;
    @FindBy(xpath = "//div[@loto='SUPERLOTO']/img")
    WebElement filterResultSuperLogo;
    @FindBy(xpath = "//div[@loto='SANSTOPU']/img")
    WebElement filterResultSansTopuLogo;
    @FindBy(xpath = "//div[@class='noresultdraws']/span")
    WebElement filterResultNoResultElement;
    @FindBy(xpath = "(//span[@class='draw_date'])[1]")
    WebElement filterResultDate;
    @FindBy(xpath = "(//span[@class='draw_time'][contains(text(),'-')])[1]/following-sibling::span")
    WebElement filterResultTime;
    @FindBy(xpath = "(//h3[@class='drawn date_month h-mb-0'])[1]")
    WebElement filterResultDrawNumberTitle;
    @FindBy(xpath = "(//h3[@class='draw_nr h-mb-0'])[1]")
    WebElement filterResultDrawNumber;
    @FindBy(xpath = "//input[@value='Favorim1']")
    WebElement defaultFavoriteName;
    @FindBy(css = ".fav-name")
    WebElement sylFavoriteTitle;
    @FindBy(css = ".titleFavourite")
    WebElement splFavoriteTitle;
    @FindBy(xpath = "//p[contains(text(),'Favori kombinasyonun başarı ile kaydedildi')]")
    WebElement splFavoritesSavedText;
    @FindBy(css = "button.grc2-btn.green")
    WebElement splConfirmToSaveButton;
    @FindBy(xpath = "(//div[@class='numbers'])[1]/div")
    List<WebElement> filterResultSylNumberList;
    @FindBy(xpath = "(//div[@class='numbers-magenta'])[1]/div")
    List<WebElement> filterResultSnsNumberList;
    @FindBy(css = "button.confirm")
    WebElement sylImportFavoritesButton;
    @FindBy(css = "button.chosse ")
    WebElement splImportFavoritesButton;
    @FindBy(xpath = "(//div[@class='modal-head'])[2]//div[contains(text(),'Kazançlarını kontrol et!')]")
    WebElement checkYourWinTitle;
    @FindBy(xpath = "(//div[@class='modal-body-content']/p[contains(text(),'Bu makbuz kodu için veri bulunmuyor')])[2]")
    WebElement noDataForTicketDesc;
    @FindBy(xpath = "(//div[@class='modal-head'])[2]//button[@class='close']")
    WebElement ticketResultCloseButton;
    @FindBy(xpath = "(//div[@class='modal-body-content diff-case']/img[@alt='Bu bilete ikramiye isabet etmedi.'])[2]")
    WebElement ticketResultLostImage;
    @FindBy(xpath = "(//div[@class='modal-body-header']//img[@src='/content/dam/sisalsans/logo/on-numara-cw.svg'])[2]")
    WebElement ticketResultNmrLogo;
    @FindBy(xpath = "(//div[@class='modal-body-content']/p[contains(text(),'Biletinin önünde bulunan')])[2]")
    WebElement enterTicketNumberDesc;
    @FindBy(xpath = "(//button[@class='btn-default btn-bg-primary play-again' and contains(text(),'TEKRAR DENE')])[2]")
    WebElement retryButton;
    @FindBy(xpath = "(//div[@class='modal-body-content diff-case']/p[contains(text(),'Bu bilete ikramiye isabet etmedi.')])[2]")
    WebElement noWinDesc;
    @FindBy(xpath = "(//div[contains(text(),'Bilet Kodu')])[4]")
    WebElement noWinTicketCodeTitle;
    @FindBy(css = "div.date")
    WebElement receiptDateText;
    @FindBy(css = "div.price")
    WebElement receiptPriceText;
    @FindBy(xpath = "//p[contains(text(),'5.000 TL üzeri bir bilet')]")
    WebElement overFiveThousandPopup;
    @FindBy(xpath = "//div[@class='loto-numbers red-loto']/div")
    List<WebElement> filterResultDetailSylNumberList;
    @FindBy(xpath = "//div[@class='winning-list']/div[@class='row no-gutters data-light']/div[@class='col-4 text-left']")
    List<WebElement> ticketDetailCategoryList;
    @FindBy(xpath = "//div[@class='winning-list']/div[@class='row no-gutters data-light']/div[@class='col-4 text-right winning-price mp-price-detail']")
    List<WebElement> ticketDetailPrizeList;
    @FindBy(xpath = "(//div[@class='numbers-purple'])[1]/div")
    List<WebElement> filterResultSplNumberList;
    @FindBy(xpath = "//div[@class='loto-numbers orange-loto']/div")
    List<WebElement> filterResultDetailSplNumberList;
    @FindBy(xpath = "//div[@id=\"numberpanel-0-0\"]/ul/li/input")
    List<WebElement> multipleStandardNumberList;
    @FindBy(xpath = "//div[@id=\"numberpanel-0-1\"]/ul/li/input")
    List<WebElement> multipleSuperstarNumberList;
    @FindBy(xpath = "//li//input")
    List<WebElement> numericGamesInputNumberList;
    @FindBy(xpath = "//div[contains(@class,'numero-')]")
    List<WebElement> sylAddedFavoritesNumberList;
    @FindBy(xpath = "//div[@class='boxnumber']/div")
    List<WebElement> splAddedFavoritesNumberList;

    public static String schedinaNo = "//button[.='%d']";
    private static final String NoWinTicketCodeText = "//div[@class='col text-right' and contains(text(),'%s')]";
    private static final String NoWinTicketContains = "//span[contains(text(),'%s')]";
    private static final String snsTicketAmountBeforeBuy = "//div[contains(text(),'%s')]";
    private static final By receiptDrawNo = By.xpath("//span[@class='drawInfoBox' and text()='Çekiliş no.']");
    private static final By DrawIncreaseButton = By.cssSelector(".plusBtn");
    public static By cancelBtn = By.xpath("//div[@class='content']//button[@class='cancel']");
    public static By deleteBtnSuperLoto = By.xpath("//button[@class='deleteRed']");
    public static By favoriteIcon = By.xpath("(//button[contains(@class,'favouriteIcon')])[1]");
    public static By deleteBtn = By.xpath("//button[@class='deleteBlue']");
    public static By savedFavoriteIcon = By.cssSelector("button.favouriteIcon.favouriteIcon.savedFavourite");
    private static final By favoriteNumberDetailOpenButton = By.cssSelector("div.icona-espandi ");
    public static By favoritesBtn = By.xpath("//button[@class='favouritesBtn']");
    public static By tamamTest = By.xpath("//button[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') ='tamam']");
    public static By titleInput = By.xpath("//input[@class='titleInput']");
    private static final By receiptSimpleStandardNumberList = By.xpath("//div[contains(@class,'gruppnumero')][1]/div");
    private static final By receiptSimpleSuperstarNumberList = By.xpath("//div[contains(@class,'gruppnumero-special')][1]/div");
    private static final By receiptMultipleStandardNumberList= By.xpath("//div[contains(@class,'gruppnumero-multipla')][1]/div");
    private static final By receiptMultipleSuperstarNumberList = By.xpath("//div[contains(@class,'gruppnumero-multipla')][2]/div[@class='numero-speciale']");
    private static final By receiptMultipleJokerNumberList = By.xpath("//div[contains(@class,'numero-special')]");
    public static final By OneTrustCloseButton = By.cssSelector("button[class*='onetrust-close-btn-ui']");
    public static String codeOfGameElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Oyun kodu')]//div[@class='%sspecific']";
    public static String dateOfTicketElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Tarih')]//div[@class='%sspecific']";
    public static String noAccountElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Hesap Bilgisi')]//div[@class='%sspecific']";
    public static String serialNoTicketElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Seri numarası')]//div[@class='%sspecific']";
    public static String numberOfCombinationsElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Kolon sayısı')]//div[@class='%sspecific']";
    public static String gameNumberElement = "//div[contains(@class,'%snameSpecific') and  contains(.,'Oyun No')]//div[@class='%sspecific']";
    private static final String filterYear = "//option[@value='%s']";
    private static final String FilterResultDetailButton = "//a[@data-draw-numb='%s']";
    private static final String nThRow = "#pannello-%d";
    private static final String nthTrashIcon = "//li[@id='pannello-%d']//button[@class='deleteBlue']";
    private static final String containsText = "//*[contains(text(),'%s')]";
    private static final String[] expectedMonths = {"Ay", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
    private static final String[] expectedYears = {"Yıl","2025","2024", "2023", "2022", "2021", "2020"};
    private static final String[] expectedFilterResultSylNumberList = {"1", "2", "3", "4", "5", "6", "7", "14"};
    private static final String[] expectedFilterResultSplNumberList = {"1", "2", "3", "4", "5", "6"};
    private static final String[] expectedPreProdFilterResultSnsNumberList = {"6", "11", "13", "25", "33", "13"};
    private static final String[] expectedFilterResultSnsNumberList = {"1", "2", "3", "4", "5", "1"};
    private static final String[] expectedFilterResultSylCategoryList = {"6", "5+1 (Joker) *", "5", "4", "3", "2", "6+SüperStar", "5+1 (Joker) +SüperStar","5+SüperStar", "4+SüperStar", "3+SüperStar", "2+SüperStar", "1+SüperStar", "0+SüperStar"};
    private static final String[] expectedFilterResultSplCategoryList = {"6", "5", "4", "3", "2"};
    private static final String[] expectedFilterResultSnsCategoryList = {"5+1", "5", "4+1", "4", "3+1", "3", "2+1", "1+1", "0+1",};
    private static final String[] expectedFilterResultSylPrizeList = {"500.000 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺","0 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺", "0 ₺"};
    private static final String[] expectedPreProdFilterResultSylPrizeList = {"40.000 ₺", "4,15 ₺", "11.000 ₺", "10,25 ₺", "8,55 ₺", "23,1 ₺","0 ₺", "0 ₺", "0 ₺", "0 ₺", "855 ₺", "500 ₺", "0 ₺", "0 ₺"};
    private static final String[] expectedFilterResultSplPrizeList = {"27.715,68 ₺", "0 ₺", "0 ₺", "0 ₺", "12,45 ₺"};
    private static final String[] expectedPreProdFilterResultSplPrizeList = {"70,82 ₺", "0 ₺", "0 ₺", "67,3 ₺", "10,05 ₺"};
    private static final String[] expectedFilterResultSnsPrizeList = {"1.903,75 ₺", "585,75 ₺", "48,8 ₺", "732,2 ₺", "41,8 ₺", "12,7 ₺", "30,1 ₺", "45,35 ₺", "18,5 ₺",};
    private static final String[] expectedPreProdFilterResultSnsPrizeList = {"2.705.101,75 ₺", "8.347,75 ₺", "723 ₺", "90,35 ₺", "47,3 ₺", "15,9 ₺", "21,4 ₺", "11,5 ₺", "12,3 ₺",};
    private static final String[] expectedFilterResultDetailSylElementText = {"SüperStar Kazanan Kategoriler", "Kazanan Numaralar", "Çekiliş no:",
            "11 Ocak 2024 20:30 Sayısal Loto Sonuçları", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı", "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE"};
    private static final String[] expectedPreProdFilterResultDetailSylElementText = {"SüperStar Kazanan Kategoriler", "Kazanan Numaralar", "Çekiliş no:",
            "11 Ocak 2024 15:06 Sayısal Loto Sonuçları", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı", "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE"};
    private static final String[] expectedFilterResultDetailSplElementText = {"Kazanan Numaralar", "Çekiliş no:", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı",
            "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE", "12 Ocak 2024 20:30 Süper Loto Sonuçları"};
    private static final String[] expectedPreProdFilterResultDetailSplElementText = {"Kazanan Numaralar", "Çekiliş no:", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı",
            "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE", "15 Ocak 2024 18:30"};
    private static final String[] expectedFilterResultDetailSnsElementText = {"Kazanan Numaralar", "Çekiliş no:", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı",
            "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE","28 Mart 2025 13:43 Şans Topu Sonuçları"};
    private static final String[] expectedPreProdFilterResultDetailSnsElementText = {"07 Şubat 2024 17:00 Şans Topu Sonuçları", "Kazanan Numaralar", "Çekiliş no:", "Kazanan Kategoriler", "Kazanan Kişi Sayısı", "İkramiye Tutarı",
            "ÇEKİLİŞE KALAN" , "Hemen oyna", "BÜYÜK İKRAMİYE"};

    Map<String,String> authenticationDetails = new HashMap<>();
    public static String accountCode, portalSessionToken;
    public static String postScriptLogin = "window.postMessage({esito: \"0\",tipoOperazione: \"27\",message: { credenziali:{ conto: '%s', token: '%s' }}})";

    private static final ArrayList<String> receiptDetailMenuTitleList;

    static {
        receiptDetailMenuTitleList = new ArrayList<>(Arrays.asList("Oyun", "Oyun kodu", "Tarih", "Hesap Bilgisi", "Seri numarası", "Kolon sayısı", "Oyun No"));
    }
    private static final String receiptDetailMenuTitles = "//div[contains(@class,'nameSpecific')][%s]";
    private static final String NoResultDrawNumber = "999";
    private static final String NoResultDrawResultMessage = "Sonuç bulunamadı";


    public LotteryGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
        this.elementHelper = new ElementHelper(driver);
        PageFactory.initElements(driver,this);

    }

    public void navigateToGameType(String gameType) {
        WebElement elementToClick=getGameTypeElement(gameType);
        try{
            elementHelper.click(elementToClick);
        }catch (Exception e){
            scenario.log("Failed to navigate to the " + gameType + " tab: " + e.getMessage());
            throw e;
        }
    }

    private WebElement getGameTypeElement(String gameType){
        switch (gameType){
            case "multiple":
            case "SistemOyunu":
               return sistemOyunu;
            case "simple":
            case "SenSec":
                return senSec;
            default:
                throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
    }

    public void enterRowByRandomButton(int clickCount) {
        if(elementHelper.isNotVisible(randomButton)) {
            scenario.log("Random button is not visible on the page.");
            throw new IllegalStateException("Random button is not visible on the page.");
        }
        for (int i=1; i<=clickCount;i++){
            elementHelper.pause(1);
            elementHelper.click(randomButton);
        }
    }

    public void buyTicket() {
        boolean ticketBoughtWithSuccess = false;
        int i= 0;
        while (i<5){
            writeConsoleLog("the try count is : " + i);
            if (elementHelper.isVisible(buyBtn)){
                clickBuyButton();
            }
            if (elementHelper.isVisible(overFiveThousandPopup)){
                clickConfirmButton();
            }
            if (elementHelper.isVisible(playAgainBtn)){
                ticketBoughtWithSuccess = true;
                break;
            }
            if (elementHelper.isVisible(confirmButton)){
                clickConfirmButton();
            }
            i++;
        }
        Assert.assertTrue(ticketBoughtWithSuccess,"TICKET WAS BOUGHT WITH SUCCESS AFTER"+ i + "TRIES");
    }

    private void clickBuyButton() {
        elementHelper.click(buyBtn);
    }
    private void clickConfirmButton() {
        elementHelper.click(overFiveThousandPopup);
    }

    public void verifyReceiptElements(int drawNumber, int columnNumber, int standartNumber, int superstarNumber) {
        verifyReceiptTitleBox(drawNumber,columnNumber,scenario);
        verifyReceiptInfoBox();
        verifyReceiptBodyBox(drawNumber,standartNumber,superstarNumber,scenario);
        verifyReceiptDetailBox(columnNumber,scenario);
    }

    private void verifyReceiptTitleBox(int drawNumber, int columnNumber, Scenario scenario) {
        elementHelper.checkVisible(receiptDateText);
        Assert.assertTrue(receiptDateText.getText().contains(getDate()));
        verifyTimePart(receiptDateText.getText());
        Assert.assertTrue(receiptPriceText.getText().equals(getTicketPrice(drawNumber, columnNumber, scenario) + " TL"), "receipt price text displays correct");
        elementHelper.checkVisible(receiptTicketDetailTitle);
    }

    private void verifyReceiptInfoBox() {
        elementHelper.checkVisible(receiptWaitingForDrawTitle);
        List<Map.Entry<WebElement,String>> receiptInfoBoxElementsList= Arrays.asList(
                Map.entry(receiptDrawNoTitle,"Çekiliş no. title"),
                Map.entry(receiptHourTitle,"SAAT title"),
                Map.entry(receiptMinuteTitle,"DAKİKA title"),
                Map.entry(receiptDateTitle,"receipt date title")
        );
        Assert.assertTrue(!receiptInfoBoxElementsList.isEmpty(), "Receipt info box elements list is empty");
        verifyNextDrawDateTime(receiptDateTitle.getText());
    }
    private void verifyReceiptBodyBox(int drawNumber, int standartNumber, int superstarNumber, Scenario scenario) {
        elementHelper.checkNotVisible(getReceiptBodyTitleSelector(scenario));
        verifyPlayednumberCount(drawNumber,standartNumber,superstarNumber,scenario);
    }

    private void verifyReceiptDetailBox(int columnNumber, Scenario scenario) {
        for(int i=1;i<8;i++){
            if(i==1)
                Assert.assertTrue(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText().contains(getGameType(scenario)),getGameType(scenario) + " game type info exists on receipt detail");
            if(i==3){
                Assert.assertTrue(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText().contains(getDate()), getDate() + " date info exists on receipt detail ");
                verifyTimePart(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText());
            }
            if(i==4)
                Assert.assertTrue(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText().contains(getAccountNo()), getAccountNo() + " account no info exists on receipt detail ");
            if(i==5){
                String text = driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText();
                verifySerialNoFormat(text);
            }
            if(i==6)
                Assert.assertTrue(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText().contains(columnNumber+" Kolon"), columnNumber + " column number  info exists on receipt detail ");

            elementHelper.checkVisible(receiptDetailDetailsTitle);
            writeConsoleLog(i+"th menu text is :" + driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText());
            Assert.assertTrue(driver.findElement(By.xpath(String.format(receiptDetailMenuTitles,i))).getText().contains(receiptDetailMenuTitleList.get(i-1)),receiptDetailMenuTitleList.get(i-1)+" displays correct on receipt detail ");
            elementHelper.checkVisible(receiptReplayButton);

        }
    }

    private void verifySerialNoFormat(String text) {
        // Extract the serial number from the text
        String[] parts = text.split("\n");
        if (parts.length < 2) {
            throw new AssertionError("Invalid text format: " + text);
        }
        String serialNo = parts[1].trim();

        // Check if the serial number length is valid
        if (serialNo.length() != 27) {
            throw new AssertionError("Serial no length is invalid: " + serialNo);
        }

        // Get the current year's last two digits
        String currentYearLastTwoDigits = String.valueOf(Year.now().getValue()).substring(2);

        // Check if the serial number starts with the current year's last two digits
        if (!serialNo.startsWith(currentYearLastTwoDigits)) {
            throw new AssertionError("Serial no does not start with the current year's last two digits: " + serialNo);
        }

        // Check if the serial number contains only alphanumeric characters
        if (!serialNo.matches("[a-zA-Z0-9]+")) {
            throw new AssertionError("Serial no contains invalid characters: " + serialNo);
        }

        System.out.println("Serial no verification passed: " + serialNo);
    }

    private String getAccountNo() {
        return ConfigReader.get("account.no");
    }

    private String getGameType(Scenario scenario) {
        return scenario.getName().contains("simple") ? "Sen Seç" : "Sistem Oyunu";
    }

    private void verifyPlayednumberCount(int drawNumber, int standartNumber, int superstarNumber, Scenario scenario) {
        if (scenario.getName().contains("Sayisal")) {
            if (scenario.getName().contains("simple")) {
                Assert.assertEquals(driver.findElements(receiptSimpleStandardNumberList).size(), standartNumber * drawNumber, "simple play played standard number count is not correct");
                Assert.assertEquals(driver.findElements(receiptSimpleSuperstarNumberList).size(), superstarNumber * 2 * drawNumber, "simple play played superstar number count is not correct");
            } else {
                Assert.assertEquals(driver.findElements(receiptMultipleStandardNumberList).size(), standartNumber * drawNumber, "multiple play played standard number count is not correct");
                Assert.assertEquals(driver.findElements(receiptMultipleSuperstarNumberList).size(), superstarNumber * drawNumber, "multiple play played superstar number count is not correct");
            }
        } else {
            if (scenario.getName().contains("Sans Topu")) {
                if (scenario.getName().contains("simple")) {
                    Assert.assertEquals(driver.findElements(receiptSimpleSuperstarNumberList).size(), superstarNumber * drawNumber, "simple play played joker number count is not correct");
                } else if (scenario.getName().contains("multiple")) {
                    Assert.assertEquals(driver.findElements(receiptMultipleJokerNumberList).size(), superstarNumber * drawNumber, "multiple play played joker number count is not correct");
                }
            }
            Assert.assertEquals(driver.findElements(receiptSimpleStandardNumberList).size(), standartNumber * drawNumber, "simple play played standard number count is not correct");
        }
    }

    private WebElement getReceiptBodyTitleSelector(Scenario scenario) {
        return scenario.getName().contains("Super Loto") ? receiptChoosedNumbersTitle : receiptYourNumbersTitle;
    }

    private void verifyNextDrawDateTime(String text) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        try {
            LocalDateTime parsedDateTime= LocalDateTime.parse(text,formatter);
            LocalDateTime now=LocalDateTime.now();
            if (!parsedDateTime.isAfter(now)){
                throw new AssertionError("The date-time is not in the future: " + text);
            }
        }catch (DateTimeParseException e){
            throw new AssertionError("The text format is invalid: " + text);
        }
    }

    private void verifyTimePart(String elementText) {
        // Extract the time part (e.g., "12:04") from the element text
        String timePart = elementText.split("-")[1].trim();

        // Parse the extracted time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime extractedTime = LocalTime.parse(timePart, timeFormatter);

        // Get the current time in the system's default time zone
        ZoneId userTimeZone = ZoneId.systemDefault();
        LocalTime currentTime = LocalTime.now(userTimeZone);

        // Calculate the acceptable range (±1 minute)
        LocalTime lowerBound = currentTime.minusMinutes(1).withSecond(0).withNano(0);
        LocalTime upperBound = currentTime.plusMinutes(1).withSecond(59).withNano(999_999_999);

        // Adjust extracted time to include the full range of seconds and nanoseconds
        LocalTime extractedLowerBound = extractedTime.withSecond(0).withNano(0);
        LocalTime extractedUpperBound = extractedTime.withSecond(59).withNano(999_999_999);

        // Verify that the extracted time range overlaps with the acceptable range
        if (extractedUpperBound.isBefore(lowerBound) || extractedLowerBound.isAfter(upperBound)) {
            throw new AssertionError("Time verification failed. Extracted time: " + extractedTime +
                    " is not within the acceptable range: " + lowerBound + " - " + upperBound);
        }
    }

    private String getTicketPrice(int drawNumber, int columnNumber, Scenario scenario) {
        String ticketPrice=String.valueOf(drawNumber * getPricePerTicket(scenario)*columnNumber);
        return formatPrice(ticketPrice);
    }

    private String formatPrice(String price) {
        double doublePrice=Double.parseDouble(price);
        // Create a DecimalFormat with custom symbols
        DecimalFormatSymbols symbols=new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat=new DecimalFormat("#,##0.00",symbols);
        //Format the double value to a string
        return decimalFormat.format(doublePrice);
    }

    private double getPricePerTicket(Scenario scenario) {
        if (scenario.getName().contains("Super Loto")){
            return ConfigReader.getDouble("spl.ticket.price");
        }else if(scenario.getName().contains("Sans Topu")){
            return ConfigReader.getDouble("sns.ticket.price");
        }else{
            return ConfigReader.getDouble("syl.ticket.price");
        }
    }

    public String getDate() {
        String month=getCurrentTime("MM");
        String day=Integer.toString(Integer.parseInt(getCurrentTime("dd")));
        String year=getCurrentTime("yyyy");
        writeConsoleLog("month is:" + month);
        writeConsoleLog("day is:" + day);
        writeConsoleLog("year is:" + year);
        switch (month){
            case "01":
                month="Ocak";
                break;
            case "02":
                month="Şubat";
                break;
            case "03":
                month="Mart";
                break;
            case "04":
                month="Nisan";
                break;
            case "05":
                month="Mayıs";
                break;
            case "06":
                month="Haziran";
                break;
            case "07":
                month="Temmuz";
                break;
            case "08":
                month="Ağustos";
                break;
            case "09":
                month="Eylül";
                break;
            case "10":
                month="Ekim";
                break;
            case "11":
                month="Kasım";
                break;
            case "12":
                month="Aralık";
                break;
            default:
                writeConsoleLog("Please check the month value");
        }
        String date= day + " " + month + " " + year;
        writeConsoleLog("date is :" + date);
        return date;
    }

    public void verifyDetailsOfTicket(List<ConcurrentMap<String ,String>> listOfTickets,String gameName){
        String codeOfGame,numberOfCombinations,serialNoTicket,gameNumber,noAccount;
        long dateOfTicket;
        for (ConcurrentMap<String , String> ticket : listOfTickets){
            for(Map.Entry<String,String> detailsTicket : ticket.entrySet()) {
                JsonPath jsonPath = JsonPath.from(detailsTicket.getValue());
                codeOfGame = jsonPath.get("biglietto.identificativo");
                gameNumber = jsonPath.get("biglietto.codiceControllo");
                serialNoTicket = jsonPath.get("biglietto.sn");
                numberOfCombinations = jsonPath.get("biglietto.numeroCombinazioni");
                noAccount = jsonPath.get("biglietto.numeroContoGiocatore");
                dateOfTicket = jsonPath.get("biglietto.dataConvalida");

                Instant instant = Instant.ofEpochMilli(dateOfTicket);
                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
                DateTimeFormatter turkishFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - HH:mm", new Locale("tr", "TR"));
                String formatedDate = zonedDateTime.format(turkishFormatter);
                // Remove leading zero for days 1-9
                formatedDate = formatedDate.replaceFirst("^0", "");

                if (StringUtils.containsIgnoreCase(gameName, "Sayisal")) {
                    Assert.assertEquals(codeOfGame, getTextJs(By.xpath(String.format(codeOfGameElement,"LT-","LT-"))), "TICKET CODE FROM FE " + getTextJs(By.xpath(String.format(codeOfGameElement,"LT-","LT-"))) + " IS EQUAL TO BE: " + codeOfGame);
                    Assert.assertEquals(gameNumber, getTextJs(By.xpath(String.format(gameNumberElement,"LT-","LT-"))), "TICKET NO FROM FE " + getTextJs(By.xpath(String.format(gameNumberElement,"LT-","LT-"))) + " IS EQUAL TO BE: " + gameNumber);
                    Assert.assertEquals(serialNoTicket, getTextJs(By.xpath(String.format(serialNoTicketElement,"LT-","LT-"))), "TICKET SN FROM FE " + getTextJs(By.xpath(String.format(serialNoTicketElement,"LT-","LT-"))) + " IS EQUAL TO BE: " + serialNoTicket);
                    Assert.assertEquals(numberOfCombinations, StringUtils.getDigits(getTextJs(By.xpath(String.format(numberOfCombinationsElement,"LT-","LT-")))), "COMBINATIONS FROM FE " + StringUtils.getDigits(getTextJs(By.xpath(String.format(numberOfCombinationsElement,"LT-","LT-")))) + " IS EQUAL TO BE: " + numberOfCombinations);
                    Assert.assertEquals(formatedDate, getTextJs(By.xpath(String.format(dateOfTicketElement,"LT-","LT-"))), "TICKET DATE FROM FE " + getTextJs(By.xpath(String.format(dateOfTicketElement,"LT-","LT-"))) + " IS EQUAL TO BE: " + formatedDate);
                    Assert.assertEquals(noAccount, getTextJs(By.xpath(String.format(noAccountElement,"LT-","LT-"))), "ACCONTNO FROM FE " + getTextJs(By.xpath(String.format(noAccountElement,"LT-","LT-"))) + " IS EQUAL TO BE: " + noAccount);
                } else {
                    Assert.assertEquals(codeOfGame, getTextJs(By.xpath(String.format(codeOfGameElement,"",""))), "TICKET CODE FROM FE " + getTextJs(By.xpath(String.format(codeOfGameElement,"",""))) + " IS EQUAL TO BE: " + codeOfGame);
                    Assert.assertEquals(gameNumber, getTextJs(By.xpath(String.format(gameNumberElement,"",""))), "TICKET NO FROM FE " + getTextJs(By.xpath(String.format(gameNumberElement,"",""))) + " IS EQUAL TO BE: " + gameNumber);
                    Assert.assertEquals(serialNoTicket, getTextJs(By.xpath(String.format(serialNoTicketElement,"",""))), "TICKET SN FROM FE " + getTextJs(By.xpath(String.format(serialNoTicketElement,"",""))) + " IS EQUAL TO BE: " + serialNoTicket);
                    Assert.assertEquals(numberOfCombinations, StringUtils.getDigits(getTextJs(By.xpath(String.format(numberOfCombinationsElement,"","")))), "COMBINATIONS FROM FE " + StringUtils.getDigits(getTextJs(By.xpath(String.format(numberOfCombinationsElement,"","")))) + " IS EQUAL TO BE: " + numberOfCombinations);
                    Assert.assertEquals(formatedDate, getTextJs(By.xpath(String.format(dateOfTicketElement,"",""))), "TICKET DATE FROM FE " + getTextJs(By.xpath(String.format(dateOfTicketElement,"",""))) + " IS EQUAL TO BE: " + formatedDate);
                    Assert.assertEquals(noAccount, getTextJs(By.xpath(String.format(noAccountElement,"",""))), "ACCONTNO FROM FE " + getTextJs(By.xpath(String.format(noAccountElement,"",""))) + " IS EQUAL TO BE: " + noAccount);}
            }
        }
    }

    private String getTextJs(By by){
        WebElement element= driver.findElement(by);
        return getTextJs(element);
    }

    private String getTextJs(WebElement element){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element);
    }
}

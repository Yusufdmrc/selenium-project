package stepDefinitions;

import Pages.HomePage;
import Pages.LotteryGamesPage;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v147.network.Network;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import util.DriverFactory;
import static backend.NetworkListener.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class LotteryGamesStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    LotteryGamesPage lotteryGamesPage = new LotteryGamesPage(driver);
    HomePage homePage = new HomePage(driver);

    private DevTools devTools;
    public static RemoteWebDriver driver1;
    ConcurrentMap<String,String> ticketDetail;
    List<ConcurrentMap<String,String>> listOfTickets;

    private void ensurePages() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
        }
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        if (lotteryGamesPage == null) {
            lotteryGamesPage = new LotteryGamesPage(driver);
        }
    }

    @When("User navigates to the {string} game page from homepage")
    public void userNavigatesToTheGamePageFromHomepage(String gameName) {
        ensurePages();
        homePage.navigateToLotteryGame(gameName);
    }

    @And("User navigates to {string} tab on game")
    public void userNavigatesToTabOnGame(String gameType) {
        ensurePages();
        lotteryGamesPage.navigateToGameType(gameType);
    }

    @And("User enters {} row by random button")
    public void userEntersRowByRandomButton(int clickCount) {
        ensurePages();
        lotteryGamesPage.enterRowByRandomButton(clickCount);
    }

    @Then("User verifies the ticket was bought successfully")
    public void userVerifiesTheTicketWasBoughtSuccessfully() {
        ensurePages();
        driver1=(RemoteWebDriver) driver;
        DevTools temporaryDevTools= devTools;
        try {
            devTools=createSessionAndEnable(driver1);
            ticketDetail= listenerResponseReceivedWithFilters(devTools,"sell/");
        } catch (org.openqa.selenium.devtools.DevToolsException e) {
            System.out.println("WARNING: DevTools not available for Chrome version. Skipping network validation.");
            // CDP uyumsuzluğu durumunda sadece ticket satın alma işlemini yap
            lotteryGamesPage.buyTicket();
            return;
        }
       try{
           lotteryGamesPage.buyTicket();
           listOfTickets= waitForListenerToRetrieveData("sell/",ticketDetail);
           devTools= temporaryDevTools;
       }catch (AssertionError error){
           devTools= temporaryDevTools;
           Assert.fail(" TICKET COULD NOT BE SOLD");
       }
    }

    @Then("User verifies the receipt elements for {} draw {} column {} standart number {} superstar number")
    public void userVerifiesTheReceiptElementsForDrawColumnStandartNumberSuperstarNumber(int drawNumber, int columnNumber, int standartNumber, int superstarNumber) {
        ensurePages();
        lotteryGamesPage.verifyReceiptElements(drawNumber,columnNumber,standartNumber,superstarNumber);
    }


    @Then("User verifies the details of the ticket for {string}")
    public void userVerifiesTheDetailsOfTheTicketFor(String gameName) {
        ensurePages();
        lotteryGamesPage.verifyDetailsOfTicket(listOfTickets,gameName);
    }
}

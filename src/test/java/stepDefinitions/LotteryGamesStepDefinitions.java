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
import org.openqa.selenium.devtools.v130.network.Network;
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
    Scenario scenario;
    private DevTools devTools;
    public static RemoteWebDriver driver1;
    ConcurrentMap<String,String> ticketDetail;
    List<ConcurrentMap<String,String>> listOfTickets;

    @When("User navigates to the {string} game page from homepage")
    public void userNavigatesToTheGamePageFromHomepage(String gameName) {
        homePage.navigateToLotteryGame(gameName,scenario);
    }

    @And("User navigates to {string} tab on game")
    public void userNavigatesToTabOnGame(String gameType) {
      lotteryGamesPage.navigateToGameType(gameType,scenario);
    }

    @And("User enters {} row by random button")
    public void userEntersRowByRandomButton(int clickCount) {
        lotteryGamesPage.enterRowByRandomButton(clickCount,scenario);
    }

    @Then("User verifies the ticket was bought successfully")
    public void userVerifiesTheTicketWasBoughtSuccessfully() {
       DevTools temporaryDevTools= devTools;
       devTools=createSessionAndEnable(driver1);
       ticketDetail= listenerResponseReceivedWithFilters(devTools,"sell/");
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
        lotteryGamesPage.verifyReceiptElements(drawNumber,columnNumber,standartNumber,superstarNumber,scenario);
    }


    @Then("User verifies the details of the ticket for {string}")
    public void userVerifiesTheDetailsOfTheTicketFor(String gameName) {
        lotteryGamesPage.verifyDetailsOfTheTicket(listOfTickets,gameName);
    }
}

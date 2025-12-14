package stepDefinitions;

import Pages.HomePage;
import Pages.LotteryGamesPage;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import util.DriverFactory;

public class LotteryGamesStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    LotteryGamesPage lotteryGamesPage = new LotteryGamesPage(driver);
    HomePage homePage = new HomePage(driver);
    Scenario scenario;


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
    }
}

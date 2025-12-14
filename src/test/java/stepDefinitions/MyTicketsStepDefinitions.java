package stepDefinitions;

import Pages.AreaPrivatePage;
import Pages.GamingHistoryPage;
import Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class MyTicketsStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    GamingHistoryPage gamingHistoryPage =new GamingHistoryPage(driver);
    HomePage homePage = new HomePage(driver);
    AreaPrivatePage areaPrivatePage = new AreaPrivatePage(driver);

    @Given("User navigates to the Gaming History page")
    public void userNavigatesToTheGamingHistoryPage() {
        homePage.navigateToAreaPrivatePage();
        areaPrivatePage.navigateToTheGamingHistoryPage();
    }

    @When("The user filters by selecting the {string} {string} and {string}")
    public void theUserFiltersBySelectingTheAnd(String game, String status, String dateRange) {
        gamingHistoryPage.filterBySelecting(game,status,dateRange);
    }

    @When("User clicks to the detail button")
    public void userClicksToTheDetailButton() {
        gamingHistoryPage.clickDetailButton();
    }

    @Then("The user verifies the ticket detail {string} according to the selected  {string}")
    public void theUserConfirmsThatTheTicketDetailHasBeenSuccessfullyDisplayed(String message,String status) {
        gamingHistoryPage.validateTicketDetail(message,status);
    }

    @When("The user filters by selecting the {string}")
    public void theUserFiltersBySelectingThe(String game) {
        gamingHistoryPage.selectGameFilter(game);
    }

    @Then("User confirms successful filtering of game {string}")
    public void userConfirmsSuccessfulFilteringOfGame(String drawName) {
        gamingHistoryPage.confirmFilter(drawName);
    }
}

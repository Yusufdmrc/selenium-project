package stepDefinitions;

import Pages.MyTicketsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class MyTicketsStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    MyTicketsPage myTicketsPage=new MyTicketsPage(driver);

    @Given("User navigates to the Biletlerim page")
    public void userNavigatesToTheBiletlerimPage() {
        myTicketsPage.navigateToBiletlerimPage();
    }

    @When("The user filters by selecting the {string} {string} and {string}")
    public void theUserFiltersBySelectingTheAnd(String game, String status, String dateRange) {
        myTicketsPage.filterBySelecting(game,status,dateRange);
    }

    @When("User clicks to the detail button")
    public void userClicksToTheDetailButton() {
        myTicketsPage.clickDetailButton();
    }

    @Then("The user verifies the ticket detail {string} according to the selected  {string}")
    public void theUserConfirmsThatTheTicketDetailHasBeenSuccessfullyDisplayed(String message,String status) {
        myTicketsPage.validateTicketDetail(message,status);
    }

    @When("The user filters by selecting the {string}")
    public void theUserFiltersBySelectingThe(String game) {
        myTicketsPage.selectGameFilter(game);
    }

    @Then("User confirms successful filtering of game {string}")
    public void userConfirmsSuccessfulFilteringOfGame(String drawName) {
        myTicketsPage.confirmFilter(drawName);
    }
}

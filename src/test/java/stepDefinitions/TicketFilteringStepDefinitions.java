package stepDefinitions;

import Pages.TicketFilteringPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class TicketFilteringStepDefinitions {


    WebDriver driver = DriverFactory.getDriver();
    TicketFilteringPage ticketFilteringPage = new TicketFilteringPage(driver);


    @When("The user filters by selecting the On Numara game")
    public void theUserFiltersBySelectingTheOnNumaraGame() {
        ticketFilteringPage.selectOnNumaraGameFilter();
    }

    @Then("User confirms successful filtering of On Numara draws")
    public void userConfirmsSuccessfulFilteringOfOnNumaraDraws() {
        ticketFilteringPage.validateGameFilter();
    }
}

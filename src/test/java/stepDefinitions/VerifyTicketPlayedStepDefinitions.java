package stepDefinitions;

import Pages.VerifyTicketPlayedPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class VerifyTicketPlayedStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    VerifyTicketPlayedPage verifyTicketPlayedPage = new VerifyTicketPlayedPage(driver);

    @Given("User navigates to the {string} page")
    public void userNavigatesToTheOnNumaraPage(String game) {
        verifyTicketPlayedPage.navigateToOnNumaraPage(game);
    }

    @When("User buys game tickets with the random button")
    public void userBuysGameTicketsWithTheRandomButton() {
        verifyTicketPlayedPage.buyTicket();
    }

    @Then("User checks whether the ticket has been purchased")
    public void userChecksWhetherTheTicketHasBeenPurchased() {
        verifyTicketPlayedPage.checkTicket();
    }
}

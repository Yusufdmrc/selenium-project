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

    @When("User buys {string} tickets with the random button")
    public void userBuysGameTicketsWithTheRandomButton(String game) {
        verifyTicketPlayedPage.startNetworkMonitoring();
        verifyTicketPlayedPage.buyTicket(game);
    }

    @Then("User checks whether the ticket has been purchased")
    public void userChecksWhetherTheTicketHasBeenPurchased() {
        verifyTicketPlayedPage.checkTicket();
    }

    @Then("The user checks the accuracy of the numbers played")
    public void theUserChecksTheValidityOfTheNumbersPlayed() {
        verifyTicketPlayedPage.checkNumbersPlayed();
    }
}

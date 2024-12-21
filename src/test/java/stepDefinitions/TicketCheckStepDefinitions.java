package stepDefinitions;

import Pages.TicketCheckPage;
import Pages.VirtualGame;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class TicketCheckStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    TicketCheckPage ticketCheckPage = new TicketCheckPage(driver);

    @Given("User navigates to the On Numara page")
    public void userNavigatesToTheOnNumaraPage() {
        ticketCheckPage.navigateToOnNumaraPage();
    }

    @When("User buys On Numara tickets with the random button")
    public void userBuysOnNumaraTicketsWithTheRandomButton() {
        ticketCheckPage.buyTicket();
    }

    @Then("User checks whether the ticket has been purchased")
    public void userChecksWhetherTheTicketHasBeenPurchased() {
        ticketCheckPage.checkTicket();
    }
}

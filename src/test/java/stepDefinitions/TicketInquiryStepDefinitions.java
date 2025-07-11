package stepDefinitions;

import Pages.TicketInquiryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class TicketInquiryStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    TicketInquiryPage ticketInquiryPage = new TicketInquiryPage(driver);

    @Given("User goes to the Lotteries page")
    public void userGoesToTheLotteriesPage() {
       ticketInquiryPage.navigateToLotteryResults();
    }

    @When("User queries ticket by {string}")
    public void userQueriesTicketBy(String serialNumber) {
        ticketInquiryPage.inquireTicket(serialNumber);

    }

    @Then("The user checks if the ticket {string} has been successfully viewed")
    public void theUserChecksIfTheTicketHasBeenSuccessfullyViewed(String statusText) {
        ticketInquiryPage.checkTicketDetail(statusText);
    }
}

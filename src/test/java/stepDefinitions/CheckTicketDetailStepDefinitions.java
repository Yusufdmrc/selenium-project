package stepDefinitions;

import Pages.CheckTicketDetailPage;
import Pages.TicketFilteringPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class CheckTicketDetailStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    CheckTicketDetailPage checkTicketDetailPage=new CheckTicketDetailPage(driver);

    @When("The user filters by selecting the {string} status")
    public void theUserFiltersBySelectingTheStatus(String status) {
        checkTicketDetailPage.selectStatusFilter(status);
    }

    @When("User clicks to the detail button")
    public void userClicksToTheDetailButton() {
        checkTicketDetailPage.clickDetailButton();
    }

    @Then("The user verifies the ticket detail {string} according to the selected status")
    public void theUserConfirmsThatTheTicketDetailHasBeenSuccessfullyDisplayed(String message) {
        checkTicketDetailPage.validateTicketDetail(message);
    }
}

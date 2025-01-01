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

    @When("The user filters by selecting the Çekiliş Bekleyen status")
    public void theUserFiltersBySelectingTheÇekilişBekleyenStatus() {
        checkTicketDetailPage.selectDrawPendingFilter();
    }

    @When("User clicks to the detail button")
    public void userClicksToTheDetailButton() {
        checkTicketDetailPage.clickDetailButton();
    }

    @Then("The user confirms that the ticket detail has been successfully viewed")
    public void theUserConfirmsThatTheTicketDetailHasBeenSuccessfullyViewed() {
        checkTicketDetailPage.validateTicketDetail();
    }



}

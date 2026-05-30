package stepDefinitions;

import Pages.DrawResultsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class DrawResultsStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    DrawResultsPage drawResultsPage = new DrawResultsPage(driver);

    @Given("User navigates to the Draw Results page")
    public void userNavigatesToTheDrawResultsPage() {
        drawResultsPage.navigateToDrawResults();
    }

    @When("User filters results by {string}")
    public void userFiltersResultsBy(String game) {
        drawResultsPage.filterByGame(game);
    }

    @Then("User verifies that the results belong to {string}")
    public void userVerifiesThatTheResultsBelongTo(String game) {
        drawResultsPage.verifyResultsBelongToGame(game);
    }
}

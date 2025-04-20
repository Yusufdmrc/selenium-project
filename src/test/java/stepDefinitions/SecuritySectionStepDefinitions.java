package stepDefinitions;

import Pages.SecurityPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class SecuritySectionStepDefinitions {

    WebDriver driver= DriverFactory.getDriver();
    SecurityPage securityPage=new SecurityPage(driver);

    @Given("User navigates to the Security Section page")
    public void userNavigatesToTheSecuritySectionPage() {
        securityPage.navigateToSecurityPage();
    }

    @When("The user click to Select All button")
    public void theUserClickToSelectAllButton() {
        securityPage.clickSelectAllButton();
    }

    @When("The user clicks the delete button and deletes all data")
    public void theUserClicksTheDeleteButtonAndDeletesAllData() {
        securityPage.clickDeleteButton();
    }

    @Then("The user verifies that all sessions are deleted")
    public void theUserVerifiesThatAllSessionsAreDeleted() {
        securityPage.verifyDeletion();
    }
}

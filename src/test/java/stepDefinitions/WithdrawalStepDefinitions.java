package stepDefinitions;

import Pages.WithdrawalPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class WithdrawalStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    WithdrawalPage withdrawalPage = new WithdrawalPage(driver);

//    @Given("User is logged in with {string} and {string}")
//    public void userIsLoggedIn(String username, String password) {
//        withdrawalPage.login(username, password);
//    }

    @When("User navigates to the 'Para Çekme' page")
    public void userNavigatesToParaCekmePage() {
        withdrawalPage.navigateToWithDrawalPage();
    }

    @When("User adds a new IBAN with {string} and {string}")
    public void userAddsNewIban(String ibanNo, String shortName) {
        withdrawalPage.addNewIban(ibanNo, shortName);
    }

    @When("User withdraws {string}")
    public void userWithdraws(String price) {
        withdrawalPage.withdrawAmount(price);
    }

    @Then("Check Successful withdrawal")
    public void checkSuccessfulWithdrawal() {
        withdrawalPage.verifySuccessfulWithdrawal();
    }
}

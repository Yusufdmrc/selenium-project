package stepDefinitions;

import Pages.RegisterPage;
import Pages.WithdrawalPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class WithdrawalStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    WithdrawalPage withdrawalPage = new WithdrawalPage(driver);


    @When("User clicks the {string} button")
    public void userClicksTheHesabımButton() {
    }

    @And("User clicks the {string} from the left side menu")
    public void userClicksTheParaÇekmeFromTheLeftSideMenu() {
    }

    @And("User clicks {string} button")
    public void userClicksYeniEkleButton() {
    }

    @And("User enters {string} into the {string} field")
    public void userEntersIntoTheField(String value, String fieldName) {
        withdrawalPage.enterField(value,fieldName);
    }

    @And("User clicks the {string} button")
    public void userClickTheDOGRULAButton() {
    }

    @Then("Check Successful withdrawal")
    public void checkSuccessfulWithdrawal() {
    }
}

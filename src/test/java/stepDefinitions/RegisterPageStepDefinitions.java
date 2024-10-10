package stepDefinitions;

import Pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;
import util.ElementHelper;

public class RegisterPageStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);

    @When("User goes to the registration page")
    public void userIsOnTheRegistrationPage() {
        registerPage.clickRegisterButton();
    }

    @And("User enters {string} in the {string} field")
    public void userEntersInTheField(String value, String fieldName) {
       registerPage.enterField(value,fieldName);
    }

    @And("User clicks the ONAYLA button")
    public void userClicksTheButton() {
        registerPage.clickSubmitButton();
    }

    @And("User clicks the otpConfirm button")
    public void userClicksTheOtpConfirmButton() {
        registerPage.clickOtpConfirmButton();
    }

    @Then("Check successful register")
    public void checkSuccessfulRegister() {
        registerPage.checkSuccessful();
    }
}

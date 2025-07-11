package stepDefinitions;

import Pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;
import util.ElementHelper;

public class RegisterPageStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);

    @Given("User goes to the registration page")
    public void userIsOnTheRegistrationPage() {
        registerPage.clickRegisterButton();
    }

    @When("User completes the registration form with random data")
    public void userCompletesTheRegistrationFormWith() {
        registerPage.fillRegistrationForm();
        registerPage.clickSubmitButton();
    }

    @And("User completes the OTP form with {string}")
    public void userCompletesTheOTPFormWith(String otpNumber) {
        registerPage.enterOtpNumber(otpNumber);
        registerPage.clickOtpConfirmButton();
    }

    @Then("Check successful register")
    public void checkSuccessfulRegister() {
        registerPage.checkSuccessful();
    }
}

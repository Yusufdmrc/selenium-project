package stepDefinitions;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class LoginPageStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("User at home page")
    public void userAtHomePage() {
     }

    @When("Click member login button")
    public void clickMemberLoginButton() {
        loginPage.clickMemberLoginButton();
    }

    @And("Click login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @And("write {string} for username field")
    public void writeForUsernameField(String username) {
        loginPage.writeUsernameForUsernameField(username);
    }

    @And("write {string} for password field")
    public void writeForPasswordField(String password) {
        loginPage.writePasswordForPasswordField(password);
    }

    @Then("Check {string} message about credentials not valid")
    public void checkMessageAboutCredentialsNotValid(String message) {
        loginPage.dontCredentialValidErrorMessage(message);
    }

    @Then("Check Successful login")
    public void checkSuccessfulLogin() {
        loginPage.checkSuccessful();
    }

    @Then("Check unsuccessful login")
    public void checkUnsuccessfulLogin() {
     loginPage.checkUnsuccessful();
    }

}

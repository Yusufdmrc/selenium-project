package stepDefinitions;
import Pages.LoginPage;
import Pages.PasswordRetrievalPage;
import Pages.PasswordUpdatePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class LoginPageStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    PasswordRetrievalPage passwordRetrievalPage = new PasswordRetrievalPage(driver);
    PasswordUpdatePage passwordUpdatePage=new PasswordUpdatePage(driver);

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

    @And("User navigates to the Pasword Retrieval Page")
    public void userNavigatesToThePaswordRetrievalPage() {
        loginPage.navigateToPasswordRetrievalPage();
    }
    @Then("User verifies Password Retrieval form is not submittable when with {string} TC ID, {string} Birth Date, {string} email missing data")
    public void userVerifiesPasswordRetrievalFormIsNotSubmittableWhenWithTCIDBirthDateEmailMissingData(String tcID, String birthdate, String email) {
        passwordRetrievalPage.verifyPasswordRetrievalFormNotSubmittable(tcID, birthdate, email);
    }


    @And("User fills the Password Retrieval form with {string} {string} {string}")
    public void userFillsThePasswordRetrievalFormWith(String tcId, String birthDate, String phoneNumber) {
        passwordRetrievalPage.fillPasswordRetrievalForm(tcId, birthDate, phoneNumber);
    }

    @And("User enters and submit OTP for password update")
    public void userEntersAndSubmitOTPForPasswordUpdate() {
        passwordRetrievalPage.enterAndSubmitOtp();
    }

    @And("User changes the password with credentials {string} on Password Retrieval Screen and submit")
    public void userChangesThePasswordWithCredentialsOnPasswordRetrievalScreenAndSubmit(String password) {
        passwordUpdatePage.changeThePasswordAndSubmit(password);
    }

    @And("User navigates to the Login Page from Password Update Screen")
    public void userNavigatesToTheLoginPageFromPasswordUpdateScreen() {
        passwordUpdatePage.navigateToLoginPage();
    }

    @And("User logs into the app with \"{}")
    public void userLogsIntoTheAppWith(String tcId,String password) {
        passwordUpdatePage.loginToApp(tcId,password);
    }

    @Then("User verifies the login with {string} is successful")
    public void userVerifiesTheLoginWithIsSuccessful(String arg0, String arg1) {
        loginPage.checkSuccessful();
    }
}

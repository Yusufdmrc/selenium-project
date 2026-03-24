package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import util.ApiAuthHelper;
import util.ConfigReader;
import util.ScenarioContext;

public class ApiLoginStepDefinitions {

    /**
     * Resolves placeholder values like "correctTcID", "correctPassword", "correctEmail", etc.
     * from config.properties. "empty" becomes an empty string.
     */
    private String resolveValue(String value) {
        switch (value) {
            case "correctTcID":
                return ConfigReader.get("correct.tc.id");
            case "correctPassword":
                return ConfigReader.get("correct.password");
            case "correctAccountNo":
                return ConfigReader.get("correct.account.no");
            case "correctEmail":
                return ConfigReader.get("correct.email");
            case "empty":
                return "";
            default:
                return value;
        }
    }

    @Given("User sends a POST request to login endpoint with {string} and {string}")
    public void userSendsLoginRequest(String username, String password) {
        String resolvedUsername = resolveValue(username);
        String resolvedPassword = resolveValue(password);

        Response response = ApiAuthHelper.login(resolvedUsername, resolvedPassword);
        ScenarioContext.set("response", response);
        ScenarioContext.set("statusCode", response.getStatusCode());
    }

    @Then("API response status code should be {int}")
    public void apiResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response response = ScenarioContext.get("response");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()
                        + "\nResponse body: " + response.getBody().asString());
    }

    @And("API response body should not be empty")
    public void apiResponseBodyShouldNotBeEmpty() {
        Response response = ScenarioContext.get("response");
        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
        Assert.assertFalse(response.getBody().asString().isEmpty(), "Response body is empty");
    }

    @And("API response should contain a token field")
    public void apiResponseShouldContainTokenField() {
        Response response = ScenarioContext.get("response");
        String body = response.getBody().asString();

        // Token field name may vary: "token", "accessToken", "data.token"
        boolean hasToken = response.jsonPath().getString("token") != null
                || response.jsonPath().getString("accessToken") != null
                || response.jsonPath().getString("data.token") != null;

        Assert.assertTrue(hasToken,
                "Response does not contain a token field.\nResponse body: " + body);
    }

    @And("API response should contain error message {string}")
    public void apiResponseShouldContainErrorMessage(String expectedMessage) {
        Response response = ScenarioContext.get("response");
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains(expectedMessage),
                "Expected error message '" + expectedMessage + "' not found in response.\nResponse body: " + body);
    }

    @And("API response body should contain a validation error")
    public void apiResponseBodyShouldContainValidationError() {
        Response response = ScenarioContext.get("response");
        String body = response.getBody().asString();
        Assert.assertFalse(body.isEmpty(),
                "Response body is empty, expected a validation error message");
    }
}



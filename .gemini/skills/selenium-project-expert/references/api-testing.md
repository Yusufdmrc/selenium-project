# API Testing & Cookie Management

The framework includes an automated system to bypass Akamai bot protections during API testing by utilizing Selenium to fetch real browser cookies.

## How It Works
1.  **Auto Fetching:** When `ApiRunner` is executed, the framework automatically spawns a headless browser, navigates to the login page, logs in, and extracts the generated Akamai authorization cookies.
2.  **Caching:** These cookies are cached for 10 minutes to prevent unnecessary UI logins for subsequent API calls, massively speeding up API test execution.
3.  **Fallback:** If the automatic extraction fails, a manual fallback system is available.

## Writing API Tests

All API tests should be built using `RestAssured`. The authentication headers/cookies must be retrieved from the Cookie Helper utility and injected into the RestAssured Request Specification.

### Example API Step Definition snippet

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ApiAuthHelper; // Hypothetical util based on project analysis
import utils.ConfigReader;

public void callProtectedApi() {
    String apiUrl = ConfigReader.get("test.url") + "/api/v1/protected-endpoint";
    String authCookies = ApiAuthHelper.getValidCookies(); // This abstracts the 10-min cache and UI fetch mechanism

    Response response = RestAssured.given()
        .header("Cookie", authCookies)
        .header("Content-Type", "application/json")
        .when()
        .get(apiUrl);

    response.then().statusCode(200);
}
```

## Running API Tests
API Tests are executed separately from UI tests to trigger the specific auto-cookie setup hooks.

```bash
mvn test -Dtest=testRunners.ApiRunner
```

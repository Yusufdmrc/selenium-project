package util;

import Pages.CookiePage;
import Pages.LoginPage;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    WebDriver driver;
    protected Scenario scenario;


    private void initializeDriverAndHandleCookies() {
        String browser = System.getProperty("browser", "chrome");
        String testEnv = System.getProperty("testEnv", "test");

        driver = DriverFactory.initializeDriver(browser, testEnv);

        CookiePage cookiePage = new CookiePage(driver);
        cookiePage.closeCookiePopup();
    }

    @Before(order = 1, value = "not @LoginRequired and not @api")
    public void beforeScenario() {
        initializeDriverAndHandleCookies();
    }

    @Before(order = 0, value = "@LoginRequired and not @api")
    public void beforeScenarioWithLogin() {
        initializeDriverAndHandleCookies();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("correct.tc.id"),
                ConfigReader.get("correct.password")
        );
    }

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }

    @After("not @api")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

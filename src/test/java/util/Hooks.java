package util;

import Pages.CookiePage;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import java.util.Properties;

public class Hooks {

    WebDriver driver;
    Properties properties;


    @Before
    public void before() {
        String browser=System.getProperty("browser");
        String testEnv=System.getProperty("testEnv");

        driver = DriverFactory.initialize_Driver(browser,testEnv);

        CookiePage cookiePage=new CookiePage(driver);
        cookiePage.closeCookiePopup();
    }

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
          TakesScreenshot ts=(TakesScreenshot) driver;

          byte[] src=ts.getScreenshotAs(OutputType.BYTES);
          scenario.attach(src,"image/png","screnshot");
        }
    }

    @After
    public void after() {
        driver.quit();
    }
}
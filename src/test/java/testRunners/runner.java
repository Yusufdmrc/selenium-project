package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;


@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"stepDefinitions", "util"},
        plugin = {
                "summary", "pretty", "html:Reports/CucumberReport/Reports.html",
                "json:Reports/CucumberReport/Reports.json",
                "rerun:target/rerun.txt"
        },
        tags = "@DeleteAllSession"
)
public class runner extends AbstractTestNGCucumberTests {
}
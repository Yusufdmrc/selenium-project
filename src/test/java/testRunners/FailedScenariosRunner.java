package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",
        glue = {"stepDefinitions", "util"},
        plugin = {
                "summary", "pretty", "html:Reports/CucumberReport/FailedReports.html",
                "json:Reports/CucumberReport/FailedReports.json",
                "rerun:target/rerun.txt"
        }
)
public class FailedScenariosRunner extends AbstractTestNGCucumberTests {
}

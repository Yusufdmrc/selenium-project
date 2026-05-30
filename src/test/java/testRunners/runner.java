package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"stepDefinitions", "utils"},
        plugin = {
                "summary", "pretty", "html:Reports/CucumberReport/Reports.html",
                "json:Reports/CucumberReport/Reports.json",
                "rerun:target/rerun.txt"
        },
        tags = "@DrawResults"
)
public class runner extends AbstractTestNGCucumberTests {
}
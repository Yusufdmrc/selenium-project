package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"stepDefinitions", "utils"},
        plugin = {
                "summary", "pretty",
                "html:Reports/CucumberReport/ApiReports.html",
                "json:Reports/CucumberReport/ApiReports.json"
        },
        tags = ""
)
public class ApiRunner extends AbstractTestNGCucumberTests {
}


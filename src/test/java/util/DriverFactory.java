package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    static WebDriver driver;

    public static String setEnvironmentURL(String environment) {
        switch (environment.toLowerCase()) {
            case "test":
                return ConfigReader.get("test.url");
            case "prod":
                return ConfigReader.get("prod.url");
            case "preprod":
                return ConfigReader.get("preprod.url");
            default:
                throw new IllegalArgumentException("Unknown environment: " + environment);
        }
    }

    public static WebDriver initializeDriver(String browser, String environment) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getInt("page.load.timeout")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        driver.get(setEnvironmentURL(environment));

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

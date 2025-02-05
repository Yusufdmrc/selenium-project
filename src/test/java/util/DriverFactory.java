package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    static WebDriver driver;

    public static String setEnvironmentURL(String enviroment){
        switch (enviroment.toLowerCase()){
            case "test":
                return Constants.TEST_URL;
            case "prod":
                return Constants.PROD_URL;
            case "preprod":
                return Constants.PRE_PROD_URL;
            default:
                return null;
        }
    }

    public static WebDriver initialize_Driver(String browser,String environment){

        // properties = ConfigReader.getProperties();

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        String url =setEnvironmentURL(environment);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return getDriver();

    }

    public static WebDriver getDriver(){
        return driver;
    }


}
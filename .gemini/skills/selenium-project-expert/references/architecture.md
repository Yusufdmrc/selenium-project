# Framework Architecture

## Directory Structure
- `src/test/java/Pages/`: Contains Page Object Model classes. Every class should encapsulate the UI logic for a specific application page.
- `src/test/java/stepDefinitions/`: Contains the Cucumber step definition files. These classes bridge the Gherkin steps to the Page Object methods.
- `src/test/java/utils/`: Core utilities (`DriverFactory`, `ElementHelper`, `ConfigReader`, `DataProcess`).
- `src/test/java/testRunners/`: TestNG Runner classes (`runner.java`, `ApiRunner.java`, `FailedScenariosRunner.java`).
- `src/test/resources/Features/`: Gherkin `.feature` files.

## Page Object Model (POM) Standard
All pages must use `@FindBy` annotations and initialize elements via `PageFactory`.

```java
package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class ExamplePage {
    WebDriver driver;

    public ExamplePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "example-button")
    public WebElement exampleButton;

    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameInput;
}
```

## Config & Environment
- Environment URLs (`test.url`, `prod.url`, etc.) and wait timeouts are stored in `src/test/resources/config.properties`.
- Configuration values are read using `ConfigReader.get("key")` or `ConfigReader.getInt("key")`.

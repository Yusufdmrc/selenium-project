# ElementHelper Conventions

This framework centralizes all interaction logic through `utils.ElementHelper` to ensure test stability and standard timeout management. **Do NOT use raw Selenium methods like `element.click()` or `element.sendKeys()` directly in your pages or step definitions.**

## Initialization
`ElementHelper` must be initialized in your Step Definitions or Page actions with the WebDriver instance.

```java
import utils.ElementHelper;
import utils.DriverFactory;

ElementHelper elementHelper = new ElementHelper(DriverFactory.getDriver());
```

## The Unified Click Method
The `ElementHelper` contains an overloaded `click(Object elementLocator)` method. This method automatically waits for the element to be clickable (via `checkClickable`) before performing the click. It accepts both `WebElement` and `By` locators.

### Correct Usage

```java
// Using a WebElement injected via @FindBy
elementHelper.click(loginPage.submitButton);

// Using a By locator (if dynamic)
elementHelper.click(By.id("dynamic-id-" + id));
```

### Incorrect Usage
```java
// WRONG: Missing explicit waits and centralized logging
loginPage.submitButton.click(); 
```

## Other Common Methods
While `click` is the most important, check `ElementHelper` for other synchronization methods such as:
- `checkVisible(WebElement element)`
- `sendKey(WebElement element, String text)`
- Frame switching methods if working with IFrames.

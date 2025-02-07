# Selenium Cucumber Test Automation Project

This repository contains a test automation framework designed using **Selenium**, **Cucumber**, **TestNG**, and **Maven**. The framework is capable of running automated tests for web applications in multiple environments and browsers. Below is an overview of the key components and functionality of the project.

---

## Features

- **Test Automation**: Automates end-to-end testing using Selenium and Cucumber.
- **Parallel Execution**: Supports parallel test execution with TestNG.
- **Multi-Browser Support**: Configurable to run on Chrome, Firefox, and Edge.
- **Environment Configurations**: Easily switch between Test, Production, and Pre-Production environments.
- **Dynamic Data Generation**: Includes utility classes for generating random user data, such as emails, phone numbers, and passwords.
- **Advanced Reporting**: Generates detailed test execution reports in HTML and JSON formats.
- **Modular Design**: Components are modular and reusable for scalability and maintainability.
- **Frame Handling**: Ability to switch between different frames within the application for element interaction.
- **Dynamic Click Method**: Unified `click` method for both `WebElement` and `By` locators.
- **Screenshot on Failure**: Captures screenshots when a scenario fails and attaches them to the test report.
- **Scenario Management**: Supports background setup, scenario outlines, and examples for reusability.
- **Comprehensive Login Handling**: Validates various login scenarios, including successful, unsuccessful, and empty credentials.
- **@FindBy Annotation**: Utilizes the `@FindBy` annotation for efficient element location and cleaner code structure.
- **DevTools Network Monitoring**: Uses Chrome DevTools Protocol (CDP) to capture network requests and analyze API calls during test execution.

---

## Project Structure

### 1. Util Package

#### DriverFactory
- Initializes WebDriver for different browsers and environments.
- Provides methods to manage WebDriver instances.

#### ElementHelper
- Contains utility methods for common element actions (e.g., `click`, `checkVisible`, `checkClickable`).
- Handles explicit waits for better stability.
- Supports switching to a different frame.
- Unified `click` method for `WebElement` and `By` locators:

```java
public void checkClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void checkClickable(By element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void click(Object elementLocator) {
    WebElement element = null;
    if (elementLocator instanceof WebElement) {
        checkClickable((WebElement) elementLocator);
        element = (WebElement) elementLocator;
    } else if (elementLocator instanceof By) {
        checkClickable((By) elementLocator);
        element = driver.findElement((By) elementLocator);
    }
    assert element != null;
    element.click();
}
```

#### DevTools Integration
- Implements Chrome DevTools Protocol (CDP) for network request monitoring.
- Captures network requests sent during automation execution.
- Detects and logs specific API requests dynamically:


#### LoginHelper
- Provides utilities for handling user credentials and login data dynamically.

#### DataProcess
- Dynamically generates random user data (e.g., names, emails, birth dates, phone numbers, passwords).
- Handles Turkish-specific identity number generation.

#### Constants
- Stores static values like URLs, timeouts, and user credentials.

### 2. Pages Package
- Contains Page Object Model (POM) classes for handling UI components and actions.
- Implements methods for interacting with elements and performing validations (e.g., login functionality, error messages).
- Uses the `@FindBy` annotation to locate elements efficiently.

### 3. TestRunners Package

#### Runner Class
- Configures and executes Cucumber tests using TestNG.
- Integrates with Maven to enable test execution through commands.

### 4. Features Folder
- Contains Gherkin feature files that define test scenarios using `Scenario Outline` and other Gherkin keywords.
- Includes background steps to set up common preconditions for scenarios.
- Uses examples to handle parameterized testing for multiple input combinations.

### 5. Step Definitions Folder
- Implements step definitions for feature files.
- Links the Gherkin steps to Selenium actions.
- Handles actions like clicking buttons, entering text, and verifying results.

### 6. Reports Folder
- Stores HTML and JSON reports generated after test execution.
- Includes screenshots of failed scenarios.

---

## Technologies Used

- **Java**: Primary programming language for the framework.
- **Selenium WebDriver**: Automates browser interactions.
- **Cucumber**: Enables Behavior-Driven Development (BDD).
- **TestNG**: Manages test execution and reporting.
- **Maven**: Builds and manages project dependencies.
- **WebDriverManager**: Simplifies WebDriver setup for browsers.
- **Chrome DevTools Protocol (CDP)**: Used for network request interception and monitoring.

---

## Key Techniques

- **Parameterized Testing**: Uses `Scenario Outline` and `Examples` in feature files to handle multiple data inputs.
- **Page Object Model (POM)**: Encapsulates UI elements and actions in dedicated classes for better maintainability.
- **Assertions**: Validates expected outcomes using assertions for both positive and negative test scenarios.
- **Background Setup**: Simplifies repeated preconditions by defining them in the `Background` section of feature files.
- **Error Handling**: Captures screenshots and attaches them to reports when scenarios fail.
- **Login Scenarios**: Covers comprehensive test cases for login functionality, including various credential combinations.
- **@FindBy Annotation**: Simplifies element location using declarative annotations to improve readability and maintainability.
- **DevTools Network Monitoring**: Captures API requests and detects changes in backend communication.

---




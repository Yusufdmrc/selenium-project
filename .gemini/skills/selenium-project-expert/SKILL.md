---
name: selenium-project-expert
description: Specialized workflows and coding conventions for the Selenium-Cucumber test automation project. Use this skill when asked to write new Page Objects, Cucumber Step Definitions, UI/API test scenarios, or interact with ElementHelper and DriverFactory in this workspace.
---

# Selenium Project Expert

This skill provides expert-level guidance for working within the current hybrid (UI/API) Selenium-Cucumber test automation framework. The framework relies heavily on a centralized `ElementHelper`, Page Object Model (POM) with `@FindBy`, and an automated Akamai cookie mechanism for API testing.

## Core Architectural Rules

1.  **Strict BDD:** All test scenarios MUST be written in Cucumber Gherkin feature files (`src/test/resources/Features/`) and linked to Java step definitions (`src/test/java/stepDefinitions/`).
2.  **POM Enforcement:** Direct `driver.findElement` calls in step definitions are prohibited. All elements MUST be defined in dedicated Page classes (`src/test/java/Pages/`) using `@FindBy` annotations.
3.  **Element Interaction:** Standard Selenium click/send keys are wrapped. You MUST use the methods provided in `utils.ElementHelper` for interacting with web elements to ensure explicit waits and proper error handling.

## Available References

Whenever writing code for this project, consult the following references:

*   **[architecture.md](references/architecture.md)**: Detailed overview of package structures, runners, and the Page Object Model structure.
*   **[element-helper.md](references/element-helper.md)**: Critical usage patterns for the `ElementHelper` class (the unified click method, explicit waits).
*   **[api-testing.md](references/api-testing.md)**: Instructions for the auto-cookie mechanism and writing RestAssured tests using `ApiRunner`.

## Quick Workflow: Adding a New Page & Test

1.  Create a `<Name>.feature` file in `src/test/resources/Features/`.
2.  Create a `<Name>Page.java` class in `src/test/java/Pages/` containing `@FindBy` locators.
3.  Create a `<Name>StepDefinitions.java` class in `src/test/java/stepDefinitions/`.
4.  Instantiate the Page class inside the StepDefinition and use `ElementHelper` methods to trigger actions.
5.  Validate the scenarios by executing `runner.java` (for UI) or `ApiRunner.java` (for API).

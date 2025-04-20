@MilliPiyangoCheckSecuritySection
Feature: Milli Piyango Security Section Test Cases

  Background:
    Given User navigates to the Security Section page

    @DeleteAllSession @SkipLogin
    Scenario: Delete all session from the Security Section
      When The user click to Select All button
      When The user clicks the delete button and deletes all data
      Then The user verifies that all sessions are deleted





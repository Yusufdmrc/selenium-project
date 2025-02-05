@MilliPiyangoVirtualGame
Feature: Milli Piyango Virtual Game Test Cases

  @UnsuccessfulVirtualGamePlay @SkipLogin
  Scenario Outline: Verify unsuccessful virtual gaming
    Given User navigates to the Sanal Oyunlar page
    When  User clicks on the Inspired 3 game button
    Then  The user receives an "<error>" message in a new window
    Examples:
    |error                      |
    |Oops! A problem has occured|
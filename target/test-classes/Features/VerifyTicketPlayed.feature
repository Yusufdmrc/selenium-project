@MilliPiyangoTicketCheck
Feature: Milli Piyango Ticket Test Cases

  @VerifyTicketPurchaseUI @LoginRequired
  Scenario Outline: Verify successful ticket purchase on the UI
    Given User navigates to the "<game>" page
    When  User buys "<game>" tickets with the random button
    Then  User checks whether the ticket has been purchased
    Examples:
    |game         |
    |Sans Topu    |



  @VerifyNumbersPlayedBackend @LoginRequired
  Scenario Outline: Verify that the selected numbers match between UI and Backend
    Given User navigates to the "<game>" page
    When  User buys "<game>" tickets with the random button
    Then  The user checks the accuracy of the numbers played
    Examples:
      |game         |
      |On Numara    |

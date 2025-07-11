@MilliPiyangoTicketCheck
Feature: Milli Piyango Ticket Test Cases

  @VerifyTicketPlayed @LoginRequired
  Scenario Outline: Verify that the ticket has been played
    Given User navigates to the "<game>" page
    When  User buys "<game>" tickets with the random button
    Then  User checks whether the ticket has been purchased
    Examples:
    |game         |
    |Sans Topu    |



  @VerifyNumbersPlayed @LoginRequired
  Scenario Outline: Verify that the ticket has been played
    Given User navigates to the "<game>" page
    When  User buys "<game>" tickets with the random button
    Then  The user checks the accuracy of the numbers played
    Examples:
      |game         |
      |On Numara    |



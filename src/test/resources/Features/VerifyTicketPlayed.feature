@MilliPiyangoTicketCheck
Feature: Milli Piyango Ticket Test Cases

  @OnNumaraVerifyTicketPlayed @SkipLogin
  Scenario: Verify that the ticket has been played
    Given User navigates to the On Numara page
    When  User buys On Numara tickets with the random button
    Then  User checks whether the ticket has been purchased



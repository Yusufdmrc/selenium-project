@MilliPiyangoTicketCheck
Feature: Milli Piyango Ticket Detail Test Cases

  @TicketCheck @SkipLogin
  Scenario: Verify ticket time
    Given User navigates to the On Numara page
    When  User buys On Numara tickets with the random button
    Then  User checks whether the ticket has been purchased

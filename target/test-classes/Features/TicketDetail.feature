@MilliPiyangoTicketDetail
Feature: Milli Piyango Ticket Detail Test Cases

  @TicketDetailTimeCheck @SkipLogin
  Scenario Outline: Verify ticket time
    Given User navigates to the On Numara page
    When  User buys On Numara tickets with the random button
    When  User navigates to the Biletlerim page
    Then  User checks the ticket time
    Examples:

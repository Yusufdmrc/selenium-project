@MilliPiyangoTicketInquiry
Feature: Milli Piyango Ticket Detail Test Cases

  @TicketInquiry @SkipLogin
  Scenario Outline: Ticket inquiry by serial number
    Given User goes to the Lotteries page
    When  User queries ticket by "<serialNumber>"
    Then  The user checks if the ticket "<statusText>" has been successfully viewed
    Examples:
      | serialNumber                   | statusText   |
      | 24008E219001LB4000010008285    | Kazandın!    |
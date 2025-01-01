@MilliPiyangoCheckTicketDetail
Feature: Milli Piyango Ticket Detail Test Cases

  @OnNumaraTicketDetail @SkipLogin
  Scenario: OnNumara ticket detail check on Biletlerim page
    Given User navigates to the Biletlerim page
    When  The user filters by selecting the On Numara game
    When  The user filters by selecting the Çekiliş Bekleyen status
    When  User clicks to the detail button
    Then  The user confirms that the ticket detail has been successfully viewed


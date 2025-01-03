@MilliPiyangoCheckTicketDetail
Feature: Milli Piyango Ticket Detail Test Cases

  @OnNumaraTicketDetail @SkipLogin
  Scenario Outline: OnNumara ticket detail check on Biletlerim page
    Given User navigates to the Biletlerim page
    When  The user filters by selecting the On Numara game
    When  The user filters by selecting the "<status>" status
    When  User clicks to the detail button
    Then  The user verifies the ticket detail "<message>" according to the selected status
    Examples:
      | status           |  message                 |
      | Çekiliş Bekleyen |  Çekiliş için bekleniyor |
      | Kazanan          |  Kazandın                |
      | Kaybeden         |  Kazanamadın             |


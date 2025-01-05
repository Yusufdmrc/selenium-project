@MilliPiyangoCheckTicketDetail
Feature: Milli Piyango Ticket Detail Test Cases

  @TicketDetail @SkipLogin
  Scenario Outline: OnNumara ticket detail check on Biletlerim page
    Given User navigates to the Biletlerim page
    When  The user filters by selecting the "<game>" "<status>" and "<dateRange>"
    When  User clicks to the detail button
    Then  The user verifies the ticket detail "<message>" according to the selected  "<status>"
    Examples:
    |game           | status           |  dateRange    | message                      |
    |OnNumara       | Çekiliş Bekleyen |  3 ay         | Çekiliş için bekleniyor      |
    |Sayısal Loto   | Kazanan          |  2 ay         | Kazandın                     |
    |Süper Loto     | Kaybeden         |  7 gün        | Kazanamadın                  |




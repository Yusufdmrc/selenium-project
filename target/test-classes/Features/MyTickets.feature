@MilliPiyangoCheckTicketDetail
Feature: Milli Piyango Ticket Detail Test Cases

  Background:
    Given User navigates to the Biletlerim page
  @TicketDetail @SkipLogin
  Scenario Outline:Ticket detail check on Biletlerim page
    When  The user filters by selecting the "<game>" "<status>" and "<dateRange>"
    When  User clicks to the detail button
    Then  The user verifies the ticket detail "<message>" according to the selected  "<status>"
    Examples:
      | game         | status           | dateRange | message                 |
      | On Numara    | Çekiliş Bekleyen | 3 ay      | Çekiliş için bekleniyor |
      | Sayısal Loto | Kazanan          | 2 ay      | Kazandın                |
      | Süper Loto   | Kaybeden         | 3 ay      | Kazanamadın             |


  @GameFiltering @SkipLogin
  Scenario Outline: Game filtering on Biletlerim page
    When  The user filters by selecting the "<game>"
    Then  User confirms successful filtering of game "<drawName>"
    Examples:
    |game           | drawName       |
    |Sayisal Loto   | sayisal-loto   |


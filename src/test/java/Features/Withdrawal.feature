@MilliPiyangoWithdrawal
Feature: Milli Piyango Withdrawal Check Test Cases

  @SuccessfulWithdrawals
  Scenario Outline: Correct "<username>" Username &  Correct "<password>" Password for login
    Given User at home page
    When  Click member login button
    And  write "<username>" for username field
    And  write "<password>" for password field
    And  Click login button
    Then Check Successful login
    When User clicks the 'Hesabım' button
    And  User clicks the 'Para Çekme' from the left side menu
    And  User clicks 'Yeni Ekle' button
    And  User enters "<ibanNo>" into the "IBAN" field
    And  User clicks the 'DOĞRULA' button
    And  User enters "<shortName>" into the "Short Name" field
    And  User clicks the 'KAYDET' button
    And  User clicks the 'Para Çekme' button
    And  User enters "<price>" into the "Price" field
    And  User clicks the 'ONAYLA' button
    Then Check Successful withdrawal

    Examples:
      |username               |password         |ibanNo     |shortName  |price    |
      |correctTcID            |correctPassword  |ibanno     |shortName  |price    |
      |correctAccountNo       |correctPassword  |ibanno     |shortName  |price    |
      |correctEmail           |correctPassword  |ibanno     |shortName  |price    |


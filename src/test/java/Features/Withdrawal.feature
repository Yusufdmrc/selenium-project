@MilliPiyangoWithdrawal
Feature: Milli Piyango Withdrawal Check Test Cases

  @SuccessfulWithdrawals
  Scenario Outline: Correct Username & Password for login and Successful Withdrawal
    Given User is logged in with "<username>" and "<password>"
    When User navigates to the 'Para Çekme' page
    And User adds a new IBAN with "<ibanNo>" and "<shortName>"
    And User withdraws "<price>"
    Then Check Successful withdrawal

    Examples:
      | username        | password        | ibanNo   | shortName  | price   |
      | correctTcID     | correctPassword | ibanNo   | shortName1 | price   |
      | correctAccountNo| correctPassword | ibanNo   | shortName2 | price   |
      | correctEmail    | correctPassword | ibanNo   | shortName3 | price   |

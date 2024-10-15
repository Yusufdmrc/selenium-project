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
      | correctTcID     | correctPassword | TR12**** | shortName1 | 500     |
      | correctAccountNo| correctPassword | TR34**** | shortName2 | 1000    |
      | correctEmail    | correctPassword | TR56**** | shortName3 | 750     |

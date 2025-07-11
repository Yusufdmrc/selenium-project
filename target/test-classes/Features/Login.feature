@MilliPiyangoLogin
Feature: Milli Piyango Login Check Test Cases

    Background:
      Given User at home page
      When  Click member login button
    @AllEmpty
    Scenario Outline: Check  "<username>" username "<password>" password for login
      And write "<username>" for username field
      And write "<password>" for password field
      And Click login button
      Then Check unsuccessful login
      Examples:
        |username       |password   |
        |empty          |empty      |
        |abcdefg        |empty      |
        |empty          |123456     |

      @UnsuccessfulLogin
      Scenario Outline: Check Correct "<username>" username & False "<password>" Password for login
        And write "<username>" for username field
        And write "<password>" for password field
        And Click login button
        Then Check "<error>" message about credentials not valid
        Examples:
          |username              |error                             |password  |
          |correctTcID           |Kimlik bilgileri geçerli değil    |abc1234   |
          |asdfg                 |Kimlik bilgileri geçerli değil    |correctPassword |


       @SuccessfulLogin
       Scenario Outline: Correct "<username>" Username &  Correct "<password>" Password for login
         And write "<username>" for username field
         And write "<password>" for password field
         And Click login button
         Then Check Successful login
         Examples:
           |username               |password         |
           |correctTcID            |correctPassword  |
           |correctAccountNo       |correctPassword  |
           |correctEmail           |correctPassword  |






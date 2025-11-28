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
          | username    | error                                        | password        |
          | correctTcID | Girdiğin şifre ve hesap bilgileri uyuşmuyor. | abc1234         |
          | asdfg       | Kimlik bilgileri geçerli değil               | correctPassword |


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

    @RetrievalWebFormMissingData
       Scenario Outline: User verifies Password Retrieval form is not submittable when with "<tcId>" TC ID, "<birthDate>" Birth Date, "<email>" email missing data
         And User navigates to the Pasword Retrieval Page
         Then User verifies Password Retrieval form is not submittable when with "<tcId>" TC ID, "<birthDate>" Birth Date, "<email>" email missing data
         @missingBirthDate
         Examples:
           | tcId        | birthDate | email        |
           | correctTcID | empty     | correctEmail |
         @missingTcId
         Examples:
           | tcId  | birthDate | email        |
           | empty | 01122001  | correctEmail |
         @missingEmail
         Examples:
           | tcId        | birthDate | email     |
           | correctTcID | 01122001  | empty     |





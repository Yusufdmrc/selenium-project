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

    @PasswordUpdateSuccess
      Scenario Outline: User verifies that can update the password to the "<password>" on Password Retrieval Screen
       And User navigates to the Pasword Retrieval Page
       And User fills the Password Retrieval form with "<tcId>" "<birthDate>" "<email>"
       And User enters and submit OTP for password update
       And User changes the password with credentials "<password>" on Password Retrieval Screen and submit
       And User navigates to the Login Page from Password Update Screen
       And User logs into the app with "<password> "tcID"
       Then User verifies the login with "<password>" is successful
       @password1
       Examples:
         | password   |  | tcId        | birthDate | email        |
         | Piyango.59 |  | correcttcid | 10122001  | correctEmail |
      @password2
       Examples:
         | password  |  | tcId        | birthDate | email        |
         | Piyango1. |  | correctTcID | 10122001  | correctEmail |



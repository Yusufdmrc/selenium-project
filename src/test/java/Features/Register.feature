@MilliPiyangoRegister
Feature: Milli Piyango Register Check Test Cases

  @CorrectRegister
  Scenario Outline: Successful User Registration with different data
    When User goes to the registration page
    And User enters "<firstName>" in the "Ad Name" field
    And User enters "<lastName>" in the "Soyadı" field
    And User enters "<birthDate>" in the "Doğum Tarihi" field
    And User enters "<email>" in the "E-posta" field
    And User enters "<phone>" in the "Cep telefonu" field
    And User enters "<tcNo>" in the "TC Numarası" field
    And User enters "<password>" in the "Şifre" field
    And User clicks the ONAYLA button
    And User enters "<otpNumber>" in the "OTP" field
    And User clicks the otpConfirm button
    Then Check successful register

    Examples:
      | firstName    | lastName   | birthDate   | email             | phone         | tcNo         | password       | otpNumber |
      | test100      | test210    | 15.05.1985  | test100@gmail.com | 0240472023   | 74489232122  | Piyango2.      | 000000    |

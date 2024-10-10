@MilliPiyangoRegister
Feature: Milli Piyango Register Check Test Cases

  @CorrectRegister
  Scenario Outline: Successful User Registration with different data
    When User goes to the registration page
    When User enters "<firstName>" in the "Ad Name" field
    And User enters "<lastName>" in the "Soyadı" field
    And User selects "<birthDate>" in the "Doğum Tarihi" field
    And User enters "<email>" in the "E-posta" field
    And User enters "<phone>" in the "Cep telefonu" field
    And User enters "<tcNo>" in the "TC Numarası" field
    And User enters "<password>" in the "Şifre" field
    When User clicks the 'ONAYLA' button
    When User enters "<otpNumber>" in the "OTP" field

    Examples:
      | firstName | lastName | birthDate   | email                 | phone         | tcNo         | password       | otpNumber |
      | John      | Doe      | 01.01.1990  | john.doe@gmail.com    | 05434184051   | 38541192700  | Piyango1.      | 000000    |
#      | Jane      | Smith    | 15.05.1985  | jane.smith@example.com| 05434184052   | 96103465464  | Piyango2.      |
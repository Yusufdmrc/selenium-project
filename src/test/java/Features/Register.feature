  @MilliPiyangoRegister
  Feature: Milli Piyango Register Check Test Cases

    @SuccessfulRegistration
    Scenario Outline: Successful User Registration with different data
      When User goes to the registration page
      And User completes the registration form with random data
      And User completes the OTP form with "<otpNumber>"
      Then Check successful register

      Examples:
        | otpNumber           |
        | correctOtpNumber    |

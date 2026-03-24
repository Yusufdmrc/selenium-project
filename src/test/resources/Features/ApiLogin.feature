@api @apiLogin
Feature: Milli Piyango Login API Test Cases

  @apiLoginSuccessful
  Scenario Outline: Successful login with valid "<username>" credentials via API
    Given User sends a POST request to login endpoint with "<username>" and "<password>"
    Then API response status code should be 200
    And API response body should not be empty
    And API response should contain a token field

    Examples:
      | username         | password        |
      | correctTcID      | correctPassword |
      | correctAccountNo | correctPassword |
      | correctEmail     | correctPassword |

  @apiLoginInvalidCredentials
  Scenario Outline: Unsuccessful login with invalid credentials via API
    Given User sends a POST request to login endpoint with "<username>" and "<password>"
    Then API response status code should be 401
    And API response should contain error message "<errorMessage>"

    Examples:
      | username    | password | errorMessage                                 |
      | correctTcID | abc1234  | Girdiğin şifre ve hesap bilgileri uyuşmuyor. |
      | invalidUser | test123  | Kimlik bilgileri geçerli değil                |


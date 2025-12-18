@LotteryGames
Feature: Lottery Games Test Cases

  @e2e
  Scenario Outline: "<Game>" - User is able to buy ticket "<Type>" SuperStar on tab : "<Tab>"
    When User navigates to the "<Game>" game page from homepage
    And User navigates to "<Tab>" tab on game
    And User enters <drawNumber> row by random button
    Then User verifies the ticket was bought successfully
    Then User verifies the receipt elements for <drawNumber> draw <columnNumber> column <standartNumber> standart number <superstarNumber> superstar number
    Then User verifies the details of the ticket for "<Game>"
    @sansTopuSimple
    Examples:
      | Game      | Tab    | Type | drawNumber | columnNumber | standartNumber | superstarNumber |
      | Sans Topu | simple | with | 1          | 1            | 5              | 1               |

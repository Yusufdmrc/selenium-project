@DrawResults
Feature: Draw Results Test Cases

  @FilterDrawResults
  Scenario Outline: Verify that games are listed according to the filter
    Given User navigates to the Draw Results page
    When User filters results by "<game>"
    Then User verifies that the results belong to "<game>"
    Examples:
      | game           |
      | Sayısal Loto   |
      | Süper Loto     |
      | Milli Piyango  |
      | Şans Topu      |
      | On Numara      |

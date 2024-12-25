@MilliPiyangoCheckBiletlerimPage
Feature: Milli Piyango Biletlerim Page Test Cases

  @OnNumaraFiltering @SkipLogin
  Scenario: OnNumara filtering on Biletlerim page
    Given User navigates to the Biletlerim page
    When  The user filters by selecting the On Numara game
    Then  User confirms successful filtering of On Numara draws


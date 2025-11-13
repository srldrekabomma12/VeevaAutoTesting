@TestCase3_for_DP1
Feature: Validate footer hyperlinks are stored & not having Duplicates

  Scenario: footer hyperlinks are stored & not having Duplicates
    Given user is on login page for url "dp1.url"
    Then user validates url navigates to "https://www.nba.com/sixers/"

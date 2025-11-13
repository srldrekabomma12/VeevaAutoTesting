@TestCase4_for_DP2
Feature: Validate footer hyperlinks are stored & not having Duplicates

  Scenario: footer hyperlinks are stored & not having Duplicates
    Given user is on login page for url "dp2.url"
    Then user validates url navigates to "https://www.nba.com/bulls/"
    And user capture footer hyperlinks and validate any Duplicate values are present
    Then user stores all links into any file

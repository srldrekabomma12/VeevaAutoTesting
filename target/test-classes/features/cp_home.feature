@TestCase2_for_CP
Feature: Validate News & Features Page from CP Home Page having >= 3d Videos Feeds

  Scenario: News & Features Page from CP Home Page having >= 3d Videos Feeds
    Given user is on login page for url "cp.url"
    Then user validates url navigates to "https://www.nba.com/warriors/"
    And user close popup "InsiderPopup_modal__QyNvR" if present and validate title/logo "NBA Logo" after closing popup
    #  From the CP home page , hover on menu Item >> click on New & Features
    When user mousehover to "Nav Item Button"
    And user selects "News & Features" from the menu "Nav Item Button"
#    validate url is navigating to expected or not
    Then user validates url navigates to "https://www.nba.com/warriors/news"
#  Count total number of Videos Feeds and count the videos feeds those are present in the page >= 3d
    Then user validates list of "VIDEOS" Feeds having "21" count
    Then user validates count of "VIDEOS" feeds greater than "3d" are "0"

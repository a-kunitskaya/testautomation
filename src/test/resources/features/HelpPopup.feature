Feature: Gmail Help popup

  Background:
    Given I opened Login page
    And I login as "VALID" user

  Scenario Outline: CDP-0003 Gmail: Help pop-up
    When I open help popup
    Then Help popup is opened
    When I search for <input>
    Then I get corresponding search results
    When I clear search field
    And I go to help page
    Then Help page is displayed
    When I close window
    And I switch to mail page
    And I switch to help popup
    And I go to help forum page
    Then Help forum page is displayed
    When I close window
    And I switch to mail page
    And I switch to help popup
    And I open feedback popup
    Then Feedback popup is displayed
    When I leave feedback and cancel it
    Then Feedback popup is not displayed
    Then Mail page is displayed

    Examples:
      | input  |
      | Change |
      | how    |

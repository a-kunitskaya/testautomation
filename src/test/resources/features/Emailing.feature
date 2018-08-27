Feature: Gmail emailing

  Background:
    Given I logged in to Gmail

  Scenario: CDP-0001 Gmail: Sending invalid email
    When I create and send email without body
    Then alert is displayed
    When I accept alert
    And I go to sent folder
    Then sent email is displayed

  Scenario: CDP-0004 Gmail: Create an email, save it as a draft and send
    When I create valid email
    And save email as draft
    Then email is displayed in drafts folder
    When I send draft email
    Then email is not displayed in drafts folder
    When I go to sent folder
    Then sent email is displayed
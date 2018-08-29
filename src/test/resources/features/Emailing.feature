Feature: Gmail emailing

  Background:
    Given I opened Login page
    And I login as "VALID" user

  Scenario: CDP-0001 Gmail: Sending invalid email
    When I create "EMPTY" email
    And I send "EMPTY" email
    Then alert is displayed
    When I accept alert
    And I go to sent folder
    Then sent "EMPTY" email is displayed

  Scenario: CDP-0004 Gmail: Create an email, save it as a draft and send
    When I create "VALID" email
    And save email as draft
    Then email is "PRESENT" in drafts folder
    When I send "DRAFT" email
    Then email is "ABSENT" in drafts folder
    When I go to sent folder
    Then sent "VALID" email is displayed
Feature: Gmail login

  Background:
    Given I opened Login page

  Scenario: CDP-0002 Logging in with invalid password
    When I login as "INVALID_PASSWORD" user
    Then Error message should be displayed
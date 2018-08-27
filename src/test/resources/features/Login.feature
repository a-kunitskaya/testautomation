Feature: Gmail login

  Background:
    Given I opened Login page

  Scenario Outline: CDP-0002 Logging in with invalid password
    When I login to Gmail with a valid username <username> and invalid password <password>
    Then Error message should be displayed

    Examples:
      | username                   | password |
      | aktestautomation@gmail.com | pwddd    |


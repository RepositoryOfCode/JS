@ZAP_Regression
Feature: Dry principle
@DRY
  Scenario: Registration of user with DRY principle
    Given Open Wep Application
    Then Open Login page
    Then Click on not yet customer
    Then fill up all fields
    Then Change first filed with password on other password
    Then Click on Register
    Then Check confirmation message
    Given Zap testing
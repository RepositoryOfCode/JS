@XSS @ZAP_Regression
Feature: Check reflect xss script

  Scenario: Send XSS via "Search" module
    Given Open Wep Application
    Then Send xss script using search module
    Then Check POP up.
    When Open DOM and check iframe locator
    Given Zap testing

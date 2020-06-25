@ZAP_Regression
Feature: SQL Injection
@SQLInjection
  Scenario: Login by using SQL Injection "' or 1=1--"
    Given Open Wep Application
    Given Open Login page
    Then Write SQL injection into email field
    Then Write password into password field
    When Click on Login button
    Then Check that user profile is displayed
    Given Zap testing
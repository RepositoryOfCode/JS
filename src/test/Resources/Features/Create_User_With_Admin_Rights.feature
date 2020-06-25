@ZAP_Regression
Feature: Create user with admin rights
@AdminRights
  Scenario: Create user with admin rights using API connection
    Then Send this JSON file with POST method to endpoint "users"
    And Status code should be 201
    Then Open Wep Application
    Then Login as a user with same email and password
    Then Open admin url "http://localhost:3000/#/administration"
    And Check Administration title is displayed
    And Zap testing


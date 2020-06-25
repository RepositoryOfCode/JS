@Regression
Feature: Regression + Zap

  Scenario: Send XSS via "Search" module
    Given Open Wep Application
    Then Send xss script using search module
    Then Check POP up.
    When Open DOM and check iframe locator

  Scenario: Upload Zip Bomb
    Given Open Wep Application
    Then Login as a user
    Then Open Complaint page
    Then Write comments in comment field
    Then Upload zipbomb file "C:\Users\DKALININ\Documents\UsefullData\New folder\bomb.zip"
    Then Check that file was succesfuly uploaded

  Scenario: Registration of user with DRY principle
    Given Open Wep Application
    Then Open Login page
    Then Click on not yet customer
    Then fill up all fields
    Then Change first filed with password on other password
    Then Click on Register
    Then Check confirmation message

  Scenario: Login by using SQL Injection "' or 1=1--"
    Given Open Wep Application
    Given Open Login page
    Then Write SQL injection into email field
    Then Write password into password field
    When Click on Login button
    Then Check that user profile is displayed

  Scenario: Give Zero Star Feedback
    Given Open Wep Application
    Then Open Feedback Page
    Then Give a comment
    Then Fill up captcha
    When Modify attribute disable on enable of Submit button in DOM
    When Click on Submit button

  Scenario:Download Logs
    Given Open Wep Application
    Given Download log file thru link "http://localhost:3000/support/logs".
    Then Open that file

  Scenario: Create user with admin rights using API connection
    Then Send this JSON file with POST method to endpoint "users"
    And Status code should be 201
    Then Open Wep Application
    Then Login as a user with same email and password
    Then Open admin url "http://localhost:3000/#/administration"
    And Check Administration title is displayed

  Scenario: Zap Testing
    Given Zap testing
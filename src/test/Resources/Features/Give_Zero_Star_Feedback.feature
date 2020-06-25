@ZAP_Regression
Feature: ZeroStarFeedback
@0StarFeedback
  Scenario: Give Zero Star Feedback
    Given Open Wep Application
    Then Open Feedback Page
    Then Give a comment
    Then Fill up captcha
    When Modify attribute disable on enable of Submit button in DOM
    When Click on Submit button
    Given Zap testing
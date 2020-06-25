@Zip_Bomb @ZAP_Regression
Feature: Upload Zip Bomb

  Scenario: Upload Zip Bomb
    Given Open Wep Application
    Then Login as a user
    Then Open Complaint page
    Then Write comments in comment field
    Then Upload zipbomb file "C:\Users\DKALININ\Documents\UsefullData\New folder\bomb.zip"
    Then Check that file was succesfuly uploaded
    Given Zap testing
@ZAP_Regression
Feature: Download Log File from the Server
@DownloadLogs
  Scenario:Download Logs
    Given Open Wep Application
    Given Download log file thru link "http://localhost:3000/support/logs".
    Then Open that file
    Given Zap testing
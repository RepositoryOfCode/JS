package JuiceShop.Step_Defenitions;

import JuiceShop.Utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.zaproxy.clientapi.core.Alert;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class Zap_Testing_Steps {

    private static Logger logger = LogManager.getLogger(Zap_Testing_Steps.class);

    private final static String MEDIUM = "MEDIUM";
    private final static String HIGH = "HIGH";
    public static ScanningProxy zapScanner;
    public static Spider zapSpider;
    public static WebDriver driver;


    // Provide scan policy names
    private final static String[] policyNames =
            {"directory-browsing", "cross-site-scripting",
                    "sql-injection", "path-traversal", "remote-file-inclusion",
                    "server-side-include", "script-active-scan-rules",
                    "server-side-code-injection", "external-redirect",
                    "crlf-injection"};
    int currentScanID;

    // Create ZAP proxy by specifying proxy host and proxy port
    public static Proxy createZapProxyConfiguration() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(ConfigurationReader.get("zap_proxyhost" ) + ":" + ConfigurationReader.get("zap_proxyport" ));
        proxy.setSslProxy(ConfigurationReader.get("zap_proxyhost" ) + ":" + ConfigurationReader.get("zap_proxyport" ));
        return proxy;
    }

    /*
     * Method to filter the generated alerts based on Risk and Confidence
     */
    private List<Alert> filterAlerts(List<Alert> alerts) {
        List<Alert> filteredAlerts = new ArrayList<Alert>();
        for (Alert alert : alerts) {
            // Filtering based on Risk: High and Confidence: Not Low
            if (alert.getRisk().equals(Alert.Risk.High) && alert.getConfidence() != Alert.Confidence.Low)
                filteredAlerts.add(alert);
        }
        return filteredAlerts;
    }

    /*
     * Method to specify the strength for the ZAP Scanner
     * as High, Medium, or Low
     */
    public void setAlert_AttackStrength() {
        for (String ZapPolicyName : policyNames) {
            String ids = activateZapPolicy(ZapPolicyName);
            for (String id : ids.split("," )) {
                zapScanner.setScannerAlertThreshold(id, MEDIUM);
                zapScanner.setScannerAttackStrength(id, HIGH);
            }
        }
    }

    /*
     * Method to configure the ZAP Scanner for
     * specified security policies and enable the scanner
     */

    private String activateZapPolicy(String policyName) {
        String scannerIds = null;
        // Compare the security policies
        // and specify scannerIds (these scannerIds are standard)
        switch (policyName.toLowerCase()) {
            case "directory-browsing":
                scannerIds = "0";
                break;
            case "cross-site-scripting":
                scannerIds = "40012,40014,40016,40017";
                break;
            case "sql-injection":
                scannerIds = "40018";
                break;
            case "path-traversal":
                scannerIds = "6";
                break;
            case "remote-file-inclusion":
                scannerIds = "7";
                break;
            case "server-side-include":
                scannerIds = "40009";
                break;
            case "script-active-scan-rules":
                scannerIds = "50000";
                break;
            case "server-side-code-injection":
                scannerIds = "90019";
                break;
            case "remote-os-command-injection":
                scannerIds = "90020";
                break;
            case "external-redirect":
                scannerIds = "20019";
                break;
            case "crlf-injection":
                scannerIds = "40003";
                break;
            case "source-code-disclosure":
                scannerIds = "42,10045,20017";
                break;
            case "shell-shock":
                scannerIds = "10048";
                break;
            case "remote-code-execution":
                scannerIds = "20018";
                break;
            case "ldap-injection":
                scannerIds = "40015";
                break;
            case "xpath-injection":
                scannerIds = "90021";
                break;
            case "xml-external-entity":
                scannerIds = "90023";
                break;
            case "padding-oracle":
                scannerIds = "90024";
                break;
            case "el-injection":
                scannerIds = "90025";
                break;
            case "insecure-http-methods":
                scannerIds = "90028";
                break;
            case "parameter-pollution":
                scannerIds = "20014";
                break;
            default:
                throw new RuntimeException("No policy found for: " + policyName);
        }
        zapScanner.setEnableScanners(scannerIds, true);
        return scannerIds;
    }

    /*
     * Method to configure spider settings, execute
     * ZAP spider, log the progress and found URLs
     */
    public void spiderWithZap() {
        logger.info("Spidering started" );
        // Configure spider settings
        //zapSpider.excludeFromSpider(XSS_Steps.LOGOUT_URL);
        zapSpider.setThreadCount(5);
        zapSpider.setMaxDepth(5);
        zapSpider.setPostForms(false);
        // Execute the ZAP spider
        // zapSpider.spider(WebSiteNavigation.BASE_URL);
        zapSpider.spider(ConfigurationReader.get("url"));
        int currentSpiderID = zapSpider.getLastSpiderScanId();
        int progressPercent = 0;
        while (progressPercent < 100) {
            progressPercent = zapSpider.getSpiderProgress(currentSpiderID);
            logger.info("Spider is " + progressPercent + "% complete." );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Log the found URLs after spider
        for (String url : zapSpider.getSpiderResults(currentSpiderID)) {
            logger.info("Found URL after spider: " + url);
        }
        logger.info("Spidering ended" );
    }

    /*
     * Method to execute scan and log the progress
     */
    public void scanWithZap() {
        logger.info("Scanning started" );
        // Execute the ZAP scanner
        //zapScanner.scan(WebSiteNavigation.BASE_URL);
        zapScanner.scan(ConfigurationReader.get("url"));
        int currentScanId = zapScanner.getLastScannerScanId();
        int progressPercent = 0;
        while (progressPercent < 100) {
            progressPercent = zapScanner.getScanProgress(currentScanId);
            logger.info("Scan is " + progressPercent + "% complete." );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Scanning ended" );
    }

    // Test methods -- testVulnerabilitiesBeforeLogin,
    // testVulnerabilitiesAfterLogin

// ----------------------------------------------------

    /*
     * Test method containing test steps like
     * navigateBeforeLogin, spiderWithZAP,
     * setAlert_AttackStrength, scanWithZAP, filterAlerts, and
     * log the found alerts and assert the count of alerts
     */


    /*
     * Test method containing test steps like loginAsUser, navigateAfterLogin,
     * spiderWithZAP, setAlert_AttackStrength, scanWithZAP, filterAlerts, and
     * log the found alerts and assert the count of alerts
     */



    @Given("Zap testing" )
    public void Zap_testing() {

        // Using ZAP Spider
        logger.info("Started spidering" );
        spiderWithZap();
        logger.info("Ended spidering" );

        // Setting alert and attack
        setAlert_AttackStrength();
        zapScanner.setEnablePassiveScan(true);

        // Using ZAP Scanner
        logger.info("Started scanning" );
        scanWithZap();
        logger.info("Ended scanning" );

        List<Alert> generatedAlerts = filterAlerts(zapScanner.getAlerts());
        for (Alert alert : generatedAlerts) {
            logger.info("Alert: " + alert.getAlert() +
                    " at URL: " + alert.getUrl() +
                    " Parameter: " + alert.getParam() +
                    " CWE ID: " + alert.getCweId());
        }
        assertThat(generatedAlerts.size(), equalTo(0));
    }

}

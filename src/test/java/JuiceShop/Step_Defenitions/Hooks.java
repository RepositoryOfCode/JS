package JuiceShop.Step_Defenitions;

import io.restassured.RestAssured;
import JuiceShop.Utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    static Logger logger = Logger.getLogger(Hooks.class);
    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8098;
    private static final String ZAP_APIKEY = null;
    Zap_Testing_Steps zapTestingSteps;

    @Before()
    public void setUpRegression() {
        zapTestingSteps = new Zap_Testing_Steps();
        logger.info("BEFORE ANNOTATION");
        Zap_Testing_Steps.zapScanner = new ZAProxyScanner
                (ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_APIKEY);
        // Start new session
        Zap_Testing_Steps.zapScanner.clear();
        logger.info("Started a new session: Scanner");

        // Create ZAP API client
        Zap_Testing_Steps.zapSpider = (Spider) Zap_Testing_Steps.zapScanner;
        logger.info("Created client to ZAP API");

        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();


        RestAssured.baseURI = "http://localhost:3000";
    }

    @Before("@UI")
    public void setUpUI() {
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
    }

    @Before("@WS")
    public void setUpWs() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Before("@DB")
    public void setUpDB() {
        // DBUtils.createConnection(HOST, DB_USERNAME, DB_PASSWORD);
    }


    @After
    public void tearDown(Scenario scenario) {
        logger.info("AFTER ANNOTATION");
        if (scenario.isFailed()) {
            logger.info("Make screenshot");
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        logger.info("Closing Test");
        // DBUtils.destroy();
        //System.out.println("Closing driver");
        Driver.closeDriver();
    }

}
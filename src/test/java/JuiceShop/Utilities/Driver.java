package JuiceShop.Utilities;

import JuiceShop.Step_Defenitions.Zap_Testing_Steps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {


    private Driver() {

    }


    private static URL url = null;
    private static DesiredCapabilities desiredCapabilities;
    //private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Driver.class);


    public static WebDriver get() {
        if (driver == null) {
            logger.info("driver was empty");

            String browser = System.getProperty("browser");
            if (browser == null) {
                logger.info("if browser variable was empty, assign browser thru Config reader" );
                browser = ConfigurationReader.get("firefoxZap");
            }
            switch (browser) {
                case "chrome":
                    logger.info("Classic Chrome" );
                    WebDriverManager.chromedriver().setup();
                    //driverPool.set(new ChromeDriver());
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    logger.info("Headless Chrome" );
                    WebDriverManager.chromedriver().setup();
                    //driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox":
                    logger.info("Classic FireFox" );
                    WebDriverManager.firefoxdriver().setup();
                    //driverPool.set(new FirefoxDriver());
                    //C:\Users\DKALININ\.m2\repository\webdriver\geckodriver\win64\0.26.0\geckodriver.exe
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    logger.info("Firefox headless" );
                    WebDriverManager.firefoxdriver().setup();
                    //driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                case "safari":
                    if (!System.getProperty("os.name" ).toLowerCase().contains("mac" ))
                        throw new WebDriverException("Your OS doesnt support Safari" );
                   WebDriverManager.getInstance(SafariDriver.class).setup();
                    //driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        url = new URL("http://localhost:4444/wd/hub" );
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setBrowserName("chrome" );
                    desiredCapabilities.setVersion("0.0.0.0" );
                    desiredCapabilities.setPlatform(Platform.ANY);
                    //driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    driver = new RemoteWebDriver(url, desiredCapabilities);
                    break;
                //mvn test -Dcucumber.options="--tags @" -Dbrowser=firefox,
                //mvn test -Dcucumber.options="--tags @" -Dbrowser=remote_firefox

                case "remote_firefox":
                    try {
                        url = new URL("" );
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setBrowserName("firefox" );
                    desiredCapabilities.setVersion("0.0.0.0" );
                    desiredCapabilities.setPlatform(Platform.ANY);
                    //driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    driver = new RemoteWebDriver(url, desiredCapabilities);
                    break;

                case "chrome_Zap":
                    logger.info("Zap Chrome Driver setup" );

                    desiredCapabilities = DesiredCapabilities.chrome();
                    desiredCapabilities.setCapability("proxy", Zap_Testing_Steps.createZapProxyConfiguration() );
                    // System.getProperty("webdriver.chrome.driver", path);
                    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    WebDriverManager.chromedriver().setup();
                    //desiredCapabilities = new DesiredCapabilities();
                    // desiredCapabilities.setBrowserName("chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.merge(desiredCapabilities);
                    //driverPool.set(new ChromeDriver(chromeOptions));
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox_Zap":


                    logger.info("Zap Firefox Driver setup" );
                    desiredCapabilities = DesiredCapabilities.firefox();
                    desiredCapabilities.setCapability("proxy", Zap_Testing_Steps.createZapProxyConfiguration());
                    // System.getProperty("webdriver.gecko.driver", path);
                    WebDriverManager.firefoxdriver().setup();
                    //desiredCapabilities.setBrowserName("firefox");
                    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.merge(desiredCapabilities);
                    // driverPool.set(new FirefoxDriver(firefoxOptions));
                    driver = (new FirefoxDriver(firefoxOptions));
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        logger.info("Closing driver" );
//        if (driverPool.get() != null) {
//            driverPool.get().quit();
//            driverPool.remove();
//        }
        if (driver != null) {
            driver.quit();
            driver=null;
        }

    }

    public static WebDriver ZapChromeDriver(Proxy proxy) {
        logger.info("Zap Chrome Driver setup" );
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("proxy", proxy);
        // System.getProperty("webdriver.chrome.driver", path);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(capabilities);
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver ZapFirefoxDriver(Proxy proxy) {
        logger.info("Zap Firefox Driver setup" );
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("proxy", proxy);
        // System.getProperty("webdriver.gecko.driver", path);
        WebDriverManager.firefoxdriver().setup();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.merge(capabilities);
        return new FirefoxDriver(firefoxOptions);
    }


}

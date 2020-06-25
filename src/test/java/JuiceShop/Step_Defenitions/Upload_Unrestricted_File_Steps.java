package JuiceShop.Step_Defenitions;

import JuiceShop.Pages.ComplaintPage;
import JuiceShop.Pages.LoginPage;
import JuiceShop.Pages.MainPage;
import JuiceShop.Utilities.ConfigurationReader;
import JuiceShop.Utilities.MainTools;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;


public class Upload_Unrestricted_File_Steps {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    ComplaintPage complaintPage = new ComplaintPage();
    private static final Logger logger= LogManager.getLogger(Upload_Unrestricted_File_Steps.class);

    @Then("Login as a user")
    public void login_as_a_user() throws InterruptedException {

        logger.info("Open login page");
        mainPage.accountButton.click();
        Thread.sleep(1000);
        mainPage.LoginButton.click();
        Thread.sleep(1000);
        logger.info("Enter Username");
        loginPage.emailField.sendKeys("test1@mail.com");
        Thread.sleep(1000);
        logger.info("Enter password");
        loginPage.passwordField.sendKeys(ConfigurationReader.get("Password"));
        Thread.sleep(1000);
        loginPage.loginButton.click();

    }

    @Then("Open Complaint page")
    public void open_Complaint_page() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("Click on dropdown");
        mainPage.dropDown.click();
        Thread.sleep(1000);
        logger.info("Click on Complain page");
        mainPage.complaintPage.click();
    }

    @Then("Write comments in comment field")
    public void write_comments_in_comment_field() {
        complaintPage.complaintMessage.sendKeys("ABCD");
    }

    @Then("Upload zipbomb file {string}")
    public void upload_zipbomb_file(String str) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("Upload file");
        complaintPage.chooseFile.sendKeys(str);
        MainTools.waitForClickablility(complaintPage.submitButton, 5);
        Thread.sleep(1000);
        logger.info("Click on submit button");
        complaintPage.submitButton.click();
    }

    @Then("Check that file was succesfuly uploaded")
    public void check_that_file_was_succesfuly_uploaded() throws InterruptedException {
        logger.info("Check confirmation message");
        Assert.assertTrue(complaintPage.confirmation.isDisplayed());
        Thread.sleep(3000);
    }
}


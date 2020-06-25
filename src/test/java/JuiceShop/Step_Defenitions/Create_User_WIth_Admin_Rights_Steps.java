package JuiceShop.Step_Defenitions;

import JuiceShop.POJOS.Registration;
import JuiceShop.Pages.AdministrationPage;
import JuiceShop.Pages.LoginPage;
import JuiceShop.Pages.MainPage;
import JuiceShop.Utilities.Driver;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static io.restassured.RestAssured.given;

public class Create_User_WIth_Admin_Rights_Steps {
    static String email = Faker.instance().name().firstName() + "@gmail.com";
    static String password = "admin";
    static String role = "admin";
    static Response response;
    MainPage mainPage;
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(Create_User_WIth_Admin_Rights_Steps.class);


    @Then("Send this JSON file with POST method to endpoint {string}")
    public void send_this_JSON_file_with_POST_method_to_endpoint(String string) throws IOException, InterruptedException {
        Thread.sleep(2000);
        logger.info("Prepeare Gson");
        Gson gson = new Gson();
        logger.info("To do serialize");
        Registration registration = new Registration(email, password, role);
        FileWriter writer = new FileWriter("src\\test\\Resources\\Regstration_with_admin_rights.json");
        File jsonFile = new File("src\\test\\Resources\\Regstration_with_admin_rights.json");
        logger.info("Write java object ot JSON file");
        gson.toJson(registration, writer);
        writer.flush();
        writer.close();
        logger.info("Make a POST method with that JSON file");
        response = given().log().all().contentType(ContentType.JSON).body(jsonFile).post("/api/users");
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(Integer int1) throws InterruptedException {
        response.prettyPeek();
        System.out.println(response.then().statusCode(int1));
        Thread.sleep(2000);
    }

    @Then("Login as a user with same email and password")
    public void login_as_a_user_with_same_email_and_password() throws InterruptedException {

        mainPage = new MainPage();
        Thread.sleep(2000);
        logger.info("Login as a created user");
        mainPage.accountButton.click();
        Thread.sleep(2000);
        mainPage.LoginButton.click();
        loginPage = new LoginPage();
        loginPage.emailField.sendKeys(email);
        loginPage.passwordField.sendKeys(password);
        Thread.sleep(2000);
        loginPage.loginButton.click();
    }

    @Then("Open admin url {string}")
    public void open_admin_url(String adminURL) throws InterruptedException {
        logger.info("Open admin URL");
        Thread.sleep(1000);
        Driver.get().get(adminURL);
        Thread.sleep(2000);
    }

    @Then("Check Administration title is displayed")
    public void Check_Administration_title_is_displaed() throws InterruptedException {
        AdministrationPage administrationPage = new AdministrationPage();
        Alert alert;
        Thread.sleep(3000);
        alert=Driver.get().switchTo().alert();
        Thread.sleep(2000);
        alert.accept();
        Assert.assertTrue(administrationPage.AdministratorTitle.isDisplayed());
    }

}

package JuiceShop.Step_Defenitions;

import JuiceShop.Pages.FeedbackPage;
import JuiceShop.Pages.MainPage;
import JuiceShop.Utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Give_Zero_Stars_Feedback_Steps {
    MainPage mainPage = new MainPage();
    FeedbackPage feedbackPage = new FeedbackPage();
    private static final Logger logger = LogManager.getLogger(Give_Zero_Stars_Feedback_Steps.class);

    @Then("Open Feedback Page")
    public void open_Feedback_Page() throws InterruptedException {
        logger.info("Open customer Feedback page");
        Thread.sleep(2000);
        mainPage.dropDown.click();
        Thread.sleep(2000);
        mainPage.customerFeedback.click();
    }

    @Then("Give a comment")
    public void give_a_comment() throws InterruptedException {
        logger.info("Write a comment");
        Thread.sleep(2000);
        feedbackPage.commetnFiled.sendKeys("abcd");
    }


    @Then("Fill up captcha")
    public void fill_up_captcha() throws ScriptException, InterruptedException {
        logger.info("Manipulate with captcha");
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String captchaText = feedbackPage.Captcha.getText();
        feedbackPage.captchaControl.sendKeys(engine.eval(captchaText).toString());
        Thread.sleep(2000);

    }

    @When("Modify attribute disable on enable of Submit button in DOM")
    public void modify_attribute_disable_on_enable_of_Submit_button_in_DOM() throws InterruptedException {
        logger.info("Modify attribute in DOM and enable submit button");
    JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("document.querySelector(\"button[disabled='true']\").removeAttribute('disabled')");
        jse.executeScript("document.getElementById(\"submitButton\").setAttribute('enabled', 'true')");
        Thread.sleep(2000);
    }

    @When("Click on Submit button")
    public void click_on_Submit_button() throws InterruptedException {
        logger.info("Submit feedback");
        Thread.sleep(2000);
        feedbackPage.Submit.click();
        Thread.sleep(3000);

    }


}

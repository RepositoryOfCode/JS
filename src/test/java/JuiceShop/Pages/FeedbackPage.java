package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeedbackPage extends BasePage{

    public FeedbackPage(){
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(xpath = "//code[@id=\"captcha\"]")
    public WebElement Captcha;

    @FindBy(xpath = "//input[@id=\"captchaControl\"]")
    public WebElement captchaControl;

    @FindBy(id = "comment")
    public WebElement commetnFiled;

    @FindBy(xpath = "//div[@class='br-unit ng-star-inserted']")
    public WebElement oneStar;

    @FindBy(id = "submitButton")
    public WebElement Submit;

    @FindBy(xpath = "//button[@disabled='true']")
    public WebElement SubmitDisable;




}

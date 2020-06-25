package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage extends BasePage {

    public RegistrationPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id="emailControl")
    public WebElement emailControl;

    @FindBy(id = "passwordControl")
    public WebElement passwordControl;

    @FindBy(id = "repeatPasswordControl")
    public WebElement repeatPasswordControl;

    @FindBy(xpath = "//span[@class='mat-option-text']")
    public List<WebElement> securityQuestionsList;

    @FindBy(xpath = "//mat-select[@role='listbox']")
    public WebElement securityQueestions;

    @FindBy(id="securityAnswerControl")
    public WebElement securityAnswerControl;

    @FindBy(id="registerButton")
    public WebElement registerButton;

    @FindBy(xpath = "//span[.='Registration completed successfully. You can now log in.']")
    public WebElement coonfirmationOfRegistration;


}

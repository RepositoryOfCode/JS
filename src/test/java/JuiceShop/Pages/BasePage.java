package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "navbarAccount")
    public WebElement accountButton;

    @FindBy(id = "navbarLoginButton")
    public WebElement LoginButton;

    @FindBy(xpath = "//button[@aria-label=\'Back to homepage\']")
    public WebElement toHomePage;

    @FindBy(xpath = "//mat-icon[.=' search ']")
    public WebElement openSearch;

    @FindBy(xpath = "//input[@id=\"mat-input-0\"]")
    public WebElement searchField;

    @FindBy(xpath = "//button[@aria-describedby=\"cdk-describedby-message-0\"]")
    public WebElement dropDown;

    @FindBy(xpath = "//button[@aria-label='Go to user profile']")
    public WebElement userProfile;

    @FindBy(xpath = "//span[.=\" Customer Feedback \"]")
    public WebElement customerFeedback;

    @FindBy(xpath = "//a[@href='#/complain']")
    public WebElement complaintPage;





}

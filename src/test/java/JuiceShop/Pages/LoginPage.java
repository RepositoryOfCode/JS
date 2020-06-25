package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id= "email")
    public WebElement emailField;

    @FindBy(id="password")
    public WebElement passwordField;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@routerlink='/forgot-password']")
    public WebElement forgotPassword;

    @FindBy(id = "newCustomerLink")
    public WebElement newCustomer;



}

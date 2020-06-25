package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdministrationPage {

    public AdministrationPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//h1[.='Administration']")
    public WebElement AdministratorTitle;
}

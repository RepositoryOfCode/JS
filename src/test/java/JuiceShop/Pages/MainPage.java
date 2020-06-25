package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public MainPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//button[@aria-label='Close Welcome Banner']")
    public WebElement WelcomeBannerDismiss;


}

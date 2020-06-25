package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    public SearchResultPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//iframe[@src=\"javascript:alert(`xss`)\"]")
    public WebElement xssInDOM;


}

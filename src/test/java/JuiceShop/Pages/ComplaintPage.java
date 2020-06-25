package JuiceShop.Pages;

import JuiceShop.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComplaintPage extends BasePage{
    public ComplaintPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id="complaintMessage")
    public WebElement complaintMessage;

    @FindBy(xpath="//input[@type='file']")
    public WebElement chooseFile;

    @FindBy(id = "submitButton")
    public WebElement submitButton;

    @FindBy(xpath = "//div[.='File too large. Maximum 100 KB allowed. ']")
    public WebElement errorAboutFileSize;

    @FindBy(xpath = "//div[.='Forbidden file type. Only PDF, ZIP allowed. ']")
    public WebElement errorAboutFileType;

    @FindBy(xpath = "//div[@class='confirmation']")
    public WebElement confirmation;
//Customer support will get in touch with you soon! Your complaint reference is #3

}

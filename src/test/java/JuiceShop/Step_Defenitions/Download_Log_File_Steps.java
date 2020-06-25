package JuiceShop.Step_Defenitions;

import JuiceShop.Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.Scanner;

public class Download_Log_File_Steps {
    private static final Logger logger= LogManager.getLogger(Download_Log_File_Steps.class);
    @Given("Download log file thru link {string}.")
    public void download_log_file_thru_link(String string) throws InterruptedException {
        logger.info("Open page with logs");
        Driver.get().get(string);
        Actions actions = new Actions(Driver.get());
        WebElement element = Driver.get().findElement(By.xpath("//span[.='access.log.2020-06-04']"));
        logger.info("Download it");
        Thread.sleep(1000);
        actions.click(element).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();

    }

    @Then("Open that file")
    public void open_that_file() throws InterruptedException {
        logger.info("Find that file on your HDD");
        Thread.sleep(1000);
        File fr = new File("C:\\Users\\DKALININ\\Downloads\\access.log.2020-06-04");
        String str = "";
        try {
            logger.info("Read the log from the file");
            Scanner scanner = new Scanner(fr);
            str = scanner.nextLine();
            System.out.println(str);
            Thread.sleep(3000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            str = e.getLocalizedMessage();
        }


    }


}
package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.JsonOrCSVDataReader;
import java.io.IOException;
import java.util.*;
import static utils.ElementUtils.*;
import static utils.ElementUtils.getDynamicXpath;
import static utils.WaitUtils.waitForVisibility;

public class DP1_Page {
    List<String> arrList = new ArrayList<>();
    String jSONfl = ConfigReader.getInstance().getProperty("jsonFilePath");
    JsonOrCSVDataReader reader = new JsonOrCSVDataReader();
    List<String> actualSlides = reader.readSlidesFromJson().getTicketsSlides();

    private WebDriver driver;
    int timeout = ConfigReader.getInstance().getIntProperty("Timeout");
    private int explicitWait;
    private Properties prop;

    By topHyperlinks = By.xpath("//*[@aria-label='header-primary-menu']//preceding-sibling::*[contains(@href,'%s')]");
    By listOfValuesUnderHyperlink = By.xpath("//*[@aria-label='header-primary-menu']//preceding-sibling::*[contains(@href,'%s')]/..//ul/li/a");
    JsonOrCSVDataReader jsonOrCSVDataReader = new JsonOrCSVDataReader();
   
    public DP1_Page(WebDriver driver, Properties prop) throws IOException {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.explicitWait = Integer.parseInt(prop.getProperty("explicit.wait"));
        this.prop = prop;
    }
    
    public void navigateToHyperlink(String hyperlinkName){
        waitForVisibility(getDynamicXpath(topHyperlinks, hyperlinkName), explicitWait);
        mouseHover(getDynamicXpath(topHyperlinks, hyperlinkName));
    }

    public void fetchValuesfromHyperlink(String hyperlinkName) {
        List<WebElement> listofValues = getListOfElements(listOfValuesUnderHyperlink, hyperlinkName);
        for (WebElement str : listofValues) {
            arrList.add(str.getAttribute("title"));
        }
        System.out.println(arrList);
       if((actualSlides.stream().allMatch(arrList::contains)) && (arrList.stream().allMatch(actualSlides::contains))){
           System.out.println("Tickets slides match!");   
       }
    }
}

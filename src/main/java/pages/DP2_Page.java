package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ConfigReader;
import utils.JsonOrCSVDataReader;
import java.io.IOException;
import java.util.*;
import static utils.ElementUtils.*;

public class DP2_Page {
    private WebDriver driver;
    int timeout = ConfigReader.getIntProperty("Timeout");


    By footerHyperlinks = By.xpath("//footer[@role='contentinfo']//*[contains(@class,'justify-start')]//ul//li//a");
    By footerTag = By.xpath("//footer");
    JsonOrCSVDataReader jsonOrCSVDataReader = new JsonOrCSVDataReader();

    public DP2_Page(WebDriver driver) {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public Set<Object> captureFooterHyperlinksAndValidateDuplicates() {
        scrollToFooterToEnd();
        List<WebElement> actSizeOfFeeds = getListOfElements(footerHyperlinks, "");
        Set<Object> uniqueLinks = new LinkedHashSet<>();
        Set<Object> duplicateLinks = new LinkedHashSet<>();
        for (WebElement element : actSizeOfFeeds) {
            String linkText = element.getText().trim();
            // Skip empty links
            if (linkText.isEmpty()) continue;
            // If add() returns false, it's a duplicate
            if (!uniqueLinks.add(linkText)) {
                duplicateLinks.add(linkText);
            }
        }
// Print results
        System.out.println("Unique links: " + uniqueLinks);
        System.out.println("Duplicate links: " + duplicateLinks);
// Optionally assert no duplicates
        Assert.assertTrue(duplicateLinks.isEmpty(), "Duplicate footer links found: " + duplicateLinks);
        return uniqueLinks;
    }

    public void storesIntoAnyFile() throws IOException {
        jsonOrCSVDataReader.JsonOrCSVDataReaderToStore(captureFooterHyperlinksAndValidateDuplicates());
    }
}

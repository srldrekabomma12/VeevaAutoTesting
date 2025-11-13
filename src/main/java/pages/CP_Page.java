package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.LoggerHelper;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static utils.ElementUtils.*;
import static utils.WaitUtils.*;


public class CP_Page {
    static WebDriver driver;
    private int timeout;
    private int explicitWait;
    private Properties prop;

    public CP_Page(WebDriver driver, Properties prop) {
        this.driver = driver;
        this.prop = prop;
        this.timeout = Integer.parseInt(prop.getProperty("Timeout"));
        this.explicitWait = Integer.parseInt(prop.getProperty("explicit.wait"));
    }

    private final Logger log = LoggerHelper.getLogger(CP_Page.class);

    By dotMenu = By.xpath("//*[@aria-label='%s']");
    By optionFromMenu = By.xpath("//*[@aria-label='%s']/parent::*//nav//li/a[normalize-space(text())='%s']");
    By numOfFeedsBasedOnDays = By.xpath("//h3[text()='%s']/ancestor::div[contains(@class,'customColumns')]//*[contains(@data-testid,'-article-link')]/../..//time/span");
    By videoOrNewsFeeds = By.xpath("//h3[text()='%s']/ancestor::div[contains(@class,'customColumns')]//*[contains(@data-testid,'-article-link')]");

    By popUpXBtn = By.xpath("//*[contains(@class,'%s')]/div/div[text()='x']");
    By nBALogo = By.xpath("//img[@alt='%s']");

    By newsTitle = By.xpath("//h3[text()='NEWS']");
    By dialog = By.xpath("//*[@role='dialog']");

    public void hoverOnMenu(String menuName) {
        waitForVisibility(getDynamicXpath(dotMenu, menuName), explicitWait);
        mouseHover(getDynamicXpath(dotMenu, menuName));
    }

    public void selectOptionFromMenu(String menuName, String menuOption) {
        waitForElementClickable(getDynamicXpath(optionFromMenu, menuOption, menuName), explicitWait);
        normalClickEle(getDynamicXpath(optionFromMenu, menuOption, menuName), timeout);
        log.info("Selected the option " + menuOption + " from the menu " + menuName);
    }

    public void closePopupIfPresent(String popUpName, String logoTitle) throws AWTException, InterruptedException {
        try {
            waitForVisibility(dialog, explicitWait);
            normalClickEle(getDynamicXpath(popUpXBtn, popUpName), timeout);
            Assert.assertTrue(waitForVisibility(getDynamicXpath(popUpXBtn, popUpName), timeout).isDisplayed());
            System.out.println("Popup displayed");
            log.info("clicked on 'X' button on popup & NBA logo displayed");
        } catch (Exception e) {
            System.out.println("Popup not displayed");
            log.info("Popup not displayed");
        }
    }

    public void validateUrl(String url) {
        Assert.assertEquals(driver.getCurrentUrl(), url);
        log.info("Validated the URL and Title");
    }

    public void validateVideosOrNewsFeeds(String feedType, String count) {
        waitForVisibility(getDynamicXpath(newsTitle, ""), timeout);
        scrollToEle(getDynamicXpath(newsTitle));
        int actSize = getListOfElements(videoOrNewsFeeds, feedType).size();
        Assert.assertEquals(Integer.parseInt(count), actSize);
    }

    public void validateCountOfFeedsbasedOnDays(String feedType, String daysOrHrsCond, String count) {
        ArrayList<String> daysOrHrsCnt = new ArrayList<>();
        waitForVisibility(getDynamicXpath(newsTitle, ""), timeout);
        List<WebElement> actSizeOfFeeds = getListOfElements(numOfFeedsBasedOnDays, feedType);
        for (WebElement str : actSizeOfFeeds) {
            if (str.getText().isEmpty()) {
                continue;
            }
            if (daysOrHrsCond.charAt(1) == 'd' && str.getText().charAt(1) == 'd' && Integer.parseInt(str.getText().substring(0, 1)) >= Integer.parseInt(daysOrHrsCond.substring(0, 1))) {
                daysOrHrsCnt.add(str.getText());
            }
        }
        Assert.assertTrue(daysOrHrsCnt.size() == Integer.parseInt(count));
    }
}

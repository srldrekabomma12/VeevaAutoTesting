package stepDefns;

import driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CP_Page;
import base.Hooks;
import utils.ExtentTestManager;

import java.awt.*;
import static utils.WaitUtils.*;

public class CPHomeSteps{
    private CP_Page cP_Page;

    @Given("^user is on login page for url \"([^\"]*)\"$")
    public void userLaunches(String url)  {
        cP_Page = new CP_Page(DriverManager.getDriver(),Hooks.prop);
        DriverManager.getDriver().get(Hooks.prop.getProperty(url));
        waitForPageToLoad(30);
    }
    @When("user mousehover to \"([^\"]*)\"$")
    public void navigate_to_videos(String menuName) {
        cP_Page.hoverOnMenu(menuName);
    }
    @And("^user close popup \"([^\"]*)\" if present and validate title/logo \"([^\"]*)\" after closing popup$")
    public void closePopupIfPresent(String popUpName,String logoTitle) throws AWTException, InterruptedException {
        cP_Page.closePopupIfPresent(popUpName,logoTitle);
    }
    @And("^user selects \"([^\"]*)\" from the menu \"([^\"]*)\"$")
    public void selectOptionszfromMenu(String menuName,String menuOption) {
        cP_Page.selectOptionFromMenu(menuName,menuOption);
        ExtentTestManager.getTest().info("Selected the option "+menuOption+" from the menu "+menuName);
    }

    @Then("^user validates list of \"([^\"]*)\" Feeds having \"([^\"]*)\" count$")
    public void validateVideosOrNewsFeeds(String feedType, String count) {
        cP_Page.validateVideosOrNewsFeeds(feedType,count);
        ExtentTestManager.getTest().info("validates list of Hyperlinks");
    }

    @Then("^user validates count of \"([^\"]*)\" feeds greater than \"([^\"]*)\" are \"([^\"]*)\"$")
    public void validateCountOfFeedsbasedOnDays(String feedType,String daysOrHrsCond,String count) {
        cP_Page.validateCountOfFeedsbasedOnDays(feedType,daysOrHrsCond,count);
        ExtentTestManager.getTest().info("validates count of feeds based on days");
    }
}

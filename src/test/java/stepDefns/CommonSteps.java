package stepDefns;

import base.Hooks;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CP_Page;
import utils.ConfigReader;
import utils.ExtentManager;
//import utils.ExtentTestManager;

import static utils.WaitUtils.waitForPageToLoad;

public class CommonSteps {

    private final WebDriver driver;
    private final ConfigReader config;

    public CommonSteps() {
        this.driver = DriverManager.getDriver();
        this.config = ConfigReader.getInstance();
    }

    @Then("^user validates url navigates to \"(.*)\"$")
    public void user_validates_url_navigates_to(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Thread ID: " + Thread.currentThread().getId() + " | Expected: " + expectedUrl + " | Actual: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "❌ URL mismatch!");
        System.out.println("URL validated successfully: " + actualUrl);
        ExtentManager.getTest().info("launched URL : " + actualUrl);
    }

    @Given("^user is on login page for url \"([^\"]*)\"$")
    public void userLaunches(String url)  {
//        cP_Page = new CP_Page(DriverManager.getDriver(), Hooks.prop);
        DriverManager.getDriver().get(config.getProperty(url));
        waitForPageToLoad(30);
    }
}

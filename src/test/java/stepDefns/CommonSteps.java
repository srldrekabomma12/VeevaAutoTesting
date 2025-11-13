package stepDefns;

import driver.DriverManager;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ExtentTestManager;

public class CommonSteps {

    private final WebDriver driver;

    public CommonSteps() {
        this.driver = DriverManager.getDriver();
    }

    @Then("^user validates url navigates to \"(.*)\"$")
    public void user_validates_url_navigates_to(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Thread ID: " + Thread.currentThread().getId() + " | Expected: " + expectedUrl + " | Actual: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "❌ URL mismatch!");
        System.out.println("URL validated successfully: " + actualUrl);
        ExtentTestManager.getTest().info("launched URL : " + actualUrl);
    }
}

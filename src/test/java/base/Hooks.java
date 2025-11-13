package base;

import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ExtentTestManager;

public class Hooks {

    private DriverManager driverFactory;
    private WebDriver driver;
    public static Properties prop;

    @Before(order = 0)
    public void browserSetUp(Scenario scenario) {
        driver = DriverManager.getDriver();
        ExtentTest test = ExtentTestManager.getExtent().createTest(scenario.getName());
        ExtentTestManager.setTest(test);
        test.info("Starting Scenario: " + scenario.getName());
        String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                driver = new FirefoxDriver(ffOptions);
                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
        }
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        ConfigReader configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void configProp() {
        ConfigReader configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @After(order = 0)
    public void quitBrowser() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            System.out.println(">> Quitting browser for Thread ID: " + Thread.currentThread().getId());
            driver.quit();
            DriverManager.unload();
            ExtentTestManager.getExtent().flush();// very important: clears ThreadLocal to avoid memory leaks
        }
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
        } else {
            ExtentTestManager.getTest().pass("Scenario passed");
        }
    }

}

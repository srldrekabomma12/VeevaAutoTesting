package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;  // ✅ important
import org.testng.annotations.*;
import utils.RetryListener;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefns", "base"},
        tags = "@TestCase4_for_DP2",
        plugin =  {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/html-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/rerun.txt"
        },
        monochrome = true
)
@Listeners(RetryListener.class)
public class DP2TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

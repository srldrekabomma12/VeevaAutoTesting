package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utils.RetryListener;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefns", "base"},
        tags = "@TestCase3_for_DP1",
        plugin =  {
                "pretty",
                "html:target/cucumber-reports/html",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:target/timeline-report",
                "rerun:target/rerun.txt"
        },
        monochrome = true
)
@Listeners(RetryListener.class)
public class DP1TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

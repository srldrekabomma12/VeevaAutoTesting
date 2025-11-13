package stepDefns;

import io.cucumber.java.en.Then;
import pages.DP2_Page;;

import java.io.IOException;

public class DP2HomeSteps {
    DP2_Page DP2Page;

    public DP2HomeSteps() {
        this.DP2Page = new DP2_Page(driver.DriverManager.getDriver());
    }

    @Then("^user capture footer hyperlinks and validate any Duplicate values are present$")
    public void captureFooterHyperlinksAndValidateDuplicates() {
        DP2Page.captureFooterHyperlinksAndValidateDuplicates();
    }

    @Then("^user stores all links into any file$")
    public void storesIntoAnyFile() throws IOException {
        DP2Page.storesIntoAnyFile();
    }
}

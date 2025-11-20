package stepDefns;

import base.Hooks;
import driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.DP1_Page;

import java.io.IOException;

public class DP1HomeSteps {
    DP1_Page DP1Page;
    private DP1_Page dP1_Page=new DP1_Page(DriverManager.getDriver(), Hooks.prop);

    public DP1HomeSteps() throws IOException {
    }


    @And("^user navigates to \"(.*)\" hyperlink$")
    public void clickOnHyperlink(String hyperlinkName){
        dP1_Page.navigateToHyperlink(hyperlinkName);
    }

    @Then("user fetches all values from particular hyperlink {string}")
    public void user_fetches_all_values_from_particular_hyperlink(String hyperlinkName) throws IOException {
        dP1_Page.fetchValuesfromHyperlink(hyperlinkName);
    }
    
}

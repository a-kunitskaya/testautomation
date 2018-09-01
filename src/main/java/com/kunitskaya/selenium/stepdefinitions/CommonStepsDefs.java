package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.base.selenium.Browser;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.assertTrue;

public class CommonStepsDefs {
    @When("^I close window$")
    public void iCloseWindow() {
        Browser.getInstance().closeCurrentWindow();
    }

    @Then("^alert is displayed$")
    public void alertIsDisplayed() {
        assertTrue(Browser.getInstance().isAlertDisplayed());
    }

    @When("^I accept alert$")
    public void iAcceptAlert() {
        Browser.getInstance().acceptAlert();
    }

}

package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.HelpPopup;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HelpPopupStepsDefs {

    private List<String> searchResults;
    private String searchInput;
    private SoftAssert softAssert = new SoftAssert();

    @When("I open help popup")
    public void iOpenHelpPopup() {
        NavigationOperations.goToHelpPopup();
    }

    @Then("^Help popup is opened$")
    public void helpPopupIsOpened() {
        softAssert.assertTrue(new HelpPopup().isDisplayed());
    }

    @When("^I search for ([\\w]+)$")
    public void iSearchForInput(String searchInput) {
        searchResults = UserOperations.searchFor(searchInput);
        this.searchInput = searchInput;
    }

    @Then("^I get corresponding search results$")
    public void iGetCorrespondingSearchResults() {
        for (String searchResult : searchResults) {
            softAssert.assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }
    }

    @When("^I clear search field$")
    public void iClearSearchField() {
        new HelpPopup().clearSearchField();
    }


    @And("^I switch to help popup$")
    public void iSwitchToHelpPopup() {
        new HelpPopup().switchToHelpPopupFrame();
    }
}

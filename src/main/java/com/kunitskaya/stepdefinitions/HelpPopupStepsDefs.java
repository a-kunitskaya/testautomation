package com.kunitskaya.stepdefinitions;

import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.pages.pf.HelpPopup;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class HelpPopupStepsDefs {

    private List<String> searchResults;
    private String searchInput;

    @When("I open help popup")
    public void iOpenHelpPopup() {
        NavigationOperations.goToHelpPopup();
    }

    @Then("^Help popup is opened$")
    public void helpPopupIsOpened() {
        assertTrue(new HelpPopup().isDisplayed());
    }

    @When("^I search for ([\\w]+)$")
    public void iSearchForInput(String searchInput) {
        searchResults = UserOperations.searchFor(searchInput);
        this.searchInput = searchInput;
    }

    @Then("^I get corresponding search results$")
    public void iGetCorrespondingSearchResults() {
        for (String searchResult : searchResults) {
            if (searchResult.contains(searchInput)) {
                assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
            }
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

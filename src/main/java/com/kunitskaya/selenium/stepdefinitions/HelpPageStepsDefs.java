package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.pages.pf.GmailHelpPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.testng.Assert.assertEquals;

public class HelpPageStepsDefs {

    @And("^I go to help page$")
    public void iGoToHelpPage() {
        NavigationOperations.goToHelpPage();
        Browser.getInstance().switchToLastOpenedWindow();
    }

    @Then("^Help page is displayed$")
    public void helpPageIsDisplayed() {
        assertEquals(new GmailHelpPage().getHeader(), GmailHelpPage.HELP_PAGE_HEADER);
        assertEquals(new GmailHelpPage().getTitle(), GmailHelpPage.HELP_PAGE_TITLE);
        assertEquals(new GmailHelpPage().getSearchFieldPlaceholder(), GmailHelpPage.HELP_PAGE_SEARCH_PLACEHOLDER);
    }
}

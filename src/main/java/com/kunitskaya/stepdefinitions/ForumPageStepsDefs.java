package com.kunitskaya.stepdefinitions;

import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.pages.pf.GmailHelpForumPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;

import static com.kunitskaya.pages.pf.GmailHelpForumPage.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ForumPageStepsDefs {

    @And("^I go to help forum page$")
    public void iGoToHelpForumPage() {
        NavigationOperations.goToHelpForum();
    }

    @Then("^Help forum page is displayed$")
    public void helpForumPageIsDisplayed() {
        assertTrue(new GmailHelpForumPage().isNewTopicButtonVisible());
        assertEquals(new GmailHelpForumPage().getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(new GmailHelpForumPage().getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(new GmailHelpForumPage().getTitle(), FORUM_PAGE_TITLE));
    }
}

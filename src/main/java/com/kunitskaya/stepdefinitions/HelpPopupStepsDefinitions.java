package com.kunitskaya.stepdefinitions;

import com.kunitskaya.base.Browser;
import com.kunitskaya.business.operations.NavigationOperations;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.pages.*;
import com.kunitskaya.test.TestDataProvider;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.kunitskaya.pages.FeedbackPopup.FEEDBACK_POPUP_HEADER;
import static com.kunitskaya.pages.FeedbackPopup.FEEDBACK_POPUP_PLACEHOLDER;
import static com.kunitskaya.pages.GmailHelpForumPage.*;
import static org.testng.Assert.*;

public class HelpPopupStepsDefinitions {
    private List<String> searchResults;
    private String searchInput;
    private String basePageWinHandle;

    @When("I open help popup")
    public void iOpenHelpPopup() {
        NavigationOperations.goToHelpPopup();
    }

    @Then("^Help popup is opened$")
    public void helpPopupIsOpened() {
        assertTrue(new HelpPopup().isDisplayed());
    }

    @When("^I search for ([\\w]+)$")
    public void iSearchFor(String searchInput) {
        searchResults = UserOperations.searchFor(searchInput);
        this.searchInput = searchInput;
    }

    @Then("^I get corresponding search results$")
    public void iGetCorrespondingSearchResults() {
        for (String searchResult : searchResults) {
            if (StringUtils.containsNone(searchResult, searchInput)) {
                continue;
            }
            assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }
    }

    @When("^I clear search field$")
    public void iClearSearchField() {
        new HelpPopup().clearSearchField();
    }

    @And("^I go to help page$")
    public void iGoToHelpPage() {
        NavigationOperations.goToHelpPage();
        basePageWinHandle = Browser.getInstance().getCurrentWindowHandle();
        Browser.getInstance().switchToLastOpenedWindow();
    }

    @Then("^Help page is displayed$")
    public void helpPageIsDisplayed() {
        assertEquals(new GmailHelpPage().getHeader(), GmailHelpPage.HELP_PAGE_HEADER);
        assertEquals(new GmailHelpPage().getTitle(), GmailHelpPage.HELP_PAGE_TITLE);
        assertEquals(new GmailHelpPage().getSearchFieldPlaceholder(), GmailHelpPage.HELP_PAGE_SEARCH_PLACEHOLDER);
    }

    @When("^I close window$")
    public void iCloseWindow() {
        Browser.getInstance().closeCurrentWindow();
    }

    @And("^I switch to mail page$")
    public void iSwitchToMailPage() {
        Browser.getInstance().switchToWindowHandle(basePageWinHandle);
    }

    @And("^I switch to help popup$")
    public void iSwitchToHelpPopup() {
        new HelpPopup().switchToHelpPopupFrame();
    }

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

    @And("^I open feedback popup$")
    public void iOpenFeedbackPopup() {
        NavigationOperations.goToFeedbackPopup();
    }

    @Then("^Feedback popup is displayed$")
    public void feedbackPopupIsDisplayed() {
        assertTrue(new FeedbackPopup().isSendFeedbackPopupDisplayed());
        assertTrue(new FeedbackPopup().isInputFieldDisplayed());
        assertTrue(new FeedbackPopup().isCancelButtonDisplayed() && new FeedbackPopup().isSendButtonDisplayed());
        assertEquals(new FeedbackPopup().getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(new FeedbackPopup().getInputFieldPlaceholder(), FEEDBACK_POPUP_PLACEHOLDER);
        assertEquals(new FeedbackPopup().isIncludeScreenshotCheckboxChecked(), TestDataProvider.getDefaultFeedback().isWithScreenshot());

    }

    @When("^I leave feedback and cancel it$")
    public void iLeaveFeedbackAndCancelIt() {
        UserOperations.leaveFeedback(TestDataProvider.getDefaultFeedback(), true);
    }

    @Then("^Feedback popup is not displayed$")
    public void feedbackPopupIsNotDisplayed() {
        assertFalse(new FeedbackPopup().isSendFeedbackPopupDisplayed());
    }

    @And("^Mail page is displayed$")
    public void mailPageIsDisplayed() {
        assertTrue(new MailPage().isAccountIconVisible());
    }
}

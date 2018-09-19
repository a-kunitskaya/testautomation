package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.objects.Feedback;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kunitskaya.selenium.pages.pf.FeedbackPopup.FEEDBACK_POPUP_HEADER;
import static com.kunitskaya.selenium.pages.pf.FeedbackPopup.FEEDBACK_POPUP_PLACEHOLDER;
import static com.kunitskaya.selenium.pages.pf.GmailHelpForumPage.*;
import static org.testng.Assert.*;

public class HelpPopupTest extends SeleniumBaseTest {

    @BeforeClass
    public void logIn() {
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up")
    public void validateHelpPopup() {
        NavigationOperations.goToHelpPopup();
        HelpPopup helpPopup = new HelpPopup();
        assertTrue(helpPopup.isDisplayed());

        String searchInput = "Change";
        List<String> searchResults = UserOperations.searchFor(searchInput);

        for (String searchResult : searchResults) {
            assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }

        helpPopup.clearSearchField();
        NavigationOperations.goToHelpPage();
        GmailHelpPage helpPage = new GmailHelpPage();

        String basePage = browser.getCurrentWindowHandle();
        browser.switchToLastOpenedWindow();

        assertEquals(helpPage.getHeader(), GmailHelpPage.HELP_PAGE_HEADER);
        assertEquals(helpPage.getTitle(), GmailHelpPage.HELP_PAGE_TITLE);
        assertEquals(helpPage.getSearchFieldPlaceholder(), GmailHelpPage.HELP_PAGE_SEARCH_PLACEHOLDER);

        browser.closeCurrentWindow();
        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

        NavigationOperations.goToHelpForum();
        GmailHelpForumPage forumPage = new GmailHelpForumPage();

        assertTrue(forumPage.isNewTopicButtonVisible());
        assertEquals(forumPage.getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(forumPage.getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(forumPage.getTitle(), FORUM_PAGE_TITLE));

        browser.closeCurrentWindow();
        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

        NavigationOperations.goToFeedbackPopup();
        Feedback feedback = TestDataProvider.getDefaultFeedback();
        FeedbackPopup feedbackPopup = new FeedbackPopup();

        assertTrue(feedbackPopup.isSendFeedbackPopupDisplayed());
        assertTrue(feedbackPopup.isInputFieldDisplayed());
        assertTrue(feedbackPopup.isCancelButtonDisplayed() && feedbackPopup.isSendButtonDisplayed());
        assertEquals(feedbackPopup.getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(feedbackPopup.getInputFieldPlaceholder(), FEEDBACK_POPUP_PLACEHOLDER);
        assertEquals(feedbackPopup.isIncludeScreenshotCheckboxChecked(), feedback.isWithScreenshot());

        UserOperations.leaveFeedback(feedback, true);

        assertTrue(new MailPage().isAccountIconVisible());
        assertFalse(feedbackPopup.isSendFeedbackPopupDisplayed());
    }
}

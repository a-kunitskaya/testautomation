package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.pf.NavigaionOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.kunitskaya.pages.pf.FeedbackPopup.FEEDBACK_POPUP_HEADER;
import static com.kunitskaya.pages.pf.FeedbackPopup.FEEDBACK_POPUP_INPUT_PLACEHOLDER;
import static com.kunitskaya.pages.pf.GmailHelpForumPage.*;
import static org.testng.Assert.*;

public class HelpPopupTest extends BaseTest {

    @BeforeClass
    public void logIn() {
        NavigaionOperations.goToLoginPage();
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up")
    public void validateHelpPopup() {
        NavigaionOperations.goToHelpPopup();
        HelpPopup helpPopup = new HelpPopup();
        assertTrue(helpPopup.isDisplayed());

        String searchInput = "Change";
        List<String> searchResults = UserOperations.searchFor(searchInput);

        for (String searchResult : searchResults) {
            System.out.println(searchResult);
            assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }

        UserOperations.clearSearchField();
        NavigaionOperations.goToHelpPage();
        GmailHelpPage helpPage = new GmailHelpPage();

        String basePage = browser.getCurrentWindowHandle();
        browser.switchToLastOpenedWindow();

        assertEquals(helpPage.getHeader(), GmailHelpPage.HELP_PAGE_HEADER);
        assertEquals(helpPage.getTitle(), GmailHelpPage.HELP_PAGE_TITLE);
        assertEquals(helpPage.getSearchFieldPlaceholder(), GmailHelpPage.HELP_PAGE_SEARCH_PLACEHOLDER);

        browser.closeCurrentWindow();
        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

        NavigaionOperations.goToHelpForum();
        GmailHelpForumPage forumPage = new GmailHelpForumPage();

        assertTrue(forumPage.isNewTopicButtonVisible());
        assertEquals(forumPage.getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(forumPage.getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(forumPage.getTitle(), FORUM_PAGE_TITLE));

        browser.closeCurrentWindow();
        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

        NavigaionOperations.goToFeedbackPopup();
        FeedbackPopup feedbackPopup = new FeedbackPopup();

        assertTrue(feedbackPopup.isSendFeedbackPopupDisplayed());
        assertTrue(feedbackPopup.isInputFieldDisplayed());
        assertTrue(feedbackPopup.isCancelButtonDisplayed() && feedbackPopup.isSendButtonDisplayed());
        assertEquals(feedbackPopup.getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(feedbackPopup.getInputFieldPlaceholder(), FEEDBACK_POPUP_INPUT_PLACEHOLDER);
        assertTrue(feedbackPopup.isIncludeScreenshotCheckboxChecked());

        UserOperations.makeFeedbackScreenshot(300, 300);
        UserOperations.cancelFeedback();

        assertTrue(new MailPage().isAccountIconVisible());
        assertFalse(feedbackPopup.isSendFeedbackPopupDisplayed());
    }
}

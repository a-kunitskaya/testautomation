package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.NavigaionOperations;
import com.kunitskaya.business.operations.UserOperations;
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
        UserOperations.logIn(user);
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up")
    public void validateHelpPopup() {
        String searchInput = "Change";
//        MailPage mailPage = new MailPage();
//        HelpPopup helpPopup = mailPage.clickSettingsButton()
//                                      .clickHelpSettingsOption();

        NavigaionOperations.goToHelpPopup();

        HelpPopup helpPopup = new HelpPopup();
        assertTrue(helpPopup.isDisplayed());

        List<String> searchResults = helpPopup.enterSearchCriteria(searchInput)
                                              .getSearchResults();

        for (String searchResult : searchResults) {
            System.out.println(searchResult);
            assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }

        helpPopup.clearSearchField();
//        .clickBrowseAllArticlesLink();

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

//        GmailHelpForumPage forumPage = helpPopup.clickVisitHelpForumLink();
//        browser.switchToLastOpenedWindow();

        NavigaionOperations.goToHelpForum();
        GmailHelpForumPage forumPage = new GmailHelpForumPage();

        assertTrue(forumPage.isNewTopicButtonVisible());
        assertEquals(forumPage.getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(forumPage.getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(forumPage.getTitle(), FORUM_PAGE_TITLE));

        browser.closeCurrentWindow();
        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

//        FeedbackPopup feedbackPopup = helpPopup.clickSendFeedBackButton()
//                                               .switchToFeedbackFrame();

        NavigaionOperations.goToFeedbackPopup();
        FeedbackPopup feedbackPopup = new FeedbackPopup();

        assertTrue(feedbackPopup.isSendFeedbackPopupDisplayed());
        assertTrue(feedbackPopup.isInputFieldDisplayed());
        assertTrue(feedbackPopup.isCancelButtonDisplayed() && feedbackPopup.isSendButtonDisplayed());
        assertEquals(feedbackPopup.getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(feedbackPopup.getInputFieldPlaceholder(), FEEDBACK_POPUP_INPUT_PLACEHOLDER);
        assertTrue(feedbackPopup.isIncludeScreenshotCheckboxChecked());

        feedbackPopup.makeScreenshot()
                     .clickCancelButton();

        assertTrue(new MailPage().isAccountIconVisible());
        assertFalse(feedbackPopup.isSendFeedbackPopupDisplayed());
    }
}

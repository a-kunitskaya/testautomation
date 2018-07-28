package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.kunitskaya.pf.FeedbackPopup.FEEDBACK_POPUP_HEADER;
import static com.kunitskaya.pf.FeedbackPopup.FEEDBACK_POPUP_INPUT_PLACEHOLDER;
import static com.kunitskaya.pf.GmailHelpForumPage.*;
import static com.kunitskaya.pf.GmailHelpPage.*;
import static org.testng.Assert.*;

public class HelpPopupTest extends BaseTest {

    @Test(description = "Log in to Gmail")
    public void logIn() throws IOException {
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.open().fillInUsername(user.getUsername())
                 .clickUsernameNextButton();

        assertEquals(loginPage.getUsernameValue(), user.getUsername());

        MailPage mailPage = loginPage.fillInPassword(user.getPassword())
                                     .clickPasswordNextButton();

        assertTrue(mailPage.isAccountIconVisible());
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up", dependsOnMethods = "logIn")
    public void validateHelpPopup() throws IOException {
        String searchInput = "Change";
        MailPage mailPage = new MailPage(webDriver);

        HelpPopup helpPopup = mailPage.clickSettingsButton()
                                      .clickHelpSettingsOption();
        assertTrue(helpPopup.isDisplayed());

        List<String> searchResults = helpPopup.enterSearchCriteria(searchInput)
                                              .getSearchResults();

        for (String searchResult : searchResults) {
            System.out.println(searchResult);
            assertTrue(StringUtils.containsIgnoreCase(searchResult, searchInput));
        }

        GmailHelpPage helpPage = helpPopup.clearSearchField()
                                          .clickBrowseAllArticlesLink();

        String basePage = browser.getCurrentWindowHandle();
        browser.switchToLastOpenedWindow();

        assertEquals(helpPage.getHeader(), HELP_PAGE_HEADER);
        assertEquals(helpPage.getTitle(), HELP_PAGE_TITLE);
        assertEquals(helpPage.getSearchFieldPlaceholder(), HELP_PAGE_SEARCH_PLACEHOLDER);

        browser.closeCurrentWindow();

        browser.switchToWindowHandle(basePage);

        helpPopup.switchToHelpPopupFrame();

        GmailHelpForumPage forumPage = helpPopup.clickVisitHelpForumLink();
        browser.switchToLastOpenedWindow();

        assertTrue(forumPage.isNewTopicButtonVisible());
        assertEquals(forumPage.getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(forumPage.getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(forumPage.getTitle(), FORUM_PAGE_TITLE));

        browser.closeCurrentWindow();

        browser.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopupFrame();

        FeedbackPopup feedbackPopup = helpPopup.clickSendFeedBackButton()
                                               .switchToFeedbackFrame();

        assertTrue(feedbackPopup.isSendFeedbackPopupDisplayed());
        assertTrue(feedbackPopup.isInputFieldDisplayed());
        assertTrue(feedbackPopup.isCancelButtonDisplayed() && feedbackPopup.isSendButtonDisplayed());
        assertEquals(feedbackPopup.getHeader(), FEEDBACK_POPUP_HEADER);
        assertEquals(feedbackPopup.getInputFieldPlaceholder(), FEEDBACK_POPUP_INPUT_PLACEHOLDER);
        assertTrue(feedbackPopup.isIncludeScreenshotCheckboxChecked());

        feedbackPopup.clickCancelButton();

        assertTrue(mailPage.isAccountIconVisible());
        assertFalse(feedbackPopup.isSendFeedbackPopupDisplayed());
    }
}

package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.kunitskaya.base.constants.AccountConstants.PASSWORD;
import static com.kunitskaya.base.constants.AccountConstants.USERNAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HelpPopupTest extends BaseTest {

    LoginPageFactory loginPage = new LoginPageFactory(webDriver);
    BaseLoggedInPageFactory baseLoggedInPage = new BaseLoggedInPageFactory(webDriver);
    private static final String SEARCH_INPUT = "Change";
    private static final String HELP_PAGE_HEADER = "Welcome to the Gmail Help Center";
    private static final String HELP_PAGE_TITLE = "Gmail Help";
    private static final String HELP_PAGE_SEARCH_PLACEHOLDER = "Describe your issue";
    private static final String FORUM_PAGE_TITLE = "Gmail Help Forum";
    private static final String FORUM_WELCOME_TEXT = "Welcome to the official Gmail Help Forum!";
    private static final String FORUM_SEARCH_PLACEHOLDER = "Search for messages";

    private static final String FEEDBACK_POPUP_HEADER = "Send feedback";
    private static final String FEEDBACK_POPUP_INPUT_PLACEHOLDER = "Describe your issue or share your ideas";

    @Test(description = "Log in to Gmail")
    public void logIn() {

        loginPage.open().fillInUsername(USERNAME).clickUsernameNextButton();
        assertEquals(loginPage.getUsernameValue(), USERNAME);

        loginPage.fillInPassword(PASSWORD).clickPasswordNextButton();
        assertTrue(baseLoggedInPage.isLoggedInAccountIconVisible());
    }

    @Test(description = "CDP-0003 Gmail: Help pop-up", dependsOnMethods = "logIn")
    public void validateHelpPopup() {
        HelpPopupFactory helpPopup = baseLoggedInPage.clickSettingsButton().clickHelpSettingsOption();
        assertTrue(helpPopup.isHelpPopupDisplayed());

        helpPopup.enterTextToHelpSearchField(SEARCH_INPUT);
        List<WebElement> searchResults = helpPopup.getSearchResults();

        for (WebElement searchResult : searchResults) {
            String result = searchResult.getText();
            assertTrue(StringUtils.containsIgnoreCase(result, SEARCH_INPUT));
        }

        helpPopup.clearSearchField();

        GmailHelpPageFactory gmailHelpPage = helpPopup.clickBrowseAllArticlesLink();

        String basePage = gmailHelpPage.getCurrentWindowHandle();
        gmailHelpPage.switchToLastOpenedWindow();

        assertEquals(gmailHelpPage.getHeplPageHeader(), HELP_PAGE_HEADER);
        assertEquals(gmailHelpPage.getCurrentPageTitle(), HELP_PAGE_TITLE);
        assertEquals(gmailHelpPage.getSearchFieldPlaceholder(), HELP_PAGE_SEARCH_PLACEHOLDER);

        webDriver.close();
        gmailHelpPage.switchToWindowHandle(basePage);

        helpPopup.switchToHelpPopup();

        GmailHelpForumFactory forumPage = helpPopup.clickVisitHelpForumLink();
        forumPage.switchToLastOpenedWindow();

        assertTrue(forumPage.isNewTopickButtonVIsible());
        assertEquals(forumPage.getSearchFieldPlaceholder(), FORUM_SEARCH_PLACEHOLDER);
        assertEquals(forumPage.getWelcomeText(), FORUM_WELCOME_TEXT);
        assertTrue(StringUtils.containsIgnoreCase(forumPage.getCurrentPageTitle(), FORUM_PAGE_TITLE));

        webDriver.close();
        forumPage.switchToWindowHandle(basePage);
        helpPopup.switchToHelpPopup();

        FeedbackPopupFactory feedbackPopup = helpPopup.clickSendFeedBackLink();

        assertTrue(feedbackPopup.isCancelButtonDisplayed());
       // assertTrue();



        //10.	‘Send feedback’ pop-up is present:
        //•	Title is ‘Send feedback’
        //•	Input field is present with ‘Describe your issue or share your ideas’ text inside
        //•	‘Include screenshot’ checkbox is checked
        //•	‘Cancel’ and ‘Send’ buttons are present

    }
}


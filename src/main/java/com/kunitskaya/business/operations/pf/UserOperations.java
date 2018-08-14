package com.kunitskaya.business.operations.pf;

import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.pages.pf.*;

import java.util.List;

public class UserOperations {
    /**
     * Logs in to Gmail
     *
     * @param user - user with credenials
     */
    public static void logIn(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
    }

    /**
     * Logs out of Gmail
     */
    public static void logOut() {
        new MailPage()
                .clickAccountIcon()
                .clickSignOutButton();
    }

    /**
     * Accepts alert
     */
    public static void acceptAlert() {
        new ComposeEmailPopup().acceptAlert();
    }

    /**
     * Receiver search results based on search input
     *
     * @param input - search criteria
     * @return - search results
     */
    public static List<String> searchFor(String input) {
        return new HelpPopup().enterSearchCriteria(input)
                              .getSearchResults();
    }

    /**
     * Clears Search field
     */
    public static void clearSearchField() {
        new HelpPopup().clearSearchField();
    }

    /**
     * Take a screenshot in the Feedback popup
     *
     * @param xOffset - from offset
     * @param yOffset - to offset
     */
    public static void makeFeedbackScreenshot(int xOffset, int yOffset) {
        new FeedbackPopup().makeScreenshot(xOffset, yOffset);
    }

    /**
     * Dismisses Feedback popup
     */
    public static void cancelFeedback() {
        new FeedbackPopup().clickCancelButton();
    }

    /**
     * Enteres username on Login page
     *
     * @param username - username to enter
     */
    public static void enterUsername(String username) {
        new LoginPage().fillInUsername(username)
                       .clickUsernameNextButton();
    }

    /**
     * Enteres password on Login page
     *
     * @param password - password to enter
     */
    public static void enterPassword(String password) {
        new LoginPage().fillInPassword(password)
                       .clickPasswordNextButton();
    }
}

package com.kunitskaya.selenium.business.operations.pf;

import com.kunitskaya.selenium.business.objects.Feedback;
import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.selenium.pages.pf.*;
import com.kunitskaya.base.test.TestDataProvider;
import com.kunitskaya.base.test.Users;

import java.util.List;

public class UserOperations {

    /**
     * Logs in to Gmail
     *
     * @param user - user with credentials
     */
    public static void logIn(User user) {
        new LoginPage().fillInUsername(user.getUsername())
                       .clickUsernameNextButton();
        new LoginPage().fillInPassword(user.getPassword())
                       .clickPasswordNextButton();
    }

    /**
     * Logs in to Gmail
     *
     * @param userType - user type to log in as
     */
    public static void logIn(Users userType) {
        User user;
        switch (userType) {
            case VALID:
                user = TestDataProvider.getGmailUser();
                break;
            case INVALID_PASSWORD:
                user = TestDataProvider.getInvalidPasswordGmailUser();
                break;
            default:
                throw new IllegalArgumentException("No such user is found " + userType);
        }
        logIn(user);
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
     * Creates and sends feedback
     *
     * @param feedback            - Feedback instance
     * @param isFeedbackCancelled - determines if to cancel or send feedback eventually
     */
    public static void leaveFeedback(Feedback feedback, boolean isFeedbackCancelled) {
        FeedbackPopup feedbackPopup = new FeedbackPopup();
        feedbackPopup.fillInInputField(feedback.getFeedbackText());
        if (feedback.isWithScreenshot()) {
            feedbackPopup = feedbackPopup.makeScreenshot(400, 400);
        }
        if (isFeedbackCancelled) {
            feedbackPopup.clickCancelButton();
        } else {
            feedbackPopup.clickSendButton();
        }
    }

    /**
     * Deletes all emails from the current folder
     */
    public static void deleteAllEmails() {
        new MailListingPage().deleteAllEmails();
    }
}

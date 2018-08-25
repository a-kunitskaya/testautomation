package com.kunitskaya.business.operations.pf;

import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.pages.pf.HelpPopup;
import com.kunitskaya.pages.pf.LoginPage;
import com.kunitskaya.pages.pf.MailListingPage;
import com.kunitskaya.pages.pf.MailPage;
import com.kunitskaya.test.Browser;

public class NavigationOperations {

    /**
     * Opens Sent Mail folder
     */
    public static void goToSentMailFolder() {
        MailPage mailPage = new MailPage();
        mailPage.clickSentMailLink();
    }

    /**
     * Opens Help popup
     */
    public static void goToHelpPopup() {
        new MailPage().clickSettingsButton()
                      .clickHelpSettingsOption();

    }

    /**
     * Opens Help page from Help popup
     */
    public static void goToHelpPage() {
        new HelpPopup().clickBrowseAllArticlesLink();
    }

    /**
     * Opens Help forum page from Help popup
     */
    public static void goToHelpForum() {
        new HelpPopup().clickVisitHelpForumLink();
        Browser.getInstance().switchToLastOpenedWindow();
    }

    /**
     * Opens Feedback popup from Help popup
     */
    public static void goToFeedbackPopup() {
        new HelpPopup().clickSendFeedBackButton()
                       .switchToFeedbackFrame();
    }

    /**
     * Opens Login page
     */
    public static void goToLoginPage() {
        new LoginPage().open();
    }

    /**
     * Opens email based on its subject
     */
    public static void goToEmail(Email email) {
        new MailListingPage().openEmailWithSubject(email.getSubject());
    }
}

package com.kunitskaya.selenium.business.operations.pf;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.test.Folders;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.pages.pf.HelpPopup;
import com.kunitskaya.selenium.pages.pf.LoginPage;
import com.kunitskaya.selenium.pages.pf.MailListingPage;
import com.kunitskaya.selenium.pages.pf.MailPage;

public class NavigationOperations {

    /**
     * Opens Sent Mail folder
     */
    public static void goToFolder(Folders folder) {
        switch (folder) {
            case SENT:
                new MailPage().clickSentMailLink();
                break;
            case DRAFT:
                new MailPage().clickDraftsFolderLink();
                break;
            default:
                throw new IllegalArgumentException("No such folder is found: " + folder.name());

        }
    }
        /**
         * Opens Help popup
         */
        public static void goToHelpPopup(){
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
        public static void goToHelpForum () {
            new HelpPopup().clickVisitHelpForumLink();
            Browser.getInstance().switchToLastOpenedWindow();
        }

        /**
         * Opens Feedback popup from Help popup
         */
        public static void goToFeedbackPopup () {
            new HelpPopup().clickSendFeedBackButton()
                           .switchToFeedbackFrame();
        }

        /**
         * Opens Login page
         */
        public static void goToLoginPage () {
            new LoginPage().open();
        }

        /**
         * Opens email based on its subject
         */
        public static void goToEmail (Email email){
            new MailListingPage().openEmailWithSubject(email.getSubject());
        }
    }

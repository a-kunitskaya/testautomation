package com.kunitskaya.business.operations;

import com.kunitskaya.base.Browser;
import com.kunitskaya.pages.pf.HelpPopup;
import com.kunitskaya.pages.pf.MailPage;

public class NavigaionOperations {

    public static void goToSentMailFolder() {
        MailPage mailPage = new MailPage();
        mailPage.clickSentMailLink();
    }

    public static void goToHelpPopup() {
        new MailPage().clickSettingsButton()
                      .clickHelpSettingsOption();

    }

    public static void goToHelpPage() {
        new HelpPopup().clickBrowseAllArticlesLink();
    }

    public static void goToHelpForum() {
        new HelpPopup().clickVisitHelpForumLink();
        Browser.getInstance().switchToLastOpenedWindow();
    }

    public static void goToFeedbackPopup() {
        new HelpPopup().clickSendFeedBackButton()
                       .switchToFeedbackFrame();
    }


}

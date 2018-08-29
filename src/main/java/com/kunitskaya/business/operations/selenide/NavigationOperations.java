package com.kunitskaya.business.operations.selenide;

import com.kunitskaya.pages.selenide.LoginPage;

import static com.kunitskaya.pages.selenide.MailPage.clickSentFolderLink;

public class NavigationOperations {

    /**
     * Opens Login page
     */
    public static void goToLoginPage() {
        LoginPage.open();
    }

    /**
     * Opens Sent Mail folder
     */
    public static void goToSentFolder() {
        clickSentFolderLink();
    }
}

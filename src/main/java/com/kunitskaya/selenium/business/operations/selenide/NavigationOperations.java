package com.kunitskaya.selenium.business.operations.selenide;

import com.kunitskaya.selenium.pages.selenide.LoginPage;

import static com.kunitskaya.selenium.pages.selenide.MailPage.clickSentFolderLink;

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

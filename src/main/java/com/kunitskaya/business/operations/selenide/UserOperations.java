package com.kunitskaya.business.operations.selenide;

import com.kunitskaya.pages.selenide.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;

public class UserOperations {
    /**
     * Enters username on Login page
     *
     * @param username - username to enter
     */
    public static void enterUsername(String username) {
        LoginPage.enterUsername(username);
    }

    /**
     * Enters password on Login page
     *
     * @param password - password to enter
     */
    public static void enterPassword(String password) {
        LoginPage.enterPassword(password);
    }

    /**
     * Finds alert by text, accepts it
     *
     * @param alertText - text in the alert
     */
    public static void acceptAlert(String alertText) {
        $(confirm(alertText));
    }
}
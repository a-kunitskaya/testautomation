package com.kunitskaya.selenium.business.operations.selenide;

import com.kunitskaya.selenium.business.objects.user.User;
import com.kunitskaya.selenium.pages.selenide.LoginPage;

public class UserOperations {

    /**
     * Logs in to Gmail
     *
     * @param user - user with credentials
     */
    public static void logIn(User user) {
        LoginPage.enterUsername(user.getUsername());
        LoginPage.enterPassword(user.getPassword());
    }
}

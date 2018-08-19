package com.kunitskaya.business.operations.selenide;

import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.pages.selenide.LoginPage;

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
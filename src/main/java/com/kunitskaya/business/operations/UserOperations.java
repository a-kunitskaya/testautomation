package com.kunitskaya.business.operations;

import com.kunitskaya.business.objects.User;
import com.kunitskaya.pages.pf.LoginPage;
import com.kunitskaya.pages.pf.MailPage;

public class UserOperations {

    public static MailPage logIn(User user) {
        return new LoginPage()
                .open()
                .fillInUsername(user.getUsername())
                .clickUsernameNextButton()
                .fillInPassword(user.getPassword())
                .clickPasswordNextButton();
    }

    public static LoginPage logOut() {
        return new MailPage()
                .clickAccountIcon()
                .clickSignOutButton();
    }
}

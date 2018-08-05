package com.kunitskaya.business.operations;

import com.kunitskaya.business.objects.User;
import com.kunitskaya.pages.LoginPage;
import com.kunitskaya.pages.MailPage;

public class UserOperations {

    public static MailPage logIn(User user){
        LoginPage loginPage = new LoginPage();
        return loginPage.open()
                 .fillInUsername(user.getUsername())
                 .clickUsernameNextButton()
                 .fillInPassword(user.getPassword())
                 .clickPasswordNextButton();
    }
}

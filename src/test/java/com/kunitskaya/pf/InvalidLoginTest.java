package com.kunitskaya.pf;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002L Logging in with invalid password")
    public void logInWithInvalidPassword() throws IOException {
        String invalidPassword = "pwd";
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open()
                 .fillInUsername(user.getUsername())
                 .clickUsernameNextButton();

        assertEquals(loginPage.getUsernameValue(), user.getUsername());

        loginPage.fillInPassword(invalidPassword)
                 .clickPasswordNextButton();

        assertTrue(loginPage.isErrorMessageDisplayed());

        MailPage mailPage = loginPage.fillInPassword(user.getPassword())
                                     .clickPasswordNextButton();

        assertTrue(mailPage.isAccountIconVisible());
    }
}

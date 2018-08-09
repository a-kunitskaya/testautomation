package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import static com.kunitskaya.pages.pf.LoginPage.WRONG_PASSWORD_ERROR_MESSAGE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        String invalidPassword = "pwd";
        LoginPage loginPage = new LoginPage();
        loginPage.open()
                 .fillInUsername(user.getUsername())
                 .clickUsernameNextButton();

        assertEquals(loginPage.getUsernameValue(), user.getUsername());

        loginPage.fillInPassword(invalidPassword)
                 .clickPasswordNextButton();

        assertTrue(loginPage.isErrorMessageDisplayed(WRONG_PASSWORD_ERROR_MESSAGE));

        MailPage mailPage = loginPage
                .fillInPassword(user.getPassword())
                .clickPasswordNextButton();

        assertTrue(mailPage.isAccountIconVisible());
    }
}

package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.pf.NavigaionOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import org.testng.annotations.Test;

import static com.kunitskaya.pages.pf.LoginPage.WRONG_PASSWORD_ERROR_MESSAGE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigaionOperations.goToLoginPage();
        UserOperations.enterUsername(user.getUsername());
        assertEquals(new LoginPage().getUsernameValue(), user.getUsername());

        String invalidPassword = "pwd";
        UserOperations.enterPassword(invalidPassword);
        assertTrue(new LoginPage().isErrorMessageDisplayed(WRONG_PASSWORD_ERROR_MESSAGE));

        UserOperations.enterPassword(user.getPassword());
        assertTrue(new MailPage().isAccountIconVisible());
    }
}

package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.pages.pf.LoginPage.WRONG_PASSWORD_ERROR_MESSAGE;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();
        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        assertTrue(new LoginPage().isErrorMessageDisplayed(WRONG_PASSWORD_ERROR_MESSAGE));

    }
}

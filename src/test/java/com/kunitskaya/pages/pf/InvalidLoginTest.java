package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.LoginLanguages;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.pages.pf.LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_ENG;
import static com.kunitskaya.pages.pf.LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_RUS;
import static com.kunitskaya.test.LoginLanguages.ENGLISH;
import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();

        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        LoginPage loginPage = new LoginPage();
        LoginLanguages english = LoginLanguages.getLanguage(ENGLISH.getLanguageCode());

        if (loginPage.isLanguageSet(english)) {
            assertTrue(loginPage.isErrorMessageDisplayed(WRONG_PASSWORD_ERROR_MESSAGE_ENG));
        } else {
            assertTrue(loginPage.isErrorMessageDisplayed(WRONG_PASSWORD_ERROR_MESSAGE_RUS));
        }
    }
}

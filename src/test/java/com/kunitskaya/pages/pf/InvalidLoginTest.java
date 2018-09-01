package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Languages;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static com.kunitskaya.test.Languages.ENGLISH;
import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();

        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        LoginPage loginPage = new LoginPage();
        Languages english = Languages.getLanguage(ENGLISH.getLanguageCode());

        if (loginPage.isLanguageSet(english)) {
            assertTrue(loginPage.isErrorMessageDisplayed(LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_ENG));
        } else {
            assertTrue(loginPage.isErrorMessageDisplayed(LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_RUS));
        }
    }
}

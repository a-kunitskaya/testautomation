package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.test.selenium.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTest extends SeleniumBaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();

        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        LoginPage loginPage = new LoginPage();
        String expectedTranslation = TestDataProvider.getLoginErrorMessage(loginPage.getDisplayedLanguage());

        assertTrue(loginPage.isErrorMessageDisplayed(expectedTranslation));
    }
}

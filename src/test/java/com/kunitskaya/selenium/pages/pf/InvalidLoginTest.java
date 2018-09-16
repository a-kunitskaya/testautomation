package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.base.test.TestDataProvider;
import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTest extends SeleniumBaseTest {

    @BeforeMethod
    public void restartDriver() {
        browser = Browser.getInstance();
    }

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();
        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        LoginPage loginPage = new LoginPage();
        String expectedTranslation = TestDataProvider.getLoginErrorMessage(loginPage.getDisplayedLanguage());

        assertTrue(loginPage.isErrorMessageDisplayed(expectedTranslation));
    }

    @Test(description = "CDP-005: Login with invalid password and fail")
    public void logInFailureTest() {
        NavigationOperations.goToLoginPage();
        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        //test should fail here
        assertTrue(new MailPage().isAccountIconVisible());
    }

    @AfterMethod
    public void quitDriver() {
        browser.quit();
    }

}

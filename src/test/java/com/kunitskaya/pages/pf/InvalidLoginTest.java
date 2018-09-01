package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.base.utils.files.CsvFileReader;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Languages;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.TranslationsElements;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "CDP-0002 Logging in with invalid password")
    public void logInWithInvalidPassword() {
        NavigationOperations.goToLoginPage();

        user = TestDataProvider.getInvalidPasswordGmailUser();
        UserOperations.logIn(user);

        LoginPage loginPage = new LoginPage();
        Languages actualLanguage = loginPage.getDisplayedLanguage();

        CsvFileReader fileReader = new CsvFileReader();
        String expectedTranslation = StringUtils.EMPTY;

        switch (actualLanguage) {
            case ENGLISH:
                expectedTranslation = fileReader.getTranslationForElement(Languages.ENGLISH, TranslationsElements.LOGIN_ERROR);
                break;
            case RUSSIAN:
                expectedTranslation = fileReader.getTranslationForElement(Languages.RUSSIAN, TranslationsElements.LOGIN_ERROR);
                break;
        }
        assertTrue(loginPage.isErrorMessageDisplayed(expectedTranslation));
    }
}

package com.kunitskaya.stepdefinitions;

import com.kunitskaya.base.Browser;
import com.kunitskaya.base.utils.files.CsvFileReader;
import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.pages.pf.LoginPage;
import com.kunitskaya.test.Languages;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.TranslationsElements;
import com.kunitskaya.test.Users;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPageStepsDefs {

    @Given("I opened Login page")
    public void iOpenLoginPage() {
        NavigationOperations.goToLoginPage();
    }

    @When("^I login as \"([^\"]*)\" user$")
    public void iLoginAsUser(String type) {
        User user = null;
        switch (Users.valueOf(type)) {
            case VALID:
                user = TestDataProvider.getGmailUser();
                break;
            case INVALID_PASSWORD:
                user = TestDataProvider.getInvalidPasswordGmailUser();
                break;
        }
        UserOperations.logIn(user);
        MailPageStepsDefs.mailPageWinHandle = Browser.getInstance().getCurrentWindowHandle();
    }

    @Then("^Error message should be displayed$")
    public void errorMessageShouldBeDisplayed() {
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

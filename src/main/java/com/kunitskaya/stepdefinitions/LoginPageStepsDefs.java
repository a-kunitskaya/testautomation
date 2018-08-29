package com.kunitskaya.stepdefinitions;

import com.kunitskaya.base.Browser;
import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.pages.pf.LoginPage;
import com.kunitskaya.test.LoginLanguages;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.Users;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.kunitskaya.test.LoginLanguages.ENGLISH;
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
        LoginLanguages english = LoginLanguages.getLanguage(ENGLISH.getLanguageCode());
        if (loginPage.isLanguageSet(english)) {
            assertTrue(loginPage.isErrorMessageDisplayed(LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_ENG));
        } else {
            assertTrue(loginPage.isErrorMessageDisplayed(LoginPage.WRONG_PASSWORD_ERROR_MESSAGE_RUS));
        }
    }
}

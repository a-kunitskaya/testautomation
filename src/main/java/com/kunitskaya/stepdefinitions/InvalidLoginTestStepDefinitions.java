package com.kunitskaya.stepdefinitions;

import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.business.operations.NavigationOperations;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.pages.LoginPage;
import com.kunitskaya.test.LoginLanguages;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.kunitskaya.test.LoginLanguages.ENGLISH;
import static org.testng.AssertJUnit.assertTrue;

public class InvalidLoginTestStepDefinitions {

    @Given("I opened Login page")
    public void iOpenLoginPage() {
        NavigationOperations.goToLoginPage();
    }

    @When("^I login to Gmail with a valid username ([\\w]+@[\\w]+.[\\w]+) and invalid password (.+)$")
    public void iLoginToGmailWithAValidUsernameUsernameAndInvalidPasswordPassword(String username, String password) {
        User user = new User.Builder()
                .withUsername(username)
                .withPassword(password)
                .build();

        UserOperations.logIn(user);
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

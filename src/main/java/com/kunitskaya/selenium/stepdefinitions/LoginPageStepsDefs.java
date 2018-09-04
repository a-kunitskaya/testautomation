package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import com.kunitskaya.selenium.pages.pf.LoginPage;
import com.kunitskaya.test.selenium.TestDataProvider;
import com.kunitskaya.test.selenium.Users;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPageStepsDefs {

    @Given("I opened Login page")
    public void iOpenLoginPage() {
        NavigationOperations.goToLoginPage();
    }

    @When("^I login as \"([^\"]*)\" user$")
    public void iLoginAsUser(Users userType) {
        UserOperations.logIn(userType);
        MailPageStepsDefs.mailPageWinHandle = Browser.getInstance().getCurrentWindowHandle();
    }

    @Then("^Error message should be displayed$")
    public void errorMessageShouldBeDisplayed() {
        LoginPage loginPage = new LoginPage();
        String expectedTranslation = TestDataProvider.getLoginErrorMessage(loginPage.getDisplayedLanguage());
        assertTrue(loginPage.isErrorMessageDisplayed(expectedTranslation));
    }
}

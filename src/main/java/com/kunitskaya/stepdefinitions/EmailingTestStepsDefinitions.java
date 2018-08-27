package com.kunitskaya.stepdefinitions;

import com.kunitskaya.base.Browser;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.objects.user.GmailUserCreator;
import com.kunitskaya.business.operations.EmailOperations;
import com.kunitskaya.business.operations.NavigationOperations;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.pages.MailListingPage;
import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.*;

public class EmailingTestStepsDefinitions {
    private Email expectedEmail;

    @Given("I logged in to Gmail")
    public void iLoggedInToGmail() {
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(new GmailUserCreator().createUser());
    }

    @When("^I create and send email without body$")
    public void createAndSendEmailWithoutBody() {
        expectedEmail = TestDataProvider.getEmailWithoutSubjectAndBody();
        EmailOperations.createAndSendEmail(expectedEmail);

        String noSubject = "(no subject)";
        expectedEmail.setSubject(noSubject);
    }

    @Then("^alert is displayed$")
    public void alertIsDisplayed() {
        assertTrue(Browser.getInstance().isAlertDisplayed());
    }

    @When("^I accept alert$")
    public void iAcceptAlert() {
        Browser.getInstance().acceptAlert();
    }

    @And("^I go to sent folder$")
    public void iGoToSentFolder() {
        NavigationOperations.goToSentMailFolder();
    }

    @Then("^sent email is displayed$")
    public void sentEmailIsDisplayed() {
        NavigationOperations.goToEmail(expectedEmail);
        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }

    @When("^I create valid email$")
    public void iCreateValidEmail() {
        expectedEmail = TestDataProvider.getDefaultEmail();
        EmailOperations.createEmail(expectedEmail);
    }

    @And("^save email as draft$")
    public void saveEmailAsDraft() {
        EmailOperations.saveEmailAsDraft();
    }

    @Then("^email is displayed in drafts folder$")
    public void emailIsDisplayedInDraftsFolder() {
        EmailOperations.openDraft(expectedEmail);
        Email actualDraft = EmailOperations.getActualEmail(Folders.DRAFT);
        assertEquals(actualDraft, expectedEmail);
    }

    @When("^I send draft email$")
    public void iSendDraftEmail() {
        EmailOperations.sendEmail();
    }

    @Then("^email is not displayed in drafts folder$")
    public void emailIsNotDisplayedInDraftsFolder() {
        assertFalse(new MailListingPage().isEmailPresentOnPage(expectedEmail.getSubject()));
    }
}
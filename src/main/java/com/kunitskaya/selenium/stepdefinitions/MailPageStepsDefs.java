package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.business.operations.pf.EmailOperations;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.pages.pf.MailListingPage;
import com.kunitskaya.selenium.pages.pf.MailPage;
import com.kunitskaya.test.selenium.Folders;
import com.kunitskaya.test.selenium.TestDataProvider;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.*;

public class MailPageStepsDefs {
    public static String mailPageWinHandle;
    private Email expectedEmail;

    @And("^I switch to mail page$")
    public void iSwitchToMailPage() {
        Browser.getInstance().switchToWindowHandle(mailPageWinHandle);
    }

    @And("^Mail page is displayed$")
    public void mailPageIsDisplayed() {
        assertTrue(new MailPage().isAccountIconVisible());
    }

    @And("^I go to sent folder$")
    public void iGoToSentFolder() {
        NavigationOperations.goToFolder(Folders.SENT);
    }

    @And("^save email as draft$")
    public void saveEmailAsDraft() {
        EmailOperations.saveEmailAsDraft();
    }

    @Then("^email is \"([^\"]*)\" in drafts folder$")
    public void emailIsInDraftsFolder(String presence) {
        if (presence.equals("PRESENT")) {
            EmailOperations.openDraft(expectedEmail);
            Email actualDraft = EmailOperations.getActualEmail(Folders.DRAFT);
            assertEquals(actualDraft, expectedEmail);
        } else {
            assertFalse(new MailListingPage().isEmailPresentOnPage(expectedEmail.getSubject()));
        }
    }

    @When("^I create \"([^\"]*)\" email$")
    public void iCreateEmail(String validity) {
        if (validity.equals("VALID")) {
            expectedEmail = TestDataProvider.getDefaultEmail();
        } else {
            expectedEmail = TestDataProvider.getEmailWithoutSubjectAndBody();
        }
        EmailOperations.createEmail(expectedEmail);
    }

    @Then("^sent \"([^\"]*)\" email is displayed$")
    public void sentEmailIsDisplayed(String validity) {
        if (validity.equals("EMPTY")) {
            String noSubject = "(no subject)";
            expectedEmail.setSubject(noSubject);
        }
        NavigationOperations.goToEmail(expectedEmail);
        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);
    }

    @When("^I send \"([^\"]*)\" email$")
    public void iSendEmail(String state) {
        if (state.equals("DRAFT")) {
            EmailOperations.sendEmail(Folders.DRAFT);
        } else {
            EmailOperations.sendEmail();
        }
    }
}

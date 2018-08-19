package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.email.Email;
import com.kunitskaya.business.operations.pf.EmailOperations;
import com.kunitskaya.business.operations.pf.NavigationOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {

    @Test(description = "Log in to Gmail")
    public void logIn() {
        NavigationOperations.goToLoginPage();
        UserOperations.logIn(user);
        assertTrue(new MailPage().isAccountIconVisible());
    }

    @Test(description = "Create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        Email expectedEmail = TestDataProvider.getDefaultEmail();

        EmailOperations.createEmail(expectedEmail);
        EmailOperations.saveEmailAsDraft();
        EmailOperations.openDraft(expectedEmail);

        Email actualDraft = EmailOperations.getActualEmail(expectedEmail, Folders.DRAFT);
        assertEquals(actualDraft, expectedEmail);

        EmailOperations.sendDraftEmail();
        assertFalse(new MailListingPage().isEmailPresentOnPage(expectedEmail.getSubject()));

        NavigationOperations.goToSentMailFolder();

        Email actualEmail = EmailOperations.getActualEmail(expectedEmail, Folders.SENT);
        assertEquals(actualEmail, expectedEmail);

    }

    @Test(description = "Log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        UserOperations.logOut();
        assertTrue(new LoginPage().isPasswordFieldDisplayed());
    }
}
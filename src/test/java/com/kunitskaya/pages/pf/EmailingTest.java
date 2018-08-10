package com.kunitskaya.pages.pf;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.operations.pf.EmailOperations;
import com.kunitskaya.business.operations.pf.NavigaionOperations;
import com.kunitskaya.business.operations.pf.UserOperations;
import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {

    @Test(description = "Log in to Gmail")
    public void logIn() {
        NavigaionOperations.goToLoginPage();
        UserOperations.logIn(user);
        assertTrue(new MailPage().isAccountIconVisible());
    }

    @Test(description = "Create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        Email email = TestDataProvider.getEmail();

        EmailOperations.createEmail(email);
        EmailOperations.saveEmailAsDraft();
        EmailOperations.openDraft(email);

        String emailContent = EmailOperations.getMailContent(email, Folders.DRAFT);
        assertEquals(emailContent, email.getReceiver() + " - " + email.getBody());

        EmailOperations.sendEmail();
        assertFalse(new MailListingPage().isEmailPresentOnPage(email.getSubject()));

        NavigaionOperations.goToSentMailFolder();
        String actualSentEmailContent = EmailOperations.getMailContent(email, Folders.SENT);
        String expectedEmailContent = email.getSubject() + email.getReceiver() + email.getBody();
        assertEquals(actualSentEmailContent, expectedEmailContent);

    }

    @Test(description = "Log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        UserOperations.logOut();
        assertTrue(new LoginPage().isPasswordFieldDisplayed());
    }
}
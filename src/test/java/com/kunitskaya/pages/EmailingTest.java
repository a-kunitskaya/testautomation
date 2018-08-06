package com.kunitskaya.pages;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.business.operations.EmailOperations;
import com.kunitskaya.business.operations.UserOperations;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {

    @Test(description = "Log in to Gmail")
    public void logIn() {
        MailPage mailPage = UserOperations.logIn(user);
        assertTrue(mailPage.isAccountIconVisible());
    }

    @Test(description = "Create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        Email email = TestDataProvider.getEmail();

        String subject = email.getSubject();
        String to = email.getReceiver();
        String body = email.getBody();

        ComposeEmailPopup emailPopup = EmailOperations.createEmail(email);
        EmailOperations.saveEmailAsDraft(emailPopup);

        emailPopup = EmailOperations.openDraft(email);
        String sMailDetails = emailPopup.getEmailContent(subject);
        assertEquals(sMailDetails, to + " - " + body);

        MailListingPage mailListingPage = EmailOperations.sendEmail();
        assertFalse(mailListingPage.isEmailPresentOnPage(subject));

        String sentEmailContent = EmailOperations.getSentMailFullContent(email);
        assertEquals(sentEmailContent, subject + to + body);

    }

    @Test(description = "Log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        LoginPage loginPage = UserOperations.logOut();
        assertTrue(loginPage.isPasswordFieldDisplayed());
    }
}

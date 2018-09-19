package com.kunitskaya.selenium.pages.pf;

import com.kunitskaya.test.Folders;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.selenium.SeleniumBaseTest;
import com.kunitskaya.selenium.business.objects.Email;
import com.kunitskaya.selenium.business.operations.pf.EmailOperations;
import com.kunitskaya.selenium.business.operations.pf.NavigationOperations;
import com.kunitskaya.selenium.business.operations.pf.UserOperations;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailingTest extends SeleniumBaseTest {

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

        Email actualDraft = EmailOperations.getActualEmail(Folders.DRAFT);
        assertEquals(actualDraft, expectedEmail);

        EmailOperations.sendEmail(Folders.DRAFT);
        assertFalse(new MailListingPage().isEmailPresentOnPage(expectedEmail.getSubject()));

        NavigationOperations.goToFolder(Folders.SENT);
        NavigationOperations.goToEmail(expectedEmail);

        Email actualEmail = EmailOperations.getActualEmail(Folders.SENT);
        assertEquals(actualEmail, expectedEmail);

    }

    @Test(description = "Log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        UserOperations.logOut();
        assertTrue(new LoginPage().isPasswordFieldDisplayed());
    }
}
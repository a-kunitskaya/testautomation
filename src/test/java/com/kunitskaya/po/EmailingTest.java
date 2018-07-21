package com.kunitskaya.po;

import com.kunitskaya.BaseTest;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.Email;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {

    @Test(description = "Log in to Gmail")
    public void logIn() {
        MailPage mailPage = new MailPage();
        LoginPage loginPage = new LoginPage();

        loginPage.open();

        loginPage.fillInUsername(user.getUsername())
                 .clickUsernameNextButton();

        String usernameForPassword = loginPage.getUsernameValue();
        assertEquals(usernameForPassword, user.getUsername());

        loginPage.fillInPassword(user.getPassword())
                 .clickPasswordNextButton();
        assertTrue(mailPage.isAccountIconVisible());
    }

    @Test(description = "create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        Email email = TestDataProvider.getEmail();

        MailPage mailPage = new MailPage();
        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton();

        composeEmailPopup.fillInToField(email.getReceiver())
                         .fillInSubjectField(email.getSubject())
                         .fillInBodyField(email.getBody())
                         .clickCloseButton();

        DraftsPage draftsPage = mailPage.clickDraftsFolderLink();
        draftsPage.openDraftWithSubject(email.getSubject());

        String draftContent = draftsPage.getDraftContent(email.getSubject());
        assertEquals(draftContent, email.getReceiver() + " - " + email.getBody());

        draftsPage.clickSendButton();
        assertFalse(draftsPage.isDraftPresentOnPage(email.getSubject()));

        SentMailPage sentMailPage = mailPage.clickSentMailLink();
        sentMailPage.openSentMailWithSubject(email.getSubject());

        String sentMailContent = sentMailPage.getSentMailContent(email.getSubject());
        assertEquals(sentMailContent, email.getSubject() + email.getReceiver() + email.getBody());
    }

    @Test(description = "log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        MailPage mailPage = new MailPage();
        LogoutPage logoutPage = mailPage.clickAccountIcon().clickSignOutButton();
        assertTrue(logoutPage.isPasswordFieldDisplayed());
    }
}

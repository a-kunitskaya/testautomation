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

        String usernameForPassword = loginPage.open()
                                              .fillInUsername(user.getUsername())
                                              .clickUsernameNextButton()
                                              .getUsernameValue();

        assertEquals(usernameForPassword, user.getUsername());

        loginPage.fillInPassword(user.getPassword())
                 .clickPasswordNextButton();
        assertTrue(mailPage.isAccountIconVisible());
    }

    @Test(description = "Create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        Email email = TestDataProvider.getEmail();

        MailPage mailPage = new MailPage();
        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton();

        DraftsPage draftsPage = mailPage.clickComposeButton()
                                        .fillInToField(email.getReceiver())
                                        .fillInSubjectField(email.getSubject())
                                        .fillInBodyField(email.getBody())
                                        .clickCloseButton()
                                        .clickDraftsFolderLink();

        draftsPage.openEmailWithSubject(email.getSubject());

        String draftContent = draftsPage.getDraftContent(email.getSubject());
        assertEquals(draftContent, email.getReceiver() + " - " + email.getBody());

        draftsPage.clickSendButton();
        assertFalse(draftsPage.isEmailPresentOnPage(email.getSubject()));

        SentMailPage sentMailPage = mailPage.clickSentMailLink();
        sentMailPage.openEmailWithSubject(email.getSubject());

        String sentMailContent = sentMailPage.getSentMailContent(email.getSubject());

        assertEquals(sentMailContent, email.getSubject() + email.getReceiver() + email.getBody());
    }

    @Test(description = "log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        MailPage mailPage = new MailPage();
        LoginPage loginPage = mailPage.clickAccountIcon().clickSignOutButton();
        assertTrue(loginPage.isPasswordFieldDisplayed());
    }
}

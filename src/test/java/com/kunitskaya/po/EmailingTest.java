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
        String subject = email.getSubject();
        String to = email.getReceiver();
        String body = email.getBody();

        MailPage mailPage = new MailPage();
        mailPage.clickComposeButton();

        DraftsPage draftsPage = mailPage.clickComposeButton()
                                        .fillInToField(to)
                                        .fillInSubjectField(subject)
                                        .fillInBodyField(body)
                                        .clickCloseButton()
                                        .clickDraftsFolderLink();

        draftsPage.openEmailWithSubject(subject);

        String draftContent = draftsPage.getDraftContent(subject);
        assertEquals(draftContent, to + " - " + body);

        draftsPage.clickSendButton();
        assertFalse(draftsPage.isEmailPresentOnPage(subject));


        String sentMailContent = mailPage.clickSentMailLink()
                                         .openEmailWithSubject(subject)
                                         .getSentMailContent(subject);

        assertEquals(sentMailContent, subject + to + body);
    }

    @Test(description = "log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        MailPage mailPage = new MailPage();
        LoginPage loginPage = mailPage.clickAccountIcon().clickSignOutButton();
        assertTrue(loginPage.isPasswordFieldDisplayed());
    }
}

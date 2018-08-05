package com.kunitskaya.pages;

import com.kunitskaya.BaseTest;
import com.kunitskaya.business.objects.Email;
import com.kunitskaya.test.TestDataProvider;
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

        ComposeEmailPopup draft = mailPage.clickComposeButton()
                                          .fillInToField(to)
                                          .fillInSubjectField(subject)
                                          .fillInBodyField(body)
                                          .clickCloseButton()
                                          .clickDraftsFolderLink()
                                          .openDraftWithSubject(subject);

        String draftContent = draft.getEmailContent(subject);
        assertEquals(draftContent, to + " - " + body);

        MailListingPage draftsPage = draft.clickSendButton();

        assertFalse(draftsPage.isEmailPresentOnPage(subject));

        String sentMailContent = mailPage.clickSentMailLink()
                                         .openEmailWithSubject(subject)
                                         .getSentMailFullContent();

        assertEquals(sentMailContent, subject + to + body);
    }

    @Test(description = "Log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        MailPage mailPage = new MailPage();
        LoginPage loginPage = mailPage.clickAccountIcon().clickSignOutButton();
        assertTrue(loginPage.isPasswordFieldDisplayed());
    }
}

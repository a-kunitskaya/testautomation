package com.kunitskaya.po;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {
   // private String subjectWithTimestamp = SUBJECT.concat(getFormattedTimestamp());

    MailPage mailPage = new MailPage(webDriver);

    @Test(description = "Log in to Gmail")
    public void logIn() {

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.open();

        loginPage.fillInUsername(USERNAME)
                .clickUsernameNextButton();

        String usernameForPassword = loginPage.getUsernameValue();
        assertEquals(usernameForPassword, USERNAME);

        loginPage.fillInPassword(PASSWORD)
                .clickPasswordNextButton();
        assertTrue(mailPage.isAccountIconVisible());
    }

    @Test(description = "create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {

        ComposeEmailPopup composeEmailPopup = mailPage.clickComposeButton();

        composeEmailPopup.fillInToField(TO)
                .fillInSubjectField(subjectWithTimestamp)
                .fillInBodyField(BODY)
                .clickCloseButton();

        DraftsPage draftsPage = mailPage.clickDraftsFolderLink();
        draftsPage.openDraftWithSubject(subjectWithTimestamp);

        String draftContent = draftsPage.getDraftContent(subjectWithTimestamp);
        assertEquals(draftContent, TO + " - " + BODY);

        draftsPage.clickSendButton();
        assertFalse(draftsPage.isDraftPresentOnPage(subjectWithTimestamp));

        SentMailPage sentMailPage = mailPage.clickSentMailLink();
        sentMailPage.openSentMailWithSubject(subjectWithTimestamp);

        String sentMailContent = sentMailPage.getSentMailContent(subjectWithTimestamp);
        assertEquals(sentMailContent, subjectWithTimestamp + TO + BODY);
    }

    @Test(description = "log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        LogoutPage logoutPage = mailPage.clickAccountIcon().clickSignOutButton();
        assertTrue(logoutPage.isPasswordFieldDislayed());
    }
}

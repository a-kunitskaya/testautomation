package com.kunitskaya.po;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static org.testng.Assert.*;

public class EmailingTest extends BaseTest {

    private String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());
    BaseLoggedInPage baseLoggedInPage = new BaseLoggedInPage(webDriver);

    @Test(description = "log in to Gmail")
    public void logIn() {

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.open();

        loginPage.fillInUsername(USERNAME)
                .clickUsernameNextButton();

        String usernameForPassword = loginPage.getUsernameValue();
        assertEquals(usernameForPassword, USERNAME);

        InboxPage inboxPage = loginPage.fillInPassword(PASSWORD)
                .clickPasswordNextButton();
        assertTrue(inboxPage.isLoggedInAccountIconVisible());
    }

    @Test(description = "create an email, save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {

        ComposeEmailPage composeEmailPage = baseLoggedInPage.clickComposeButton();

        composeEmailPage.fillInToField(TO)
                .fillInSubjectField(subjectWithTimestamp)
                .fillInBodyField(BODY)
                .clickCloseButton();

        DraftsPage draftsPage = baseLoggedInPage.clickDraftsFolderLink();
        draftsPage.openDraftWithSubject(subjectWithTimestamp);

        String draftContent = draftsPage.getDraftContent(subjectWithTimestamp);
        assertEquals(draftContent, TO + " - " + BODY);

        draftsPage.clickSendButton();
        assertFalse(draftsPage.isDraftPresentOnPage(subjectWithTimestamp));

        SentMailPage sentMailPage = baseLoggedInPage.clickSentMailLink();
        sentMailPage.openSentMailWithSubject(subjectWithTimestamp);

        String sentMailContent = sentMailPage.getSentMailContent(subjectWithTimestamp);
        assertEquals(sentMailContent, subjectWithTimestamp + TO + BODY);
    }

    @Test(description = "log out from Gmail account", dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        LogoutPage logoutPage = baseLoggedInPage.clickLoggedInAccountIcon().clickSignOutButton();
        assertTrue(logoutPage.isPasswordFieldDislayed());
    }
}

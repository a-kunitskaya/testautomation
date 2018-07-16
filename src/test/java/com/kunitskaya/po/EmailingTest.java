package com.kunitskaya.po;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EmailingTest extends BaseTest {

    //TODO:
    // 1. add web driver desired conditions from the book

    private String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());

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
        BaseLoggedInPage baseLoggedInPage = new BaseLoggedInPage(webDriver);

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

    }
//
//
//
//        WebElement sentDraft = findEmailBySubject(webDriver, subjectWithTimestamp);
//        sentDraft.click();
//
//        String sentTo = webDriver.findElement(By.xpath("//span[@dir='ltr' and @class='g2']")).getAttribute("email");
//        String sentSubject = webDriver.findElement(By.cssSelector("h2.hP")).getText();
//        //String sentBody = webDriver.findElement(By.xpath("//div[contains(text(),'" + BODY +"')]")).getAttribute("innerText");
//
//        // System.out.println(StringUtils.removeAll(sentBody, "[^"+ BODY +"]"));
//
//        assertEquals(sentTo, draftToString);
//        assertEquals(sentSubject, draftSubject);
//        //assertEquals(sentBody, draftBodyString);
//
//        webDriver.navigate().back();
//    }


}

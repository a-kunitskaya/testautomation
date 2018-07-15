package com.kunitskaya.po;

import com.kunitskaya.BaseTest;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmailingTest extends BaseTest {

    //TODO:
    // 1. add web driver desired conditions from the book

    String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());

    @Test(description = "log in to Gmail")
    public void logIn() {

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.open();

        loginPage.fillInUsername(USERNAME);
        loginPage.clickUsernameNextButton();

        String usernameForPassword = loginPage.getUsernameValue();
        assertEquals(usernameForPassword, USERNAME);

        loginPage.fillInPassword(PASSWORD);

        InboxPage inboxPage = loginPage.clickPasswordNextButton();
        assertTrue(inboxPage.isLoggedInAccountIconVisible());
    }

    @Test(description = "create an email,save it as a draft and send", dependsOnMethods = "logIn")
    public void sendDraftEmail() {
        BaseLoggedInPage baseLoggedInPage = new BaseLoggedInPage(webDriver);

        ComposeEmailPage composeEmailPage = baseLoggedInPage.clickComposeButton();
        composeEmailPage.fillInToField(TO);
        composeEmailPage.fillInSubjectField(subjectWithTimestamp);
        composeEmailPage.fillInBodyTextarea(BODY);
        composeEmailPage.clickCloseButton();

    }

//
//        WebElement draftsFolder = webDriver.findElement(By.partialLinkText("Drafts "));
//        draftsFolder.click();
//
//        WebElement draft = findEmailBySubject(webDriver, subjectWithTimestamp);
//
//        draft.click();
//
//        WebElement draftTo = webDriver.findElement(By.xpath("//span[@class='vN bfK a3q']"));
//        WebElement draftBody = webDriver.findElement(By.xpath("//span[contains(text(),'" + subjectWithTimestamp + "')]/following-sibling::span[1]"));
//
//        String draftSubject = draft.getText();
//        String draftToString = draftTo.getAttribute("email");
//        String draftBodyString = draftBody.getText();
//
//        assertEquals(draftSubject, subjectWithTimestamp);
//        assertEquals(draftToString, TO);
//        assertEquals(draftBodyString, " - " + BODY);
//
//        waitForElementVisibility(webDriver, 30, By.xpath("//div[text()='Send']"));
//        webDriver.findElement(By.xpath("//div[text()='Send']")).click();
//
//        webDriver.findElement(By.cssSelector(".ag.a8k"));
//        webDriver.navigate().refresh();
//
//        waitForElementVisibility(webDriver, 30, By.partialLinkText("Drafts "));
//
//        assertTrue(findEmailsBySubject(webDriver, subjectWithTimestamp).size() < 1);
//
//        WebElement sentFolder = webDriver.findElement(By.linkText("Sent Mail"));
//        sentFolder.click();
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

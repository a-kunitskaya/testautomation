package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.finders.MailFinderBySubject.findRecentMailBySubject;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementExplicitly;
import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;
import static org.testng.Assert.assertEquals;

public class SendEmailTest extends BaseTest {

    @BeforeClass
    public void verifyCurrentPage() {
        webDriver.get(INBOX);
    }

    @Test
    public void saveDraftEmail() {
        webDriver.findElement(By.xpath("//div[@gh='cm']")).click();
        waitForElementExplicitly(webDriver, 5, By.xpath("//textarea[@name='to']"));
        WebElement to = webDriver.findElement(By.xpath("//textarea[@name='to']"));
        waitImplicitly(webDriver, 10);
        to.sendKeys(TO);

        WebElement subject = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subject.sendKeys(SUBJECT);

        WebElement body = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        body.click();
        body.sendKeys(BODY);

        //wait for the draft email to be saved automatically
        waitImplicitly(webDriver, 10);

        //close the "New message" popup
        webDriver.findElement(By.xpath("//img[@alt='Close']")).click();

        waitImplicitly(webDriver, 10);

        webDriver.get(DRAFTS_PAGE);

        //wait for the page to load
        waitImplicitly(webDriver, 10);
        WebElement draft = findRecentMailBySubject(webDriver, SUBJECT);
        String draftSubject = draft.getText();

        assertEquals(draftSubject, SUBJECT);

    }

    @Test(dependsOnMethods = {"saveDraftEmail"})
    public void sendDraftEmail() {

        assertEquals(webDriver.getCurrentUrl(), DRAFTS_PAGE);
        WebElement draft = findRecentMailBySubject(webDriver, SUBJECT);
        draft.click();

        webDriver.findElement(By.xpath("//div[text()='Send']")).click();

        webDriver.get(SENT_PAGE);
        findRecentMailBySubject(webDriver, SUBJECT);
    }

}


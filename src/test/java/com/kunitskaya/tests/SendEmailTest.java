package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import com.kunitskaya.base.utils.finders.DraftFinderBySubject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static org.testng.Assert.assertEquals;

public class SendEmailTest extends BaseTest {

    @BeforeClass
    public void verifyCurrentPage() {
        assertEquals(webDriver.getCurrentUrl(), INBOX);
    }

    @Test(dependsOnMethods = "com.kunitskaya.tests.LogInTest.logIn")
    public void saveDraftEmail() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@gh='cm']")).click();

        Thread.sleep(2500);

        WebElement to = webDriver.findElement(By.xpath("//textarea[@name='to']"));

        Thread.sleep(2500);

        to.sendKeys(TO);

        WebElement subject = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));

        subject.sendKeys(SUBJECT);

        WebElement body = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));

        //click to move focus to the field
        body.click();
        body.sendKeys(BODY);

        //wait for the draft email to be saved automatically
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //close the "New message" popup
        webDriver.findElement(By.xpath("//img[@alt='Close']")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get(DRAFTS_PAGE);

        //wait for the page to load
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement draft = DraftFinderBySubject.findRecentDraftBySubject(webDriver, SUBJECT);

        String draftSubject = draft.getText();
        assertEquals(draftSubject, SUBJECT);

    }

    @Test(dependsOnMethods = "saveDraftEmail")
    public void sendDraftEmail() {

        assertEquals(webDriver.getCurrentUrl(), DRAFTS_PAGE);
        WebElement draft = DraftFinderBySubject.findRecentDraftBySubject(webDriver, SUBJECT);

        String draftId= draft.getAttribute("id");

        draft.click();

        webDriver.findElement(By.xpath("//div[text()='Send']")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }

}


package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static org.testng.Assert.assertEquals;

public class SendEmailTest extends BaseTest {

    @BeforeMethod
    public void verifyCurrentPage() {
        assertEquals(webDriver.getCurrentUrl(), INBOX);
    }

    @Test(dependsOnMethods = "com.kunitskaya.tests.LogInTest.logIn")
    public void saveDraftEmail() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@gh='cm']")).click();

        WebElement to = webDriver.findElement(By.xpath("//textarea[@name='to']"));

        Thread.sleep(2500);

        to.sendKeys(TO);

        WebElement subject = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));

        //click to move focus to the field
        subject.click();
        subject.sendKeys(SUBJECT);

        WebElement body = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));

        //click to move focus to the field
        body.click();
        body.sendKeys(BODY);

        //wait for the draft email to be saved automatically
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //close the "New message" popup
        webDriver.findElement(By.xpath("//img[@alt='Close']")).click();


        String toString = to.getAttribute("value");
        String subjectString = subject.getAttribute("value");
        String bodyString = body.getAttribute("value");

        //TODO: add asserts for strings, waits, test fails - inverstigate
        //StaleElementReferenceException: stale element reference: element is not attached to the page document
    }

    @Test(dependsOnMethods = "saveDraftEmail")
    public void sendDraftEmail(){

    }
}

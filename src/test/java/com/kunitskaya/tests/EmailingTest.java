package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static com.kunitskaya.base.utils.finders.MailFinderBySubject.findEmailBySubject;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementExplicitly;
import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmailingTest extends BaseTest {

    String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());

    @Test
    public void logIn() throws InterruptedException {
        webDriver.get(INBOX);

        waitImplicitly(webDriver, 30);

        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
        webDriver.findElement(By.id("identifierNext")).click();
        waitImplicitly(webDriver, 10);
        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");

        assertEquals(profileIdentifierValue, USERNAME);

        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
        waitForElementExplicitly(webDriver, 40, By.id("passwordNext"));
        webDriver.findElement(By.id("passwordNext")).click();

        Thread.sleep(6000);
        String actualPageAfterLogin = webDriver.getCurrentUrl();

        assertEquals(actualPageAfterLogin, INBOX);

        //check that the "Compose" button is displayed since only a logged-in user can see it
        WebElement composeButton = webDriver.findElement(By.xpath("//div[@gh='cm']"));
        assertTrue(composeButton.isDisplayed());

    }

    @Test(dependsOnMethods = "logIn", expectedExceptions = StaleElementReferenceException.class)
    public void sendDraftEmail() throws InterruptedException {
        webDriver.findElement(By.xpath("//div[@gh='cm']")).click();
        waitForElementExplicitly(webDriver, 5, By.xpath("//textarea[@name='to']"));
        WebElement to = webDriver.findElement(By.xpath("//textarea[@name='to']"));
        waitImplicitly(webDriver, 10);
        to.sendKeys(TO);

        WebElement subject = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subject.sendKeys(subjectWithTimestamp);

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
        WebElement draft = findEmailBySubject(webDriver, subjectWithTimestamp);

        String draftSubject = draft.getText();

        assertEquals(draftSubject, subjectWithTimestamp);

        draft.click();

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[text()='Send']")).click();
        Thread.sleep(2000);

        webDriver.navigate().refresh();
        Thread.sleep(4000);

        //should throw StaleElementReferenceException
        draft.click();

        webDriver.get(SENT_PAGE);
        assertTrue(draft.isDisplayed());
    }

    @Test(dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        webDriver.findElement(By.xpath("//span[@class='gb_db gbii']")).click();
        webDriver.findElement(By.id("gb_71")).click();

        waitForElementExplicitly(webDriver, 30, By.name("password"));

        WebElement enterPasswordField = webDriver.findElement(By.name("password"));
        assertTrue(enterPasswordField.isDisplayed());
    }
}

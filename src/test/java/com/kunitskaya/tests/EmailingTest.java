package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static com.kunitskaya.base.utils.finders.MailFinderBySubject.findEmailBySubject;
import static com.kunitskaya.base.waits.CustomFluentWait.waitForElementFluently;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementExplicitly;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmailingTest extends BaseTest {

    String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());

    @Test
    public void logIn() {
        webDriver.get(INBOX);

        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
        webDriver.findElement(By.id("identifierNext")).click();

        // waitImplicitly(webDriver, 10);

        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");

        assertEquals(profileIdentifierValue, USERNAME);

        waitForElementFluently(webDriver, 300, By.name("password"));

        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
        waitForElementExplicitly(webDriver, 40, By.id("passwordNext"));

        webDriver.findElement(By.id("passwordNext")).click();

        waitForElementVisibility(webDriver, 40, By.xpath("//div[@gh='cm']"));

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

        to.sendKeys(TO);

        WebElement subject = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subject.sendKeys(subjectWithTimestamp);

        WebElement body = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        body.click();
        body.sendKeys(BODY);

        //close the "New message" popup
        webDriver.findElement(By.xpath("//img[@alt='Close']")).click();

        webDriver.get(DRAFTS_PAGE);

        WebElement draft = findEmailBySubject(webDriver, subjectWithTimestamp);

        draft.click();

        WebElement draftTo = webDriver.findElement(By.xpath("//span[@class='vN bfK a3q']"));
        WebElement draftBody = webDriver.findElement(By.xpath("//span[contains(text(),'" + subjectWithTimestamp + "')]/following-sibling::span[1]"));


        String draftSubject = draft.getText();
        String draftToString = draftTo.getAttribute("email");
        String draftBodyString = draftBody.getText();


        assertEquals(draftSubject, subjectWithTimestamp);
        assertEquals(draftToString, TO);
        assertEquals(draftBodyString, " - " + BODY);

        //TODO: remove Thread.sleep if there is a better way
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

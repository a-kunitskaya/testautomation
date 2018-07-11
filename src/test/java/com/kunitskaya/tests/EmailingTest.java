package com.kunitskaya.tests;

import com.kunitskaya.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.kunitskaya.base.constants.AccountConstants.*;
import static com.kunitskaya.base.utils.DateTimeUtil.getSubjectTimestamp;
import static com.kunitskaya.base.utils.finders.MailFinderBySubject.findEmailBySubject;
import static com.kunitskaya.base.waits.ExplicitWait.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmailingTest extends BaseTest {

    String subjectWithTimestamp = SUBJECT.concat(getSubjectTimestamp());

    @Test
    public void logIn() {
        webDriver.get(INBOX);
        waitForPageLoadComplete(webDriver);

        webDriver.findElement(By.id("identifierId")).sendKeys(USERNAME);
        webDriver.findElement(By.id("identifierNext")).click();

        WebElement profileIdentifier = webDriver.findElement(By.id("profileIdentifier"));
        String profileIdentifierValue = profileIdentifier.getAttribute("data-email");

        assertEquals(profileIdentifierValue, USERNAME);

        waitForElementFluently(webDriver, By.name("password"));

        webDriver.findElement(By.name("password")).sendKeys(PASSWORD);
        waitForElementExplicitly(webDriver, 40, By.id("passwordNext"));

        webDriver.findElement(By.id("passwordNext")).click();

        waitForElementVisibility(webDriver, 40, By.xpath("//div[@gh='cm']"));

        String actualPageAfterLogin = webDriver.getCurrentUrl();
        assertEquals(actualPageAfterLogin, INBOX);

        WebElement loggedInAccountButton = webDriver.findElement(By.cssSelector(".gb_b.gb_db.gb_R"));
        assertTrue(loggedInAccountButton.isDisplayed());
    }

    @Test(dependsOnMethods = "logIn")
    public void sendDraftEmail() {
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

        WebElement draftsFolder = webDriver.findElement(By.partialLinkText("Drafts "));
        draftsFolder.click();

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

        waitForElementVisibility(webDriver, 30, By.xpath("//div[text()='Send']"));
        webDriver.findElement(By.xpath("//div[text()='Send']")).click();

        webDriver.findElement(By.cssSelector(".ag.a8k"));
        webDriver.navigate().refresh();

        waitForElementVisibility(webDriver, 30, By.partialLinkText("Drafts "));

        assertTrue(webDriver.findElements(By.xpath("//span[contains(text(), '" + subjectWithTimestamp + "')]")).size() < 1);

        WebElement sentFolder = webDriver.findElement(By.linkText("Sent Mail"));
        sentFolder.click();

        WebElement sentDraft = findEmailBySubject(webDriver, subjectWithTimestamp);
        sentDraft.click();

        assertEquals(draftSubject, subjectWithTimestamp);
        assertEquals(draftToString, TO);
        assertEquals(draftBodyString, " - " + BODY);
    }

    @Test(dependsOnMethods = "sendDraftEmail")
    public void logOut() {
        webDriver.findElement(By.cssSelector(".gb_b.gb_db.gb_R")).click();

        waitForElementVisibility(webDriver, 10, By.id("gb_71"));
        webDriver.findElement(By.id("gb_71")).click();

        waitForPageLoadComplete(webDriver);

        WebElement enterPasswordField = webDriver.findElement(By.name("password"));
        assertTrue(enterPasswordField.isDisplayed());
    }
}

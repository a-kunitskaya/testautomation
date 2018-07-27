package com.kunitskaya.po;

import org.openqa.selenium.By;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static com.kunitskaya.po.MailListingPage.MESSAGE_ROW_LOCATOR;

public class ComposeEmailPopup extends AbstractPage {
    private static final By TO_FIELD = By.xpath("//textarea[@name='to']");
    private static final By SUBJECT_FIELD = By.xpath("//input[@name='subjectbox']");
    private static final By BODY_FIELD = By.xpath("//div[@aria-label='Message Body']");
    private static final By CLOSE_BUTTON = By.xpath("//img[@alt='Close']");
    private static final By TO_VALUE = By.xpath("//span[@class='vN bfK a3q']");
    private static final By SEND_BUTTON = By.xpath("//div[text()='Send']");
    private static final By MESSAGE_SENT_LINK = By.id("link_vsm");
    private static final String TO_VALUE_ATTRIBUTE = "email";

    public ComposeEmailPopup fillInToField(String to) {
        waitForElementVisibility(webDriver, TO_FIELD);
        webDriver.findElement(TO_FIELD).sendKeys(to);
        return this;
    }

    public ComposeEmailPopup fillInSubjectField(String subject) {
        webDriver.findElement(SUBJECT_FIELD).sendKeys(subject);
        return this;
    }

    public ComposeEmailPopup fillInBodyField(String body) {
        webDriver.findElement(BODY_FIELD).sendKeys(body);
        return this;
    }

    public MailPage clickCloseButton() {
        webDriver.findElement(CLOSE_BUTTON).click();
        return new MailPage();
    }

    public String getEmailContent(String subject) {
        return getTo() + getBody(subject);
    }

    public String getBody(String subject) {
        return webDriver.findElement(By.xpath(String.format(MESSAGE_ROW_LOCATOR, subject))).getText();
    }

    public String getTo() {
        return webDriver.findElement(TO_VALUE).getAttribute(TO_VALUE_ATTRIBUTE);
    }

    public MailListingPage clickSendButton() {
        waitForElementToBeClickable(webDriver, SEND_BUTTON);
        webDriver.findElement(SEND_BUTTON).click();
        waitForElementVisibility(webDriver, MESSAGE_SENT_LINK);
        return new MailListingPage();
    }
}

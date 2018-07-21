package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComposeEmailPopup extends AbstractPage {
    private static final By TO_FIELD = By.xpath("//textarea[@name='to']");
    private static final By SUBJECT_FIELD = By.xpath("//input[@name='subjectbox']");
    private static final By BODY_FIELD = By.xpath("//div[@aria-label='Message Body']");
    private static final By CLOSE_BUTTON = By.xpath("//img[@alt='Close']");

    public ComposeEmailPopup(WebDriver driver) {
        super(driver);
    }

    public ComposeEmailPopup fillInToField(String to) {
        waitForElementVisibility(TO_FIELD);
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
        return new MailPage(webDriver);
    }
}

package com.kunitskaya.po;

import org.openqa.selenium.By;

import static com.kunitskaya.base.waits.ExplicitWait.*;


public class DraftsPage extends MailPage {
    private static final By TO = By.xpath("//span[@class='vN bfK a3q']");
    private static final By SEND_BUTTON = By.xpath("//div[text()='Send']");
    private static final By MESSAGE_SENT_LINK = By.id("link_vsm"); //By.cssSelector(".ag.a8k");
    private static final String MESSAGE_ROW_LOCATOR = "//span[contains(text(),'%s')]/following-sibling::span[1]";
    private static final String TO_VALUE_ATTRIBUTE = "email";

    public DraftsPage openDraftWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        return this;
    }

    public String getDraftContent(String subject) {
        return getTo() + getBody(subject);
    }

    public String getBody(String subject) {
        return webDriver.findElement(By.xpath(String.format(MESSAGE_ROW_LOCATOR, subject))).getText();
    }

    public String getTo() {
        return webDriver.findElement(TO).getAttribute(TO_VALUE_ATTRIBUTE);
    }

    public DraftsPage clickSendButton() {
        waitForElementToBeClickable(webDriver, SEND_BUTTON);
        webDriver.findElement(SEND_BUTTON).click();
        waitForElementVisibility(webDriver, MESSAGE_SENT_LINK);
        return this;
    }

    public boolean isDraftPresentOnPage(String subject) {
        webDriver.navigate().refresh();
        waitForPageLoadComplete(webDriver);
        return !findEmailsBySubject(webDriver,subject).isEmpty();
    }
}

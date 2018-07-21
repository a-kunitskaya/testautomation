package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailPage extends AbstractPage {

    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
    private static final By ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");
    private static final By SENT_MAIL_FOLDER_LINK = By.linkText("Sent Mail");
    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountIconVisible() {
        return webDriver.findElement(ACCOUNT_ICON).isDisplayed();
    }

    public MailPage clickAccountIcon() {
        webDriver.findElement(ACCOUNT_ICON).click();
        waitForElementToBeClickable(SIGN_OUT_BUTTON);
        return this;
    }

    public LogoutPage clickSignOutButton() {
        webDriver.findElement(SIGN_OUT_BUTTON).click();
        waitForPageLoadComplete();
        return new LogoutPage(webDriver);
    }

    public ComposeEmailPopup clickComposeButton() {
        webDriver.findElement(COMPOSE_BUTTON).click();
        return new ComposeEmailPopup(webDriver);
    }

    public DraftsPage clickDraftsFolderLink() {
        webDriver.findElement(DRAFTS_FOLDER_LINK).click();
        waitForPageLoadComplete();
        return new DraftsPage(webDriver);
    }

    public SentMailPage clickSentMailLink() {
        webDriver.findElement(SENT_MAIL_FOLDER_LINK).click();
        waitForPageLoadComplete();
        return new SentMailPage(webDriver);
    }

    public static WebElement findEmailBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElement(By.xpath("//span[contains(text(), '" + subject + "')]"));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }

    public static List<WebElement> findEmailsBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            List<WebElement> emails = webDriver.findElements(By.xpath("//span[contains(text(), '" + subject + "')]"));
            return emails;
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }
}

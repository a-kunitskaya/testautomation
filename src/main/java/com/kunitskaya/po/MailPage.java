package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class MailPage extends AbstractPage {

    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
    private static final By ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");
    private static final By SENT_MAIL_FOLDER_LINK = By.linkText("Sent Mail");
    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");

    public boolean isAccountIconVisible() {
        return webDriver.findElement(ACCOUNT_ICON).isDisplayed();
    }

    public MailPage clickAccountIcon() {
        webDriver.findElement(ACCOUNT_ICON).click();
        waitForElementToBeClickable(webDriver, SIGN_OUT_BUTTON);
        return this;
    }

    public LogoutPage clickSignOutButton() {
        webDriver.findElement(SIGN_OUT_BUTTON).click();
        waitForPageLoadComplete(webDriver);
        return new LogoutPage();
    }

    public ComposeEmailPopup clickComposeButton() {
        webDriver.findElement(COMPOSE_BUTTON).click();
        return new ComposeEmailPopup();
    }

    public DraftsPage clickDraftsFolderLink() {
        webDriver.findElement(DRAFTS_FOLDER_LINK).click();
        waitForPageLoadComplete(webDriver);
        return new DraftsPage();
    }

    public SentMailPage clickSentMailLink() {
        webDriver.findElement(SENT_MAIL_FOLDER_LINK).click();
        waitForPageLoadComplete(webDriver);
        return new SentMailPage();
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

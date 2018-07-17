package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseLoggedInPage extends AbstractPage {

    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
    private static final By LOGGED_IN_ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");
    private static final By SENT_MAIL_FOLDER_LINK = By.linkText("Sent Mail");
    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");


    public BaseLoggedInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedInAccountIconVisible() {
        if (webDriver.findElement(LOGGED_IN_ACCOUNT_ICON).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public BaseLoggedInPage clickLoggedInAccountIcon() {
        webDriver.findElement(LOGGED_IN_ACCOUNT_ICON).click();
        waitForElementToBeClickable(SIGN_OUT_BUTTON);
        return this;
    }

    public LogoutPage clickSignOutButton() {
        webDriver.findElement(SIGN_OUT_BUTTON).click();
        waitForPageLoadComplete();
        return new LogoutPage(webDriver);
    }

    public ComposeEmailPage clickComposeButton() {
        webDriver.findElement(COMPOSE_BUTTON).click();
        return new ComposeEmailPage(webDriver);
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
}

package com.kunitskaya.po;

import org.openqa.selenium.By;

import java.io.IOException;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;

public class MailPage extends AbstractPage {
    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
    private static final By ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");
    private static final By SENT_MAIL_FOLDER_LINK = By.linkText("Sent Mail");
    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");

    public MailPage() {
        super();
    }

    public boolean isAccountIconVisible() {
        return webDriver.findElement(ACCOUNT_ICON).isDisplayed();
    }

    public MailPage clickAccountIcon() throws IOException {
        webDriver.findElement(ACCOUNT_ICON).click();
        waitForElementToBeClickable(webDriver, SIGN_OUT_BUTTON);
        return this;
    }

    public LoginPage clickSignOutButton() {
        webDriver.findElement(SIGN_OUT_BUTTON).click();
        return new LoginPage();
    }

    public ComposeEmailPopup clickComposeButton() {
        webDriver.findElement(COMPOSE_BUTTON).click();
        return new ComposeEmailPopup();
    }

    public MailListingPage clickDraftsFolderLink() {
        webDriver.findElement(DRAFTS_FOLDER_LINK).click();
        return new MailListingPage();
    }

    public MailListingPage clickSentMailLink() {
        webDriver.findElement(SENT_MAIL_FOLDER_LINK).click();
        return new MailListingPage();
    }


}

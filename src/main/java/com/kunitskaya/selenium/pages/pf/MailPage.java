package com.kunitskaya.selenium.pages.pf;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementToBeClickable;

public class MailPage extends AbstractPage {

    @FindBy(css = ".gb_b.gb_eb.gb_R")
    WebElement accountIcon;

    @FindBy(xpath = "//div[@data-tooltip='Settings' or @title='Settings']")
    WebElement settingsButton;

    @FindBy(xpath = "//div[@role='menuitem' and .='Help']")
    WebElement helpSettingsOption;

    @FindBy(xpath = "//div[@gh='cm']")
    public WebElement composeButton;

    @FindBy(linkText = "Sent Mail")
    WebElement sentMailFolder;

    @FindBy(partialLinkText = "Drafts ")
    WebElement draftsFolderLink;

    @FindBy(linkText = "Sign out")
    WebElement signOutButton;

    public MailPage() {
        super();
    }

    public boolean isAccountIconVisible() {
        try {
            return accountIcon.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public MailPage clickSettingsButton() {
        waitForElementToBeClickable(webDriver, settingsButton);
        settingsButton.click();
        return this;
    }

    public HelpPopup clickHelpSettingsOption() {
        waitForElementToBeClickable(webDriver, settingsButton);
        helpSettingsOption.click();
        return new HelpPopup();
    }

    public ComposeEmailPopup clickComposeButton() {
        new Actions(webDriver).click(composeButton).build().perform();
        return new ComposeEmailPopup();
    }

    public MailListingPage clickSentMailLink() {
        new Actions(webDriver).click(sentMailFolder).build().perform();
        return new MailListingPage();
    }

    public MailPage clickAccountIcon() {
        accountIcon.click();
        waitForElementToBeClickable(webDriver, signOutButton);
        return this;
    }

    public LoginPage clickSignOutButton() {
        signOutButton.click();
        return new LoginPage();
    }

    public MailListingPage clickDraftsFolderLink() {
        draftsFolderLink.click();
        return new MailListingPage();
    }

}

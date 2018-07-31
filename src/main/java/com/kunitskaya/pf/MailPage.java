package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;

public class MailPage extends AbstractPage {

    @FindBy(css = ".gb_b.gb_db.gb_R")
    WebElement accountIcon;

    @FindBy(xpath = "//div[@data-tooltip='Settings' or @title='Settings']")
    WebElement settingsButton;

    @FindBy(xpath = "//div[@role='menuitem' and .='Help']")
    WebElement helpSettingsOption;

    @FindBy(xpath = "//div[@gh='cm']")
    WebElement composeButton;

    @FindBy(linkText = "Sent Mail")
    WebElement sentMailFolder;

    public MailPage() {
        super();
    }

    public boolean isAccountIconVisible() {
        return accountIcon.isDisplayed();
    }

    public MailPage clickSettingsButton() throws IOException {
        waitForElementToBeClickable(webDriver, settingsButton);
        settingsButton.click();
        return this;
    }

    public HelpPopup clickHelpSettingsOption() throws IOException {
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
}

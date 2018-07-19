package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseLoggedInPageFactory extends AbstractPageFactory {

    @FindBy(css = ".gb_b.gb_db.gb_R")
    WebElement loggedInAccountIcon;

    @FindBy(id = ":28")
    WebElement settingsButton;

    @FindBy(xpath = "//div[@role='menuitem' and .='Help']")
    WebElement helpSettingsOption;

    @FindBy(id = ":6p")
    WebElement settingsDropdown;

//    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
//    private static final By LOGGED_IN_ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
//    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");
//    private static final By SENT_MAIL_FOLDER_LINK = By.linkText("Sent Mail");
//    private static final By SIGN_OUT_BUTTON = By.linkText("Sign out");

    protected BaseLoggedInPageFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isLoggedInAccountIconVisible() {
        if (loggedInAccountIcon.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public BaseLoggedInPageFactory clickSettingsButton() {
        settingsButton.click();
        return this;
    }

    public HelpPopupFactory clickHelpSettingsOption() {
        helpSettingsOption.click();
        waitForAjaxExecution();
        return new HelpPopupFactory(webDriver);
    }

//    public BaseLoggedInPage clickLoggedInAccountIcon() {
//        webDriver.findElement(LOGGED_IN_ACCOUNT_ICON).click();
//        waitForElementToBeClickable(SIGN_OUT_BUTTON);
//        return this;
//    }
//
//    public LogoutPage clickSignOutButton() {
//        webDriver.findElement(SIGN_OUT_BUTTON).click();
//        waitForPageLoadComplete();
//        return new LogoutPage(webDriver);
//    }
//
//    public ComposeEmailPage clickComposeButton() {
//        webDriver.findElement(COMPOSE_BUTTON).click();
//        return new ComposeEmailPage(webDriver);
//    }
//
//    public DraftsPage clickDraftsFolderLink() {
//        webDriver.findElement(DRAFTS_FOLDER_LINK).click();
//        waitForPageLoadComplete();
//        return new DraftsPage(webDriver);
//    }
//
//    public SentMailPage clickSentMailLink() {
//        webDriver.findElement(SENT_MAIL_FOLDER_LINK).click();
//        waitForPageLoadComplete();
//        return new SentMailPage(webDriver);
//    }
}

package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseLoggedInPage extends AbstractPage {

    private static final By COMPOSE_BUTTON = By.xpath("//div[@gh='cm']");
    private static final By LOGGED_IN_ACCOUNT_ICON = By.cssSelector(".gb_b.gb_db.gb_R");
    private static final By DRAFTS_FOLDER_LINK = By.partialLinkText("Drafts ");


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

    public ComposeEmailPage clickComposeButton() {
        webDriver.findElement(COMPOSE_BUTTON).click();
        return new ComposeEmailPage(webDriver);
    }

    public DraftsPage clickDraftsFolderLink(){
        webDriver.findElement(DRAFTS_FOLDER_LINK).click();
        waitForPageLoadComplete();
        return new DraftsPage(webDriver);
    }
}

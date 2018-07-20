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
}

package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpPageFactory extends BaseLoggedInPageFactory {

    @FindBy(xpath = "//h1")
    WebElement helpPageHeader;

    @FindBy(xpath = "//*[@class = 'gb_5e' and @name='q']")
    WebElement searchField;

    protected GmailHelpPageFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public String getHeplPageHeader() {
        return helpPageHeader.getText();
    }

    public String getSearchFieldPlaceholder() {
        return searchField.getAttribute("placeholder");
    }

}

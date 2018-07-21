package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpPage extends AbstractPage {
    public static final String HELP_PAGE_HEADER = "Welcome to the Gmail Help Center";
    public static final String HELP_PAGE_TITLE = "Gmail Help";
    public static final String HELP_PAGE_SEARCH_PLACEHOLDER = "Describe your issue";

    @FindBy(css = "nav.accordion-homepage h1")
    WebElement helpPageHeader;

    @FindBy(xpath = "//*[@class = 'gb_5e' and @name='q']")
    WebElement searchField;

    public String getHelpPageHeader() {
        return helpPageHeader.getText();
    }

    public String getSearchFieldPlaceholder() {
        return searchField.getAttribute("placeholder");
    }

}

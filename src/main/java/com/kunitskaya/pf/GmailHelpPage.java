package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpPage extends AbstractPage {
    public static final String HELP_PAGE_HEADER = "Welcome to the Gmail Help Center";
    public static final String HELP_PAGE_TITLE = "Gmail Help";
    public static final String HELP_PAGE_SEARCH_PLACEHOLDER = "Describe your issue";

    @FindBy(css = "nav.accordion-homepage h1")
    WebElement header;

    @FindBy(xpath = "//input[@class = 'gb_5e' and @name='q']")
    WebElement searchField;

    public String getHeader() {
        return header.getText();
    }

    public String getSearchFieldPlaceholder() {
        return searchField.getAttribute("placeholder");
    }

}

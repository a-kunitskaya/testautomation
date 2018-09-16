package com.kunitskaya.selenium.pages.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpPage extends AbstractPage {
    public static final String HELP_PAGE_HEADER = "How can we help you?";
    public static final String HELP_PAGE_TITLE = "Gmail Help";
    public static final String HELP_PAGE_SEARCH_PLACEHOLDER = "Describe your issue";

    @FindBy(xpath = "//h1/div/following-sibling::div")
    WebElement header;

    @FindBy(xpath = "//input[@class = 'promoted-search__input' and @name='q']")
    WebElement searchField;

    public GmailHelpPage() {
        super();
    }

    public String getHeader() {
        highlightElement(header);
        String result = header.getText();
        unHighlightElement(header);
        return result;
    }

    public String getSearchFieldPlaceholder() {
        highlightElement(searchField);
        String result = searchField.getAttribute("placeholder");
        unHighlightElement(searchField);
        return result;
    }
}

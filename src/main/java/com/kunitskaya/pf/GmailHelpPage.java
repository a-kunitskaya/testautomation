package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpPage extends AbstractPage {

    @FindBy(css = "nav.accordion-homepage h1")//(xpath = "//h1")
    WebElement helpPageHeader;

    @FindBy(xpath = "//*[@class = 'gb_5e' and @name='q']") //(xpath = "//*[@class = 'gb_5e' and @name='q']")
    WebElement searchField;

    protected GmailHelpPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getHelpPageHeader() {
        return helpPageHeader.getText();
    }

    public String getSearchFieldPlaceholder() {
        return searchField.getAttribute("placeholder");
    }

}

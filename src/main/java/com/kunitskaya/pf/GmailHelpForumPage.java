package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpForumPage extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'Welcome')]")
    WebElement welcomeText;

    @FindBy(id = "b_new_topic")
    WebElement newTopicButton;

    @FindBy(id = "gbqfq")
    WebElement searchField;

    protected GmailHelpForumPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getSearchFieldPlaceholder() {
        waitForElementVisibility(searchField);
        return searchField.getAttribute("placeholder");
    }

    public String getWelcomeText() {
        waitForElementVisibility(welcomeText);
        return welcomeText.getText();
    }

    public boolean isNewTopicButtonVisible() {
        return newTopicButton.isDisplayed();
    }
}

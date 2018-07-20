package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHelpForumFactory extends BaseLoggedInPageFactory {

    @FindBy(xpath = "//span[contains(text(),'Welcome')]")
    WebElement welcomeText;

    @FindBy(id = "b_new_topic")
    WebElement newTopicButton;

    @FindBy(id = "gbqfq")
    WebElement searchField;

    protected GmailHelpForumFactory(WebDriver webDriver) {
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

    public boolean isNewTopickButtonVIsible() {
        waitForElementVisibility(newTopicButton);
        if (newTopicButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}

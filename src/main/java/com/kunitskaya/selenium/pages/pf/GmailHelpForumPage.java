package com.kunitskaya.selenium.pages.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementVisibility;

public class GmailHelpForumPage extends AbstractPage {
    public static final String FORUM_PAGE_TITLE = "Gmail Help Forum";
    public static final String FORUM_WELCOME_TEXT = "Welcome to the official Gmail Help Forum!";
    public static final String FORUM_SEARCH_PLACEHOLDER = "Search for messages";

    @FindBy(xpath = "//span[contains(text(),'Welcome')]")
    WebElement welcomeText;

    @FindBy(id = "b_new_topic")
    WebElement newTopicButton;

    @FindBy(id = "gbqfq")
    WebElement searchField;

    public GmailHelpForumPage() {
        super();
    }

    public String getSearchFieldPlaceholder() {
        waitForElementVisibility(webDriver, searchField);
        highlightElement(searchField);
        String result = searchField.getAttribute("placeholder");
        unHighlightElement(searchField);
        return result;
    }

    public String getWelcomeText() {
        waitForElementVisibility(webDriver, welcomeText);
        highlightElement(welcomeText);
        String result = welcomeText.getText();
        unHighlightElement(welcomeText);
        return result;
    }

    public boolean isNewTopicButtonVisible() {
        highlightElement(newTopicButton);
        return newTopicButton.isDisplayed();
    }
}

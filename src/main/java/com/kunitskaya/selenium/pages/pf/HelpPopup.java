package com.kunitskaya.selenium.pages.pf;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementVisibility;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class HelpPopup extends AbstractPage {

    @FindBy(xpath = "//div[@id = 'dialog' and @class = 'ghp-dialog ng-scope']")
    WebElement helpPopup;

    @FindBy(id = "search-box")
    WebElement helpSearchField;

    @FindAll(@FindBy(css = ".ghp-iconTextComponent-label.ng-binding"))
    List<WebElement> searchResults;

    @FindBy(id = "google-feedback-wizard")
    WebElement helpPopupFrame;

    @FindBy(xpath = "//li[contains(., 'Browse All Articles')]/a")
    WebElement browseAllArticlesLink;

    @FindBy(xpath = "//a[contains(@href, 'productforums')]")
    WebElement visitHelpForumLink;

    @FindBy(xpath = "//button[contains(., 'Send feedback')]")
    WebElement sendFeedbackButton;

    public HelpPopup() {
        super();
    }

    public boolean isDisplayed() {
        if (helpPopupFrame.isDisplayed()) {
            switchToHelpPopupFrame();
            return helpPopup.isDisplayed();
        } else {
            return false;
        }

    }

    public HelpPopup switchToHelpPopupFrame() {
        webDriver.switchTo().frame(helpPopupFrame);
        return this;
    }

    public HelpPopup enterSearchCriteria(String input) {
        waitForElementVisibility(webDriver, helpSearchField);
        helpSearchField.sendKeys(input);
        helpSearchField.submit();
        waitForElementVisibility(webDriver, searchResults.get(0));
        return this;
    }

    public List<String> getSearchResults() {
        List<String> results = new ArrayList<>();
        for (WebElement result : searchResults) {
            waitForElementVisibility(webDriver, result);
            results.add(result.getText());
        }
        return results;
    }

    public HelpPopup clearSearchField() {
        new Actions(webDriver).click(helpSearchField)
                              .sendKeys(Keys.chord(IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL, "a", Keys.DELETE), Keys.ENTER)
                              .build()
                              .perform();
        waitForElementVisibility(webDriver, browseAllArticlesLink);
        return this;
    }

    public GmailHelpPage clickBrowseAllArticlesLink() {
        waitForElementToBeClickable(webDriver, browseAllArticlesLink);
        browseAllArticlesLink.click();
        return new GmailHelpPage();
    }

    public GmailHelpForumPage clickVisitHelpForumLink() {
        waitForElementToBeClickable(webDriver, visitHelpForumLink);
        visitHelpForumLink.click();
        return new GmailHelpForumPage();
    }

    public FeedbackPopup clickSendFeedBackButton() {
        waitForElementToBeClickable(webDriver, sendFeedbackButton);
        sendFeedbackButton.click();
        return new FeedbackPopup();
    }
}

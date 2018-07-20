package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HelpPopupFactory extends BaseLoggedInPageFactory {

    @FindBy(xpath = "//div[@id = 'dialog' and @class = 'ghp-dialog ng-scope']")
    WebElement helpPopup;

    @FindBy(id = "search-box")
    WebElement helpSearchField;

    @FindAll(@FindBy(css = ".ghp-iconTextComponent-label.ng-binding"))
    List<WebElement> searchResults;

    @FindBy(id = "google-feedback-wizard")
    WebElement helpPopopFrame;

    @FindBy(xpath = "//a[@href='https://support.google.com/mail?hl=en']")
    WebElement browseAllArticlesLink;

    @FindBy(xpath = "//a[@href='https://productforums.google.com/forum/#!forum/gmail']")
    WebElement visitHelpForumLink;

    @FindBy(css = ".ghp-iconTextComponent.ghpv-block.ng-scope")
    WebElement sendFeedbackButton;

    protected HelpPopupFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isHelpPopupDisplayed() {
        switchToHelpPopup();
        if (helpPopup.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public HelpPopupFactory switchToHelpPopup() {
        webDriver.switchTo().frame(helpPopopFrame);
        return this;
    }

    public HelpPopupFactory enterTextToHelpSearchField(String input) {
        waitForElementVisibility(helpSearchField);
        helpSearchField.click();
        helpSearchField.sendKeys(input);
        helpSearchField.submit();
        return this;
    }

    public List<WebElement> getSearchResults() {
        waitForAjaxExecution();
        return searchResults;
    }

    public HelpPopupFactory clearSearchField() {
        helpSearchField.clear();
        helpSearchField.click();
        return this;
    }

    public GmailHelpPageFactory clickBrowseAllArticlesLink() {
        waitForElementToBeClickable(browseAllArticlesLink);
        browseAllArticlesLink.click();
        waitForPageLoadComplete();
        return new GmailHelpPageFactory(webDriver);
    }

    public GmailHelpForumFactory clickVisitHelpForumLink() {
        waitForElementToBeClickable(visitHelpForumLink);
        visitHelpForumLink.click();
        waitForPageLoadComplete();
        return new GmailHelpForumFactory(webDriver);
    }

    public FeedbackPopupFactory clickSendFeedBackLink() {
        waitForElementToBeClickable(sendFeedbackButton);
        sendFeedbackButton.click();
        waitForAjaxExecution();
        return new FeedbackPopupFactory(webDriver);
    }
}

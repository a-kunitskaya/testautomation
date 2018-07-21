package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HelpPopup extends AbstractPage {

    @FindBy(xpath = "//div[@id = 'dialog' and @class = 'ghp-dialog ng-scope']")
    WebElement helpPopup;

    @FindBy(id = "search-box")
    WebElement helpSearchField;

    @FindAll(@FindBy(css = "li.sbsb_c div[role=option]"))  //(@FindBy(css = ".ghp-iconTextComponent-label.ng-binding"))
    List<WebElement> suggestedSearchResults;

    @FindBy(id = "google-feedback-wizard")
    WebElement helpPopupFrame;

    @FindBy(xpath = "//a[contains(@href, 'https://support.google.com/mail?hl')])") //(xpath = "//a[@href='https://support.google.com/mail?hl=en']")
    WebElement browseAllArticlesLink;

    @FindBy(xpath = "contains(@href, 'productforums')")//(xpath = "//a[@href='https://productforums.google.com/forum/#!forum/gmail']")
    WebElement visitHelpForumLink;

    @FindBy(xpath = "//button[contains(., 'Send feedback')]")//(css = ".ghp-iconTextComponent.ghpv-block.ng-scope")
    WebElement sendFeedbackButton;

    protected HelpPopup(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isHelpPopupDisplayed() {
        if(helpPopupFrame.isDisplayed()){
            switchToHelpPopupFrame();
            return helpPopup.isDisplayed();
        }
        else{
            return false;
        }

    }

    public HelpPopup switchToHelpPopupFrame() {
        webDriver.switchTo().frame(helpPopupFrame);
        return this;
    }

    public HelpPopup enterTextToHelpSearchField(String input) {
        waitForElementVisibility(helpSearchField);
        //helpSearchField.click();
        helpSearchField.sendKeys(input);
        //helpSearchField.submit();
        return this;
    }

    public List<WebElement> getSuggestedSearchResults() {
        return suggestedSearchResults;
    }

    public HelpPopup clearSearchField() {
        helpSearchField.clear();
        //helpSearchField.click();
        return this;
    }

    public GmailHelpPage clickBrowseAllArticlesLink() {
        waitForElementToBeClickable(browseAllArticlesLink);
        browseAllArticlesLink.click();
       // waitForPageLoadComplete();
        return new GmailHelpPage(webDriver);
    }

    public GmailHelpForumPage clickVisitHelpForumLink() {
        waitForElementToBeClickable(visitHelpForumLink);
        visitHelpForumLink.click();
        //waitForPageLoadComplete();
        return new GmailHelpForumPage(webDriver);
    }

    public FeedbackPopup clickSendFeedBackLink() {
        waitForElementToBeClickable(sendFeedbackButton);
        sendFeedbackButton.click();
        return new FeedbackPopup(webDriver);
    }
}

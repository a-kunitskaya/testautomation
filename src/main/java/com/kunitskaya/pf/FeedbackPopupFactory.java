package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeedbackPopupFactory extends BaseLoggedInPageFactory {

    @FindBy(id = "google-feedback-wizard")
    WebElement feedbackPopupFrame;

    @FindBy(xpath = "//h1")
    WebElement title;

    @FindBy(xpath = "//textarea//preceding-sibling::div/div")
    WebElement inputField;

    @FindBy(xpath = "//*[@xmlns='https://www.w3.org/2000/svg']")
    WebElement includeScreenshotCheckbox;

    @FindBy(xpath = "//uf-material-button[@key='cancel']")
    WebElement cancelButton;

    @FindBy(xpath = "//button[@key='send']")
    WebElement sendButton;

    @FindBy(xpath = "//div[@key='dialog']")
    WebElement sendFeedbackPopup;

    protected FeedbackPopupFactory(WebDriver webDriver) {
        super(webDriver);
    }

    public String getHeader() {
        waitForPageLoadComplete();
        return title.getText();
    }

    public String getInputFieldPlaceholder() {
        waitForElementVisibility(inputField);
        return inputField.getText();
    }

    public boolean isIncludeScreenshotCheckboxChecked() {
        waitForElementVisibility(includeScreenshotCheckbox);
        if (includeScreenshotCheckbox.getAttribute("fill").equals("#4285F4")) {
            return true;
        } else if (includeScreenshotCheckbox.getAttribute("fill").equals("#757575")) {
            return false;
        } else {
            System.out.println("Unable to get includeScreenshotCheckbox value");
            return false;
        }
    }

    public boolean isCancelButtonDisplayed() {
        waitForElementToBeClickable(cancelButton);
        if (cancelButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSendButtonDisplayed() {
        waitForElementToBeClickable(sendButton);
        if (sendButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isSendFeedbackPopupDisplayed() {
        waitForElementVisibility(sendFeedbackPopup);
        if (sendFeedbackPopup.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public BaseLoggedInPageFactory clickCancelButton() {
        waitForElementToBeClickable(cancelButton);
        cancelButton.click();
        waitForAjaxExecution();
        return new BaseLoggedInPageFactory(webDriver);
    }

    public FeedbackPopupFactory switchToFeedbackFrame() {
        webDriver.switchTo().frame(feedbackPopupFrame);
        return this;
    }
}

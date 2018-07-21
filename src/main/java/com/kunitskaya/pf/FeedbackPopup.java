package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class FeedbackPopup extends AbstractPage {
    public static final String FEEDBACK_POPUP_HEADER = "Send feedback";
    public static final String FEEDBACK_POPUP_INPUT_PLACEHOLDER = "Describe your issue or share your ideas";

    @FindBy(id = "google-feedback-wizard")
    WebElement feedbackPopupFrame;

    @FindBy(xpath = "//header//h1")
            WebElement title;

    @FindBy(xpath = "//textarea")
    WebElement inputField;

    @FindBy(xpath = "//textarea/preceding-sibling::div/div")
    WebElement inputFieldPlaceholder;

    @FindBy(xpath = "//label[@key='include-screenshot']//input[@type='checkbox']")
            WebElement includeScreenshotCheckbox;

    @FindBy(xpath = "//uf-material-button[@key='cancel']")
    WebElement cancelButton;

    @FindBy(xpath = "//button[@key='send']")
    WebElement sendButton;

    @FindBy(xpath = "//div[@key='dialog']")
    WebElement sendFeedbackPopup;

    @FindAll(@FindBy(xpath = "//div[@key='dialog']"))
    List<WebElement> sendFeedbackPopups;


    public String getHeader() {
        return title.getText();
    }

    public String getInputFieldPlaceholder() {
        waitForElementVisibility(webDriver, inputFieldPlaceholder);
        return inputFieldPlaceholder.getText();
    }

    public boolean isIncludeScreenshotCheckboxChecked() {
        waitForElementVisibility(webDriver, includeScreenshotCheckbox);
        return includeScreenshotCheckbox.isSelected();
    }

    public boolean isCancelButtonDisplayed() {
        return cancelButton.isDisplayed();
    }

    public boolean isSendButtonDisplayed() {
        return sendButton.isDisplayed();
    }

    //can't simplify to sendFeedbackPopup.isDisplayed(), since it tries to find element and throws exception if it can't
    public boolean isSendFeedbackPopupDisplayed() {
        if (sendFeedbackPopups.size() < 1) {
            return false;
        } else {
            return sendFeedbackPopup.isDisplayed();
        }
    }

    public boolean isInputFieldDIsplayed() {
        return inputField.isDisplayed();
    }

    public MailPage clickCancelButton() {
        waitForElementToBeClickable(webDriver, cancelButton);
        cancelButton.click();
        return new MailPage();
    }

    public FeedbackPopup switchToFeedbackFrame() {
        webDriver.switchTo().frame(feedbackPopupFrame);
        return this;
    }
}

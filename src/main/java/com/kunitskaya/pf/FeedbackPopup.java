package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//button[@key='annotate']")
    WebElement screenshotButton;

    @FindBy(xpath = "//button[@key='done']")
    WebElement screenshotDoneButton;

    public FeedbackPopup() {
        super();
    }

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

    public boolean isSendFeedbackPopupDisplayed() {
        return !sendFeedbackPopups.isEmpty() && sendFeedbackPopup.isDisplayed();
    }

    public boolean isInputFieldDisplayed() {
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

    public FeedbackPopup makeScreenshot() {
        new Actions(webDriver).click(screenshotButton)
                              .clickAndHold()
                              .moveByOffset(300, 300)
                              .release()
                              .click(screenshotDoneButton)
                              .build()
                              .perform();
        return this;
    }
}

package com.kunitskaya.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementPresence;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class FeedbackPopup extends AbstractPage {

    //переименовать на ...page все

    @FindBy(id = "google-feedback-wizard")
    WebElement feedbackPopupFrame;

    @FindBy(xpath = "//header//h1")//(xpath = "//h1")
            WebElement title;

    @FindBy(xpath = "//textarea")
    WebElement inputField;

    @FindBy(xpath = "//textarea/preceding-sibling::div/div")
    WebElement inputFieldPlaceholder;

    @FindBy(xpath = "//label[@key='include-screenshot']//input[@type='checkbox']") //(xpath = "//*[@xmlns='https://www.w3.org/2000/svg']")
            WebElement includeScreenshotCheckbox;

    @FindBy(xpath = "//uf-material-button[@key='cancel']")
    WebElement cancelButton;

    @FindBy(xpath = "//button[@key='send']")
    WebElement sendButton;

    @FindBy(xpath = "//div[@key='dialog']")
    WebElement sendFeedbackPopup;

    protected FeedbackPopup(WebDriver webDriver) {
        super(webDriver);
    }

    public String getHeader() {
        return title.getText();
    }

    public String getInputFieldPlaceholder() {
        waitForElementVisibility(inputFieldPlaceholder);
        return inputFieldPlaceholder.getText();
    }

    public boolean isIncludeScreenshotCheckboxChecked() {
        waitForElementVisibility(includeScreenshotCheckbox);
//        if (includeScreenshotCheckbox.getAttribute("fill").equals("#4285F4")) {
//            return true;
//        } else if (includeScreenshotCheckbox.getAttribute("fill").equals("#757575")) {
//            return false;
//        } else {
//            System.out.println("Unable to get includeScreenshotCheckbox value");
//            return false;
//        }
        includeScreenshotCheckbox.isSelected();

    }

    public boolean isCancelButtonDisplayed() {
        return cancelButton.isDisplayed();
    }

    public boolean isSendButtonDisplayed() {
        return sendButton.isDisplayed();
    }


    public boolean isSendFeedbackPopupDisplayed() {
        return sendFeedbackPopup.isDisplayed();
    }

    public boolean isInputFieldDIsplayed() {
        return inputField.isDisplayed();
    }

    public MailPage clickCancelButton() {
        waitForElementToBeClickable(cancelButton);
        cancelButton.click();
        return new MailPage(webDriver);
    }

    public FeedbackPopup switchToFeedbackFrame() {
        webDriver.switchTo().frame(feedbackPopupFrame);
        return this;
    }
}

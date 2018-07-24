package com.kunitskaya.pf;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;

public class ComposeEmailPopup extends AbstractPage {

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement toField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectField;

    @FindBy(xpath = "//div[@role='alertdialog']")
    WebElement errorPopup;

    @FindBy(name = "ok")
    WebElement okButton;

    public ComposeEmailPopup fillInToField(String to) {
        waitForElementVisibility(webDriver, toField);
        new Actions(webDriver).click(toField).sendKeys(to).build().perform();
        new Actions(webDriver).sendKeys(Keys.TAB).build().perform();
        return this;
    }

    public ComposeEmailPopup fillInSubjectField(String subject) {
        new Actions(webDriver).sendKeys(subject).build().perform();
        return this;
    }

    public ComposeEmailPopup sendEmailWithHotkeys() {
        new Actions(webDriver).click(subjectField).build().perform();
        new Actions(webDriver).sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER)).build().perform();
        return this;
    }

    public boolean isErrorPopupDisplayed() {
        return errorPopup.isDisplayed();
    }

    public ComposeEmailPopup clickOkButton() {
        new Actions(webDriver).click(okButton).build().perform();
        return this;
    }
}

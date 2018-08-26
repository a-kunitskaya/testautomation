package com.kunitskaya.pages.pf;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementToBeClickable;
import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static com.kunitskaya.pages.pf.MailListingPage.SUBJECT_LOCATOR;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class ComposeEmailPopup extends AbstractPage {
    private static final String TO_VALUE_ATTRIBUTE = "email";
    private static final String SUBJECT_VALUE_ATTRIBUTE = "value";

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement toField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectField;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    WebElement bodyField;

    @FindBy(xpath = "//img[@alt='Close']")
    WebElement closeButton;

    @FindBy(xpath = "//span[@class='vN bfK a3q']")
    WebElement toValue;

    @FindBy(xpath = "//div[text()='Send']")
    WebElement sendButton;

    @FindBy(id = "link_vsm")
    WebElement messageSentLink;

    public ComposeEmailPopup() {
        super();
    }

    public ComposeEmailPopup fillInToField(String to) {
        waitForElementVisibility(webDriver, toField);
        new Actions(webDriver).click(toField).sendKeys(to).sendKeys(Keys.TAB).build().perform();
        return this;
    }

    public ComposeEmailPopup sendEmailWithHotKeys() {
        new Actions(webDriver).click(subjectField)
                              .sendKeys(Keys.chord(IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL, Keys.ENTER))
                              .build()
                              .perform();
        return this;
    }

    public ComposeEmailPopup fillInSubjectField(String subject) {
        if (!subject.equals(StringUtils.EMPTY)) {
            subjectField.sendKeys(subject);
            return this;
        } else {
            return this;
        }
    }

    public ComposeEmailPopup fillInBodyField(String body) {
        if (body != StringUtils.EMPTY) {
            bodyField.sendKeys(body);
            return this;
        } else {
            return this;
        }
    }

    public MailPage clickCloseButton() {
        closeButton.click();
        return new MailPage();
    }

    public String getBody(String subject) {
        return StringUtils.remove(webDriver.findElement(By.xpath(String.format(SUBJECT_LOCATOR, subject))).getText(), " - ");
    }

    public String getTo() {
        return toValue.getAttribute(TO_VALUE_ATTRIBUTE);
    }

    public String getSubject() {
        return subjectField.getAttribute(SUBJECT_VALUE_ATTRIBUTE);
    }

    public MailListingPage clickSendButton() {
        waitForElementToBeClickable(webDriver, sendButton);
        sendButton.click();
        waitForElementVisibility(webDriver, messageSentLink);
        return new MailListingPage();
    }
}

package com.kunitskaya.selenium.pages.pf;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForElementVisibility;
import static com.kunitskaya.base.selenium.waits.ExplicitWait.waitForPageLoadComplete;

public class MailListingPage extends MailPage {
    public static final String MESSAGE_ROW_LOCATOR = "//span[contains(text(),'%s')]";
    protected static final String SUBJECT_LOCATOR = "//span[contains(text(),'%s')]/following-sibling::span[1]";

    public MailListingPage() {
        super();
    }

    protected WebElement findEmailBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElement(By.xpath(String.format(MESSAGE_ROW_LOCATOR, subject)));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }

    protected List<WebElement> findEmailsBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElements(By.xpath((String.format(MESSAGE_ROW_LOCATOR, subject))));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }

    public ComposeEmailPopup openDraftWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        return new ComposeEmailPopup();
    }


    public MailDetailsPage openEmailWithSubject(String subject) {
        try {
            waitForElementVisibility(webDriver, findEmailBySubject(webDriver, subject));
            findEmailBySubject(webDriver, subject).click();
            return new MailDetailsPage();
        } catch (ElementNotVisibleException e) {
            return new MailDetailsPage();
        }
    }

    public boolean isEmailPresentOnPage(String subject) {
        webDriver.navigate().refresh();
        waitForPageLoadComplete(webDriver);
        return !findEmailsBySubject(webDriver, subject).isEmpty();
    }

    public MailListingPage deleteAllEmails() {

        new Actions(webDriver).keyDown(Keys.SHIFT)
                              .sendKeys("*", "a")
                              .keyUp(Keys.SHIFT)
                              .sendKeys(Keys.chord(Keys.SHIFT, "#"))
                              .sendKeys(Keys.ENTER)
                              .build()
                              .perform();

        new Actions(webDriver).sendKeys(Keys.ENTER).build().perform();
        return this;
    }
}

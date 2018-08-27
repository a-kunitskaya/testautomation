package com.kunitskaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.kunitskaya.base.waits.ExplicitWait.waitForElementVisibility;
import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

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
}

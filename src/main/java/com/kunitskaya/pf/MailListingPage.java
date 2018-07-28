package com.kunitskaya.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class MailListingPage extends MailPage {
    protected static final String MESSAGE_ROW_LOCATOR = "//span[contains(text(),'%s')]";


    protected MailDetailsPage openSentMailWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        waitForPageLoadComplete(webDriver);
        return new MailDetailsPage();
    }

    protected static WebElement findEmailBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElement(By.xpath(String.format(MESSAGE_ROW_LOCATOR, subject)));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }
}

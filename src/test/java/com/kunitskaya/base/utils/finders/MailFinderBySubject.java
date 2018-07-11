package com.kunitskaya.base.utils.finders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailFinderBySubject {

    /**
     * Find an email by its subject
     *
     * @param webDriver - web driver
     * @param subject   - the email subject or its part
     * @return - the most recent email with the specified subject
     */
    public static WebElement findEmailBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElement(By.xpath("//span[contains(text(), '" + subject + "')]"));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }

}

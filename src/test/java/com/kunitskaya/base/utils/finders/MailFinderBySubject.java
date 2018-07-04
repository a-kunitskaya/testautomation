package com.kunitskaya.base.utils.finders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailFinderBySubject {

    public static WebElement findRecentMailBySubject(WebDriver webDriver, String subject) {
        List<WebElement> mails = webDriver.findElements(By.xpath("//span[contains(text(), '" + subject + "')]"));
        return mails.get(0);
    }
}

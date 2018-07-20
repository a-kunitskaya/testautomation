package com.kunitskaya.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.kunitskaya.base.utils.finders.MailFinderBySubject.findEmailBySubject;

public class SentMailPage extends BaseLoggedInPage {
    private static final By TO = By.xpath("//span[@dir='ltr' and @class='g2']");
    private static final By BODY = By.xpath("//div/div[@dir='ltr']");
    private static final By SUBJECT = By.cssSelector("h2.hP");

    public SentMailPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getSentMailWithSubject(String subject) {
        return findEmailBySubject(webDriver, subject);
    }

    public SentMailPage openSentMailWithSubject(String subject) {
        getSentMailWithSubject(subject).click();
        waitForPageLoadComplete();
        return this;
    }

    public String getSentMailContent(String subject) {
        return getSubject() + getTo() + getBody(subject);
    }

    public String getBody(String subject) {
        return webDriver.findElement(BODY).getText();

    }

    public String getTo() {
        return webDriver.findElement(TO).getAttribute("email");
    }


    public String getSubject() {
        return webDriver.findElement(SUBJECT).getText();
    }
}

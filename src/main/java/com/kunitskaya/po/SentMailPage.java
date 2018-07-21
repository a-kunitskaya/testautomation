package com.kunitskaya.po;

import org.openqa.selenium.By;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class SentMailPage extends MailPage {
    private static final By TO = By.xpath("//span[@dir='ltr' and @class='g2']");
    private static final By BODY = By.xpath("//div/div[@dir='ltr']");
    private static final By SUBJECT = By.cssSelector("h2.hP");

    public SentMailPage openSentMailWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        waitForPageLoadComplete(webDriver);
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

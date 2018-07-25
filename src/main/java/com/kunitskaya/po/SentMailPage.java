package com.kunitskaya.po;

import org.openqa.selenium.By;

public class SentMailPage extends MailPage {
    private static final By TO = By.xpath("//span[@dir='ltr' and @class='g2']");
    private static final By BODY = By.xpath("//div/div[@dir='ltr']");
    private static final By SUBJECT = By.cssSelector("h2.hP");

    public String getSentMailContent(String subject) {
        return getSubject() + getTo() + getBody();
    }

    public String getBody() {
        return webDriver.findElement(BODY).getText();

    }

    public String getTo() {
        return webDriver.findElement(TO).getAttribute("email");
    }


    public String getSubject() {
        return webDriver.findElement(SUBJECT).getText();
    }

    @Override
    public SentMailPage openEmailWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        return this;
    }
}

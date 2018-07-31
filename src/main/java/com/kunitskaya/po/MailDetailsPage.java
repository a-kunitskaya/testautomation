package com.kunitskaya.po;

import org.openqa.selenium.By;

public class MailDetailsPage extends MailPage {
    private static final By TO = By.xpath("//span[@dir='ltr' and @class='g2']");
    private static final By BODY = By.xpath("//div/div[@dir='ltr']");
    private static final By SUBJECT = By.cssSelector("h2.hP");

    public MailDetailsPage() {
        super();
    }

    public String getSentMailContent() {
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
}

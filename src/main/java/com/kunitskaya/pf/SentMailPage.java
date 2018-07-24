package com.kunitskaya.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class SentMailPage extends MailPage {

    @FindBy(xpath = "//span[@dir='ltr' and @class='g2']")
    WebElement to;

    @FindBy(css = "h2.hP")
    WebElement subject;

    public SentMailPage openSentMailWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        waitForPageLoadComplete(webDriver);
        return this;
    }

    protected static WebElement findEmailBySubject(WebDriver webDriver, String subject) {
        if (!subject.isEmpty()) {
            return webDriver.findElement(By.xpath("//span[contains(text(), '" + subject + "')]"));
        } else {
            throw new IllegalArgumentException("Subject string is empty. Please specify subject");
        }
    }
    public String getSentMailContent(String subject) {
        return getSubject() + getTo();
    }

    public String getTo() {
        return to.getAttribute("email");
    }

    public String getSubject() {
        return subject.getText();
    }
}

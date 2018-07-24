package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;
import static com.kunitskaya.po.MailPage.findEmailBySubject;

public class SentMailPage extends AbstractPage {

    @FindBy(xpath = "//span[@dir='ltr' and @class='g2']")
    WebElement to;

    @FindBy(css = "h2.hP")
    WebElement subject;

    public SentMailPage openSentMailWithSubject(String subject) {
        findEmailBySubject(webDriver, subject).click();
        waitForPageLoadComplete(webDriver);
        return this;
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

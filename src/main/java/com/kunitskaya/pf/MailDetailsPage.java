package com.kunitskaya.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailDetailsPage extends MailPage {

    @FindBy(xpath = "//span[@dir='ltr' and @class='g2']")
    WebElement to;

    @FindBy(css = "h2.hP")
    WebElement subject;

    public String getMailContent() {
        return getSubject() + getTo();
    }

    public String getTo() {
        return to.getAttribute("email");
    }

    public String getSubject() {
        return subject.getText();
    }
}

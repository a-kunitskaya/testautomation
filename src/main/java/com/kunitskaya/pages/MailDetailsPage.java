package com.kunitskaya.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailDetailsPage extends MailPage {

    @FindBy(xpath = "//span[@dir='ltr' and @class='g2']")
    WebElement to;

    @FindBy(css = "h2.hP")
    WebElement subject;

    @FindBy(xpath = "//div/div[@dir='ltr']")
    WebElement body;

    public MailDetailsPage() {
        super();
    }

    public String getTo() {
        return to.getAttribute("email");
    }

    public String getSubject() {
        return subject.getText();
    }

    public String getBody() {
        return body.getText();

    }
}

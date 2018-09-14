package com.kunitskaya.selenium.pages.pf;

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
        highlightElement(to);
        String result = to.getAttribute("email");
        unHighlightElement(to);
        return result;
    }

    public String getSubject() {
        highlightElement(subject);
        String result = subject.getText();
        unHighlightElement(subject);
        return result;
    }

    public String getBody() {
        highlightElement(body);
        String result = body.getText();
        unHighlightElement(body);
        return result;
    }
}

package com.kunitskaya.pf;

import com.kunitskaya.base.Browser;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.PageFactory;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage extends Browser {

    public AbstractPage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        waitForPageLoadComplete(webDriver);
        return webDriver.getTitle();
    }

    public boolean isAlertDisplayed() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        webDriver.switchTo().alert().accept();
    }
}

package com.kunitskaya.pf;

import com.kunitskaya.base.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage extends Browser {
    protected WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public String getTitle() {
        waitForPageLoadComplete(webDriver);
        return webDriver.getTitle();
    }
}

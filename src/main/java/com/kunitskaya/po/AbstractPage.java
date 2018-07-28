package com.kunitskaya.po;

import org.openqa.selenium.WebDriver;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class AbstractPage {
    WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        waitForPageLoadComplete(webDriver);
    }
}

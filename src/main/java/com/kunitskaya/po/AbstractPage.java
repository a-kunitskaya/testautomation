package com.kunitskaya.po;

import com.kunitskaya.base.waits.ExplicitWait;
import com.kunitskaya.base.waits.ImplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbstractPage {
    private static final int DEFAULT_TIMEOUT = 20;
    protected WebDriver webDriver;

    public AbstractPage(WebDriver driver) {
        this.webDriver = driver;
    }

    protected void waitForElementPresence(By locator) {
        ExplicitWait.waitForElementPresence(webDriver, DEFAULT_TIMEOUT, locator);
    }

    protected void waitForElementVisibility(By locator) {
       ExplicitWait.waitForElementVisibility(webDriver, DEFAULT_TIMEOUT, locator);
    }

    protected void waitForPageLoadComplete() {
        ExplicitWait.waitForPageLoadComplete(webDriver);
    }

    protected void waitForAjaxExecution(){
        ImplicitWait.waitForAjaxExecution(webDriver, DEFAULT_TIMEOUT);
    }

    protected void waitForElementToBeClickable(By locator){
        ExplicitWait.waitForElementToBeClickable(webDriver, DEFAULT_TIMEOUT, locator);
    }

}

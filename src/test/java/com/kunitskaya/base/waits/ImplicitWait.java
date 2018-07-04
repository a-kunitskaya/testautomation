package com.kunitskaya.base.waits;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * We tell Selenium that we would like it to wait for a certain amount of time
 * before throwing an exception that it cannot find the element on the page
 */
public class ImplicitWait {
    public static void waitImplicitly(WebDriver webDriver, int timeout) {
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }
}

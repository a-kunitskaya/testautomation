package com.kunitskaya.base.waits;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * We tell Selenium that we would like it to wait for a certain amount of time
 * before throwing an exception that it cannot find the element on the page
 */
public class ImplicitWait {

    /**
     * Waits for the specified amount of time
     *
     * @param webDriver - web driver
     * @param timeout   - the amount of time to wait in seconds
     */
    public static void waitImplicitly(WebDriver webDriver, int timeout) {
        webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    /**
     * Waits for AJAX to finish
     *
     * @param webDriver - web driver
     * @param timeout   - the amount of time to wait in seconds
     */
    public static void waitForAjaxExecution(WebDriver webDriver, int timeout) {
        webDriver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);

    }
}

package com.kunitskaya.base.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Defines the maximum amount of time to wait for a condition,
 * as well as the frequency with which to check the condition
 * = explicit wait
 */
public class CustomFluentWait {

    /**
     * Waits for an element to appear
     * ignores NoSuchElementException while waiting
     *
     * @param webDriver - web driver
     * @param timeout   - max amount of time to wait in seconds
     * @param locator   - element locator
     */
    public static void waitForElementFluently(WebDriver webDriver, int timeout, final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }
}

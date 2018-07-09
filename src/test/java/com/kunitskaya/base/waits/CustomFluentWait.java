package com.kunitskaya.base.waits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Defines the maximum amount of time to wait for a condition,
 * as well as the frequency with which to check the condition
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

        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    /**
     * Waits for the page to load
     *
     * @param driver - web driver
     */
    public static void waitForPageLoadComplete(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30, 10000L);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }
}

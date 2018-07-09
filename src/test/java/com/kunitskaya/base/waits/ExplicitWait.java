package com.kunitskaya.base.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waits for the specified condition
 */
public class ExplicitWait {

    /**
     * Waits for an element to appear on the page
     *
     * @param webDriver - web driver
     * @param timeout   - max amount of time to wait in seconds
     * @param locator   - element locator
     */
    public static void waitForElementExplicitly(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for element visibility
     *
     * @param webDriver- web driver
     * @param timeout-   max amount of time to wait in seconds
     * @param locator    - element locator
     */
    public static void waitForElementVisibility(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

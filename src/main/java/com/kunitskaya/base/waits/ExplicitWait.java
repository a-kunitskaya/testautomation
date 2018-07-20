package com.kunitskaya.base.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
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
    public static void waitForElementPresence(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for element visibility
     *
     * @param webDriver - web driver
     * @param timeout   -  max amount of time to wait in seconds
     * @param locator   - element locator
     */
    public static void waitForElementVisibility(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * @param webDriver - web driver
     * @param timeout   - max amount of time to wait in seconds
     * @param locator   - element locator
     */
    public static void waitForElementToBeClickable(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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

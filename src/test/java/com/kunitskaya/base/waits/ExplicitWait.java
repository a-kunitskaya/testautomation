package com.kunitskaya.base.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waits for the specified condition
 */
public class ExplicitWait {
    public static void waitForElementExplicitly(WebDriver webDriver, int timeout, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}

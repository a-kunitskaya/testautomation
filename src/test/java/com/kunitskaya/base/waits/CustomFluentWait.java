package com.kunitskaya.base.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Defines the maximum amount of time to wait for a condition,
 * as well as the frequency with which to check the condition,
 * can be configured to ignore specific types of exceptions whilst waiting,
 * such as NoSuchElementExceptions when searching for an element on the page.
 *
 * does not work for now
 */
public class CustomFluentWait {

    public static WebElement waitForElementFluently(WebDriver webDriver, int timeout, final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
        return null;
    }
}
package com.kunitskaya.base.waits;

import com.kunitskaya.test.ConfigProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waits for the specified condition
 */
public class ExplicitWait {

    private static ConfigProvider configProvider = ConfigProvider.getInstance();

    /**
     * Waits for an element to appear on the page
     *
     * @param webDriver - web driver
     * @param locator   - element locator
     */
    public static void waitForElementPresence(WebDriver webDriver, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, configProvider.getDefaultTimeout());
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for element visibility
     *
     * @param webDriver - web driver
     * @param locator   - element locator
     */
    public static void waitForElementVisibility(WebDriver webDriver, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, configProvider.getDefaultTimeout());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for element visibility
     *
     * @param webDriver  web driver
     * @param webElement - web element instance
     */
    public static void waitForElementVisibility(WebDriver webDriver, WebElement webElement) {
        new WebDriverWait(webDriver, configProvider.getDefaultTimeout()).until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Waits for element to be clickable
     *
     * @param webDriver - web driver
     * @param locator   - element locator
     */
    public static void waitForElementToBeClickable(WebDriver webDriver, By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, configProvider.getDefaultTimeout());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for element to be clickable
     *
     * @param webDriver  web driver
     * @param webElement - web element instance
     */
    public static void waitForElementToBeClickable(WebDriver webDriver, WebElement webElement) {
        new WebDriverWait(webDriver, configProvider.getDefaultTimeout()).until(ExpectedConditions.elementToBeClickable(webElement));
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

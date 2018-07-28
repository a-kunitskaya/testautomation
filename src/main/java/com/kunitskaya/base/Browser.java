package com.kunitskaya.base;

import com.google.common.collect.Iterables;
import org.openqa.selenium.WebDriver;

public class Browser {
    private static Browser instance;
    WebDriver webDriver;

    private Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static Browser getInstance(WebDriver webDriver) {
        if (instance == null) {
            instance = new Browser(webDriver);
        }
        return instance;
    }

    public void switchToLastOpenedWindow() {
        String windowHandle = Iterables.getLast(webDriver.getWindowHandles());
        webDriver.switchTo().window(windowHandle);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }

    public String getCurrentWindowHandle() {
        return webDriver.getWindowHandle();
    }

    public void switchToWindowHandle(String windowHandle) {
        webDriver.switchTo().window(windowHandle);
    }

    public void closeCurrentWindow() {
        webDriver.close();
    }

    public void clearCookies(WebDriver webDriver) {
        webDriver.manage().deleteAllCookies();
    }
}

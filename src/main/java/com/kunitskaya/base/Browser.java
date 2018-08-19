package com.kunitskaya.base;

import com.google.common.collect.Iterables;
import com.kunitskaya.base.webdriver.WebDriverProvider;
import org.openqa.selenium.WebDriver;

import static com.kunitskaya.base.webdriver.WebDriverProvider.resetDriver;

public class Browser {
    private static Browser instance;
    private WebDriver webDriver;

    private Browser() {
        this.webDriver = WebDriverProvider.getInstance();
    }

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public void switchToLastOpenedWindow() {
        String winHandle = Iterables.getLast(webDriver.getWindowHandles());
        webDriver.switchTo().window(winHandle);

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

    public void clearCookies() {
        webDriver.manage().deleteAllCookies();
    }

    public void quit() {
        resetDriver();
        instance = null;
    }
}

package com.kunitskaya.base.selenium;

import com.google.common.collect.Iterables;
import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

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

    public boolean isAlertDisplayed() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        webDriver.switchTo().alert().accept();
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
        WebDriverProvider.resetDriver();
        instance = null;
    }
}

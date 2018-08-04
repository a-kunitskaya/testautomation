package com.kunitskaya.base;

import com.google.common.collect.Iterables;
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

    public void quit(){
        webDriver.quit();
        instance = null;
    }
}

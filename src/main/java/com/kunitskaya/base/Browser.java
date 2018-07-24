package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class Browser {
    protected WebDriver webDriver;

    public Browser() {
        this.webDriver = WebDriverProvider.getInstance();
        waitForPageLoadComplete(webDriver);
    }

    public void switchToLastOpenedWindow() {
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

    public void closeCurrentWindow(){
     webDriver.close();
    }

    public static void clearCookies(WebDriver webDriver){
        webDriver.manage().deleteAllCookies();
    }
}

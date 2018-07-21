package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;

public class Browser {
    protected WebDriver webDriver;

    public Browser() {
        this.webDriver = WebDriverProvider.getInstance();
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
}

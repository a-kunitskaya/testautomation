package com.kunitskaya.base;

import com.google.common.collect.Iterables;
import org.openqa.selenium.WebDriver;

import static com.kunitskaya.base.waits.ExplicitWait.waitForPageLoadComplete;

public class Browser {
    protected WebDriver webDriver;

    public Browser() {
        this.webDriver = WebDriverProvider.getInstance();
        waitForPageLoadComplete(webDriver);
    }

    public void switchToLastOpenedWindow() {
        String windowHandle = Iterables.getLast(webDriver.getWindowHandles());
        webDriver.switchTo().window(windowHandle);
//        for (String winHandle : webDriver.getWindowHandles()) {
//            webDriver.switchTo().window(winHandle);
//        }
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

    public static void clearCookies(WebDriver webDriver){ //не статический должен быть, т к буду объект создавать
        webDriver.manage().deleteAllCookies();
    }
}

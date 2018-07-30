package com.kunitskaya.base.waits;

import com.kunitskaya.test.ConfigProvider;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * We tell Selenium that we would like it to wait for a certain amount of time
 * before throwing an exception that it cannot find the element on the page
 */
public class ImplicitWait {

    private static ConfigProvider configProvider = ConfigProvider.getInstance();
    /**
     * Waits for the specified amount of time
     *
     * @param webDriver - web driver
     */
    public static void waitImplicitly(WebDriver webDriver) throws IOException {
        webDriver.manage().timeouts().implicitlyWait(configProvider.getDefaultTimeout(), TimeUnit.SECONDS);
    }
}

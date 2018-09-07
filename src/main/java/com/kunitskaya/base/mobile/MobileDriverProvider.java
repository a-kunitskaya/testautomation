package com.kunitskaya.base.mobile;

import com.kunitskaya.base.ConfigProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverProvider {

    /**
     * Initializes driver with android capabilities
     *
     * @return web driver
     */
    public static WebDriver getAndroidDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Nexus_5X_API_28_2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("chromedriverExecutable", "/Users/aleksandra/Documents/Projects/cdp/src/test/resources/chromedriver");

        try {
            return new RemoteWebDriver(new URL(ConfigProvider.getInstance().getAppiumServerUrl()), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

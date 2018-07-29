package com.kunitskaya.base;

import com.kunitskaya.test.ConfigProvider;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

public class WebDriverProvider {

    private static WebDriver webDriver;
    static ConfigProvider configProvider = ConfigProvider.getInstance();

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() throws IOException {
        if (webDriver == null) {
            if (configProvider.getIsRemoteDriver()) {
                initializeRemoteWebDriver();
            } else {
                initializeChromeDriver();
            }
            waitImplicitly(webDriver);
        }
        return webDriver;
    }

    public static void initializeChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            chromeOptions.addArguments("--kiosk");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            chromeOptions.addArguments("--start-maximized");
        }
        webDriver = new ChromeDriver(chromeOptions);
    }

    public static void initializeRemoteWebDriver() {
        try {
            webDriver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void webDriverQuit() {
        webDriver.quit();
        webDriver = null;
    }
}

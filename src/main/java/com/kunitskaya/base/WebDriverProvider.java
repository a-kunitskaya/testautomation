package com.kunitskaya.base;

import com.kunitskaya.test.ConfigProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.kunitskaya.base.Browsers.getBrowser;
import static com.kunitskaya.base.Platforms.MAC;
import static com.kunitskaya.base.Platforms.getPlatform;
import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

public class WebDriverProvider {

    private static WebDriver webDriver;
    private static ConfigProvider configProvider = ConfigProvider.getInstance();

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() {
        if (webDriver == null) {
            initializeDriver();
            waitImplicitly(webDriver);
        }
        return webDriver;
    }

    private static void initializeDriver() {
        DesiredCapabilities capabilities;
        ChromeOptions chromeOptions = new ChromeOptions();
        String currentBrowser = configProvider.getBrowser();
        String currentPlatform = configProvider.getPlatform();
        boolean isRemoteDriver = configProvider.isRemoteDriver();
        Browsers browser = getBrowser(currentBrowser);
        Platforms platform = getPlatform(currentPlatform);

        //getting the url from properties file since it changes every time I start the grid
        URL hubUrl = null;
        try {
            hubUrl = new URL(configProvider.getHubUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case CHROME:
                chromeOptions = platform.equals(MAC) ? chromeOptions.addArguments("--kiosk") : chromeOptions.addArguments("--start-maximized");

                if (isRemoteDriver) {
                    capabilities = DesiredCapabilities.chrome().merge(chromeOptions);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                } else {
                    if (platform.equals(MAC)) {
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                    } else {
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                    }
                    System.setProperty("selenide.browser", currentBrowser);
                    webDriver = new ChromeDriver(chromeOptions);
                }
                break;
        }
    }

    public static void resetDriver() {
        webDriver.quit();
        webDriver = null;
    }
}

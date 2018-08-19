package com.kunitskaya.base.webdriver;

import com.kunitskaya.test.Browsers;
import com.kunitskaya.test.Platforms;
import com.kunitskaya.test.ConfigProvider;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static com.kunitskaya.test.Browsers.getBrowser;
import static com.kunitskaya.test.Platforms.MAC;
import static com.kunitskaya.test.Platforms.getPlatform;
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
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = new WebDriverDecorator(webDriver);
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
                chromeOptions.addArguments("--lang=en");
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                if (isRemoteDriver) {
                    capabilities = DesiredCapabilities.chrome().merge(chromeOptions);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                } else {
                    if (platform.equals(MAC)) {
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                    } else {
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                    }
                    webDriver = new ChromeDriver(chromeOptions);

                    //set selenide web driver
                    setWebDriver(webDriver);
                }
                break;
        }
    }

    public static void resetDriver() {
        webDriver.quit();
        webDriver = null;
    }
}

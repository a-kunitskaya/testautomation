package com.kunitskaya.base.selenium.webdriver;

import com.kunitskaya.base.ConfigProvider;
import com.kunitskaya.base.selenium.Browsers;
import com.kunitskaya.base.selenium.Platforms;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static com.kunitskaya.base.selenium.Browsers.getBrowser;
import static com.kunitskaya.base.selenium.Platforms.MAC;
import static com.kunitskaya.base.selenium.waits.ImplicitWait.waitImplicitly;
import static com.kunitskaya.logging.TestLogger.TEST_LOGGER;

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

        String currentBrowser = configProvider.getBrowser();
        Browsers browser = getBrowser(currentBrowser);
        boolean isMobile = configProvider.isMobile();

        switch (browser) {
            case CHROME:
                if (isMobile) {
                    getMobileDriver();
                } else {
                    webDriver = getChromeDriver();
                    setSelenideDriver(webDriver);
                }
                break;
        }
        webDriver = new WebDriverDecorator(webDriver);

    }

    public static void resetDriver() {
        webDriver.quit();
        webDriver = null;
    }

    private static WebDriver getChromeDriver() {
        TEST_LOGGER.info("Initializing chrome driver");

        String currentPlatform = configProvider.getPlatform();
        Platforms platform = Platforms.valueOf(currentPlatform);
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions = platform.equals(MAC) ? chromeOptions.addArguments("--kiosk") : chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--lang=en");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

        if (configProvider.isRemoteDriver()) {
            return getRemoteDriver(chromeOptions);
        } else {
            System.setProperty("webdriver.chrome.driver", configProvider.getChromeDriverPath());
            return new ChromeDriver(chromeOptions);
        }

    }

    private static WebDriver getRemoteDriver(ChromeOptions chromeOptions) {
        TEST_LOGGER.info("Initializing remote chrome driver");
        URL hubUrl = null;
        try {
            hubUrl = new URL(configProvider.getHubUrl());
        } catch (MalformedURLException e) {
            TEST_LOGGER.error("Selenium hub url is not found " + e.getMessage());
        }
        DesiredCapabilities capabilities = DesiredCapabilities.chrome().merge(chromeOptions);
        return new RemoteWebDriver(hubUrl, capabilities);
    }

    private static void setSelenideDriver(WebDriver webDriver) {
        TEST_LOGGER.info("Setting selenide driver");
        setWebDriver(webDriver);
    }

    private static void getMobileDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String deviceName = configProvider.getDeviceName();
        String mobilePlatform = configProvider.getMobilePlatform();
        String browser = configProvider.getBrowser();

        TEST_LOGGER.info("Initializing mobile driver for " + deviceName + " on " + mobilePlatform);

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", mobilePlatform);
        capabilities.setCapability("browserName", StringUtils.capitalize(browser));
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+configProvider.getChromeDriverPath());

        try {
            webDriver = new RemoteWebDriver(new URL(configProvider.getAppiumServerUrl()), capabilities);
        } catch (MalformedURLException e) {
            TEST_LOGGER.fatal("Could not initialize mobile driver " + e.getMessage());
        }
    }
}



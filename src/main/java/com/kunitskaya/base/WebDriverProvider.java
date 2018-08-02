package com.kunitskaya.base;

import com.kunitskaya.test.ConfigProvider;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;
import static com.kunitskaya.pf.LoginPage.LOGIN_PAGE_URL;

public class WebDriverProvider {

    private static WebDriver webDriver;
    static ConfigProvider configProvider = ConfigProvider.getInstance();

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() throws IOException {
        if (webDriver == null) {
            if (configProvider.getIsRemoteDriver()) {
                initializeRemoteDriver();
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

    public static void initializeRemoteDriver() {
        DesiredCapabilities capabilities = null;
        String browser = configProvider.getBrowser();
        String platform = configProvider.getPlatform();

        //getting the url from properties file since it changes depending on the network
        URL hubUrl = null;
        try {
            hubUrl = new URL(configProvider.getHubUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (platform.equals("MAC")) {
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--kiosk");
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.merge(chromeOptions);
                    break;

                case "firefox":
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("browser.startup.homepage", LOGIN_PAGE_URL);
                    firefoxProfile.setPreference("intl.accept_languages", "en-US");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setProfile(firefoxProfile);
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.merge(firefoxOptions);
                    break;
            }
            webDriver = new RemoteWebDriver(hubUrl, capabilities);

        } else {
            switch (browser) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.merge(options);
                    webDriver = new RemoteWebDriver(hubUrl, capabilities);
                    break;

                case "firefox":
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("browser.startup.homepage", LOGIN_PAGE_URL);
                    firefoxProfile.setPreference("intl.accept_languages", "en-US");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setProfile(firefoxProfile);
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.merge(firefoxOptions);
                    break;
            }

        }
    }

    public static void webDriverQuit() {
        webDriver.quit();
        webDriver = null;
    }
}

package com.kunitskaya.base;

import com.kunitskaya.test.ConfigProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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

    public static void initializeDriver() {
        DesiredCapabilities capabilities = null;
        ChromeOptions chromeOptions = null;
        String browser = configProvider.getBrowser();
        String platform = configProvider.getPlatform();
        boolean isRemoteDriver = configProvider.getIsRemoteDriver();

        //getting the url from properties file since it changes every time I start the grid
        URL hubUrl = null;
        try {
            hubUrl = new URL(configProvider.getHubUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (platform.equals("MAC")) {
            switch (browser) {
                case "chrome":
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--kiosk");
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.merge(chromeOptions);
                    break;

            }
        } else {
            switch (browser) {
                case "chrome":
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.merge(chromeOptions);
                    break;
            }
        }
        if (isRemoteDriver) {
            webDriver = new RemoteWebDriver(hubUrl, capabilities);
        } else if (platform.equals("MAC")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        }
        webDriver = new ChromeDriver(chromeOptions);
    }

    public static void webDriverQuit() {
        webDriver.quit();
        webDriver = null;
    }
}

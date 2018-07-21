package com.kunitskaya.base;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

public class WebDriverProvider {

    private static WebDriver webDriver;

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() {
        if (webDriver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (SystemUtils.IS_OS_MAC) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                chromeOptions.addArguments("--kiosk");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                chromeOptions.addArguments("--start-maximized");
            }
            webDriver = new ChromeDriver(chromeOptions);
            waitImplicitly(webDriver);
        }
        return webDriver;
    }
}

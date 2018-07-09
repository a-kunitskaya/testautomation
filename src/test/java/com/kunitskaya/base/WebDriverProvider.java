package com.kunitskaya.base;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider {

    private static WebDriver driver;

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            if (SystemUtils.IS_OS_MAC) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            }
            driver = new ChromeDriver();
            driver.manage().window().fullscreen();
        }
        return driver;
    }
}

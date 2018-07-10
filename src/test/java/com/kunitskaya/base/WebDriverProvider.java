package com.kunitskaya.base;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

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
            driver.manage().window().maximize();
            waitImplicitly(driver, 30);
        }
        return driver;
    }
}

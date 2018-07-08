package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider {

    private static WebDriver driver;

    private WebDriverProvider() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().fullscreen();
        }
        return driver;
    }
}

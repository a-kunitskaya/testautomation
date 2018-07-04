package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver = WebDriverSingleton.getInstance();

    @BeforeSuite
    public void setUp() {
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
    }
}
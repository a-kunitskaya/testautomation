package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

/**
 * TODO: Do not use URLs to navigate to pages
 */
public class BaseTest {

    protected WebDriver webDriver = WebDriverProvider.getInstance();

    @BeforeClass
    public void deleteCookies() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
    }
}
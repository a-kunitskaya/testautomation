package com.kunitskaya;

import com.kunitskaya.base.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

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
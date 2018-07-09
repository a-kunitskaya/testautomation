package com.kunitskaya.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import static com.kunitskaya.base.waits.ImplicitWait.waitImplicitly;

public class BaseTest {

    protected WebDriver webDriver = WebDriverProvider.getInstance();

    @BeforeClass
    public void deleteCookies(){
        webDriver.manage().deleteAllCookies();
        waitImplicitly(webDriver, 30);
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
    }
}
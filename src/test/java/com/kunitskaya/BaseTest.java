package com.kunitskaya;

import com.kunitskaya.base.Browser;
import com.kunitskaya.base.WebDriverProvider;
import com.kunitskaya.business.objects.User;
import com.kunitskaya.test.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver webDriver;
    protected Browser browser;
    protected User user = TestDataProvider.getUser();

    @BeforeClass
    public void setUp() {
        webDriver = WebDriverProvider.getInstance();
        browser = Browser.getInstance();
        browser.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
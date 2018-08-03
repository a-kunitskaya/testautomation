package com.kunitskaya;

import com.kunitskaya.base.Browser;
import com.kunitskaya.base.WebDriverProvider;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.kunitskaya.base.WebDriverProvider.webDriverQuit;

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
        webDriverQuit();
    }
}
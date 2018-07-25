package com.kunitskaya;

import com.kunitskaya.base.WebDriverProvider;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.kunitskaya.base.Browser.clearCookies;

public class BaseTest {
    protected WebDriver webDriver = WebDriverProvider.getInstance();
    protected User user = TestDataProvider.getUser();

    @BeforeClass
    public void deleteCookies() {
        clearCookies(webDriver);
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
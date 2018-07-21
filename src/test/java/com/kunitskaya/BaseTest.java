package com.kunitskaya;

import com.kunitskaya.base.WebDriverProvider;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver webDriver = WebDriverProvider.getInstance();

   User user = TestDataProvider.getUser();
    //email в base test не выносить, тк не отностися ко всем тестам

    @BeforeClass
    public void deleteCookies() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void tearDown() {
        webDriver.quit();
    }
}
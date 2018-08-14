package com.kunitskaya;

import com.kunitskaya.base.Browser;
import com.kunitskaya.business.objects.user.User;
import com.kunitskaya.test.TestDataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected Browser browser;
    protected User user = TestDataProvider.getUser();

    @BeforeClass
    public void setUp() {
        browser = Browser.getInstance();
        browser.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
package com.kunitskaya.selenium;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.selenium.business.objects.user.GmailUserCreator;
import com.kunitskaya.selenium.business.objects.user.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SeleniumBaseTest {
    protected Browser browser;
    protected User user = new GmailUserCreator().createUser();

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

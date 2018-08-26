package com.kunitskaya;

import com.kunitskaya.base.Browser;
import com.kunitskaya.business.objects.user.GmailUserCreator;
import com.kunitskaya.business.objects.user.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
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

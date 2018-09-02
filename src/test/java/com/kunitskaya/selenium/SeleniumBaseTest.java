package com.kunitskaya.selenium;

import com.kunitskaya.base.selenium.Browser;
import com.kunitskaya.selenium.business.objects.user.GmailUserCreator;
import com.kunitskaya.selenium.business.objects.user.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class SeleniumBaseTest {
    protected Browser browser;
    protected User user = new GmailUserCreator().createUser();
    protected SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        browser = Browser.getInstance();
        browser.clearCookies();
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
        softAssert.assertAll();
    }
}

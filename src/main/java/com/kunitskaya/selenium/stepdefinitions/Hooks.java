package com.kunitskaya.selenium.stepdefinitions;

import com.kunitskaya.base.selenium.Browser;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks {
    protected Browser browser;

    @Before
    public void setUp() {
        browser = Browser.getInstance();
        browser.clearCookies();
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}

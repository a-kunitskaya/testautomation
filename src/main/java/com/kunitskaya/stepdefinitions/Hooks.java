package com.kunitskaya.stepdefinitions;

import com.kunitskaya.base.Browser;
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

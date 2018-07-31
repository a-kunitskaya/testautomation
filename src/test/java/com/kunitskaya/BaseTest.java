package com.kunitskaya;

import com.kunitskaya.base.Browser;
import com.kunitskaya.base.WebDriverProvider;
import com.kunitskaya.test.TestDataProvider;
import com.kunitskaya.test.entities.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.kunitskaya.base.WebDriverProvider.webDriverQuit;

//TODO: from test.properties - default timeout, browser,
// isRemoteDriver (эту переменную использовать в wd provider
// - если фолс, то драйвер - хром драйвер, если тру - то ремоут драйвер)

public class BaseTest {
    protected WebDriver webDriver;
    protected Browser browser;
    protected User user = TestDataProvider.getUser();

    @BeforeClass
    public void setUp() throws IOException {
        webDriver = WebDriverProvider.getInstance();
        browser = Browser.getInstance();
        browser.clearCookies(webDriver);
    }

    @AfterClass
    public void tearDown() {
        webDriverQuit();
    }
}
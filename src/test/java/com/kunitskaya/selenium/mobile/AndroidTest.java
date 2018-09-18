package com.kunitskaya.selenium.mobile;

import com.kunitskaya.base.selenium.webdriver.WebDriverProvider;
import com.kunitskaya.selenium.SeleniumBaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kunitskaya.selenium.mobile.HomePage.GOOGLE_HOME_URL;
import static org.testng.AssertJUnit.assertEquals;

public class AndroidTest extends SeleniumBaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverProvider.getInstance();
    }

    @Test
    public void validateWebSiteOpening() {
        new HomePage().open();
        assertEquals(GOOGLE_HOME_URL, driver.getCurrentUrl());
    }
}

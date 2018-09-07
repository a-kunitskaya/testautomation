package com.kunitskaya;

import com.kunitskaya.base.mobile.MobileDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AndroidTest {
    protected WebDriver driver;
    private static final String GOOGLE_HOME_URL = "https://www.google.com/";


    @BeforeClass
    protected void setUp() {
        driver = MobileDriverProvider.getAndroidDriver();
    }

    @Test
    public void validateWebSiteOpening() {
        driver.get(GOOGLE_HOME_URL);
        assertEquals(GOOGLE_HOME_URL, driver.getCurrentUrl());
    }
}

package com.kunitskaya;

import com.kunitskaya.mobile.MobileDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class AndroidTest {
    protected WebDriver driver;
    private static final String GOOGLE_HOME_URL = "https://www.google.com/";
    MobileDriverProvider mobileDriverProvider = MobileDriverProvider.getInstance();

    @BeforeClass
    protected void setUp() {
        driver = mobileDriverProvider.getAndroidDriver();
    }

    @Test
    public void validateWebSiteOpening() {
        driver.get(GOOGLE_HOME_URL);
        assertEquals(GOOGLE_HOME_URL, driver.getCurrentUrl());
    }
}
